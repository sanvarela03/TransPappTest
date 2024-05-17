package com.example.transpapptest.data.repository

import android.util.Log
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.dao.AddressDao
import com.example.transpapptest.data.local.dao.CustomerInfoDao
import com.example.transpapptest.data.local.dao.DeliveryAddressDao
import com.example.transpapptest.data.local.dao.OrderDao
import com.example.transpapptest.data.local.dao.PickupAddressDao
import com.example.transpapptest.data.local.dao.ProducerInfoDao
import com.example.transpapptest.data.local.dao.TransporterDao
import com.example.transpapptest.data.local.dao.TransporterInfoDao
import com.example.transpapptest.data.local.dao.VehicleDao
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.remote.api.TransporterApi
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse
import com.example.transpapptest.domain.repository.TransporterRepository
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
class TransporterRepositoryImpl @Inject constructor(
    private val transporterApi: TransporterApi,
    private val transporterDao: TransporterDao,
    private val addressDao: AddressDao,
    private val vehicleDao: VehicleDao,
    private val orderDao: OrderDao,
    private val producerInfoDao: ProducerInfoDao,
    private val customerInfoDao: CustomerInfoDao,
    private val transporterInfoDao: TransporterInfoDao,
    private val deliveryAddressDao: DeliveryAddressDao,
    private val pickupAddressDao: PickupAddressDao,
    private val tokenManager: TokenManager
) : TransporterRepository {
    override fun loadTransporter(
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<MessageResponse>> = flow {
        Log.d("TransporterRepositoryImpl", "loadTransporter")
        val id = tokenManager.getUserId().first()

        id?.let { transporterId ->
            val localTransporter = transporterDao.getLocalTransporter(transporterId)
            val isDbEmpty = localTransporter == null
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                Log.d("TransporterRepositoryImpl", "Datos cargados de forma local")
                emit(ApiResponse.Success(MessageResponse("Datos cargados de forma local")))
                return@flow
            }

            val response: TransporterInfoResponse? = getResponse(transporterId)

            Log.d("JUEPUTA", "response = $response")


            response?.let { transporterInfoResponse ->
                transporterDao.insertTransporter(transporterInfoResponse.toTransporterEntity())
                transporterInfoResponse.addressList.forEach { addressResponse ->
                    addressDao.insertAddress(addressResponse.toAddressEntity())
                }
                transporterInfoResponse.vehicleList.forEach { vehicleResponse ->
                    vehicleDao.insertVehicle(vehicleResponse.toVehicleEntity())
                }
                transporterInfoResponse.orderInfoResponseList.forEach { orderInfoResponse ->
                    orderDao.insertOrder(orderInfoResponse.toOrderEntity())
                    producerInfoDao.insertProducerInfo(orderInfoResponse.toProducerInfoEntity())
                    customerInfoDao.insertCustomerInfo(orderInfoResponse.toCustomerInfoEntity())
                    transporterInfoDao.insertTransporterInfo(orderInfoResponse.toTransporterInfoEntity())
                    deliveryAddressDao.insertDeliveryAddress(orderInfoResponse.toDeliveryAddressEntity())
                    pickupAddressDao.insertPickupAddress(orderInfoResponse.toPickupAddressEntity())
                }


            }
            val transporters = transporterDao.getAllTransporters()
            Log.d("JUEPUTA", "transporters = $transporters")
        }
    }

    override fun getTransporter(transporterId: Long): Flow<TransporterEntity> {
        return transporterDao.getTransporter(transporterId)
    }

    override suspend fun getAll(): List<TransporterEntity> {
        return transporterDao.getAllTransporters()
    }

    private suspend fun FlowCollector<ApiResponse<MessageResponse>>.getResponse(
        id: Long
    ) = try {
        val response = transporterApi.getTransporter(id)
        if (response.isSuccessful) {
            emit(ApiResponse.Success(MessageResponse("Datos del transportador obtenidos con éxito")))
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