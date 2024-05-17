package com.example.transpapptest.data.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.config.common.apiRequestFlow
import com.example.transpapptest.data.local.dao.CustomerDao
import com.example.transpapptest.data.local.entities.relations.OrderAndStatus
import com.example.transpapptest.data.remote.api.CustomerApi
import com.example.transpapptest.data.remote.payload.request.AddOrderRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.customer.order.OrderInfoResponse
import com.example.transpapptest.domain.repository.OrderRepository
import com.example.transpapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepositoryImpl @Inject constructor(
    private val dao: CustomerDao,
    private val api: CustomerApi,
    private val tokenManager: TokenManager
) : OrderRepository {

    override suspend fun getAllOrdersWithStatus(fetchFromRemote: Boolean): Flow<ApiResponse<List<OrderAndStatus>>> =
        flow {
            emit(ApiResponse.Loading)
            val customerId = tokenManager.getUserId().first()

            customerId?.let { id ->
                val localOrderAndStatus = dao.getOrderAndStatus()

                if (localOrderAndStatus != null) {
                    emit(ApiResponse.Success(localOrderAndStatus))
                }

                val isDbEmpty = localOrderAndStatus == null

                val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

                if (shouldJustLoadFromCache) {
                    return@flow
                }

                val remote = getRemote(id)

                remote?.let { orderInfoResponseList: List<OrderInfoResponse> ->
                    dao.clearOrderEntity()
                    dao.clearCustomerInfoEntity()
                    dao.clearProducerInfoEntity()
                    dao.clearTransporterInfoEntity()
                    dao.clearPickupAddressEntity()
                    dao.clearDeliveryAddressEntity()
                    dao.clearStatusEntity()

                    orderInfoResponseList.forEach {
                        dao.insertOrder(it.toOrderEntity())
                        dao.insertCustomerInfoEntity(it.toCustomerInfoEntity())
                        dao.insertProducerInfoEntity(it.toProducerInfoEntity())
                        if (it.transporterContactInfoResponse!!.transporterId != null) {
                            dao.insertTransporterInfoEntity(it.toTransporterInfoEntity())
                        }
                        dao.insertPickupAddress(it.toPickupAddressEntity())
                        dao.insertDeliveryAddress(it.toDeliveryAddressEntity())

                        it.statusList.forEach { statusResponse ->
                            dao.insertStatus(statusResponse.toStatusEntity())
                        }
                    }
                }

                val newOrderAndStatus = dao.getOrderAndStatus()

                newOrderAndStatus?.let {
                    emit(ApiResponse.Success(newOrderAndStatus))
                }
            }
        }

    override suspend fun addOrder(addOrderRequest: AddOrderRequest): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow {
            api.addOrder(addOrderRequest)
        }
    }

    private suspend fun FlowCollector<ApiResponse<List<OrderAndStatus>>>.getRemote(
        id: Long
    ) = try {
        val response = api.getAllOrders(id)
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }

    } catch (e: IOException) {
        e.printStackTrace()
        emit(ApiResponse.Failure("No se pudo cargar los datos", 400))
        null
    } catch (e: HttpException) {
        e.printStackTrace()
        emit(ApiResponse.Failure("No se pudo cargar los datos", 400))
        null
    }
}