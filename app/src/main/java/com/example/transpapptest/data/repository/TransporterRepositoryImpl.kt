package com.example.transpapptest.data.repository

import android.util.Log
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.config.common.apiRequestFlow
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
        transporterId: Long,
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<TransporterInfoResponse>> = flow {
        Log.d("TransporterRepositoryImpl", "loadTransporter")

        val localTransporter: TransporterEntity? = transporterDao.getLocalTransporter(transporterId)
        val isDbEmpty = localTransporter == null
        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

        if (shouldJustLoadFromCache) {

            emit(ApiResponse.Success(localTransporter!!.toTransporterInfoResponse()))
            return@flow
        }

        val responseTest = apiRequestFlow { transporterApi.getTransporter(transporterId) }
        responseTest.collect { res ->
            when (res) {
                ApiResponse.Waiting -> {}
                ApiResponse.Loading -> {}

                is ApiResponse.Failure -> {
                    emit(res)
                }

                is ApiResponse.Success -> {
                    transporterDao.insertTransporter(res.data.toTransporterEntity())
                    addressDao.insertAllAddresses(res.data.addressList.map { it.toAddressEntity() })
                    vehicleDao.insertAllVehicles(res.data.vehicleList.map { it.toVehicleEntity() })
                    res.data.orderInfoResponseList.forEach { order ->
                        orderDao.insertOrder(order.toOrderEntity())
                        producerInfoDao.insertProducerInfo(order.toProducerInfoEntity())
                        customerInfoDao.insertCustomerInfo(order.toCustomerInfoEntity())
                        transporterInfoDao.insertTransporterInfo(order.toTransporterInfoEntity())
                        deliveryAddressDao.insertDeliveryAddress(order.toDeliveryAddressEntity())
                        pickupAddressDao.insertPickupAddress(order.toPickupAddressEntity())
                    }
                    emit(res)
                }
            }
        }
    }

    override fun getTransporter(transporterId: Long): Flow<TransporterEntity> {
        return transporterDao.getTransporter(transporterId)
    }

    override suspend fun getAll(): List<TransporterEntity> {
        return transporterDao.getAllTransporters()
    }

    override suspend fun updateAvailability(availability: Boolean): Flow<ApiResponse<MessageResponse>> {
        val transporterId = tokenManager.getUserId().first()
        return apiRequestFlow {
            transporterApi.updateAvailability(
                transporterId ?: -1,
                availability
            )
        }
    }

    override suspend fun updateLocalTransporter(transporter: TransporterEntity) {
        transporterDao.insertTransporter(transporter)
    }

    override suspend fun clearLocalTransporter() {
        addressDao.clearAddressEntity()
        vehicleDao.clearVehicleEntity()
        orderDao.clearOrderEntity()
        transporterDao.clearTransporterEntity()
    }

}