package com.example.transpapptest.data.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.config.common.apiRequestFlow
import com.example.transpapptest.data.local.dao.VehicleDao
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.data.remote.api.VehicleApi
import com.example.transpapptest.data.remote.payload.request.VehicleRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.VehicleRepository
import com.example.transpapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager,
    private val vehicleApi: VehicleApi,
    private val vehicleDao: VehicleDao,
) : VehicleRepository {

    override fun getAll(fetchFromRemote: Boolean): Flow<ApiResponse<List<VehicleEntity>>> {
        return flow {
            emit(ApiResponse.Loading)
            val transporterId = tokenManager.getUserId().first()
            transporterId?.let {
                val vehicles = vehicleDao.getAllVehicles(transporterId)

                if (!vehicles.isNullOrEmpty()) {
                    emit(ApiResponse.Success(vehicles))
                }

                val isDBEmpty = vehicles.isNullOrEmpty()
                val loadFromLocal = !isDBEmpty && !fetchFromRemote

                if (loadFromLocal) {
                    return@flow
                }

                val remoteTest = apiRequestFlow { vehicleApi.getAll(transporterId) }

                remoteTest.collect { res ->
                    when (res) {
                        ApiResponse.Loading -> {}
                        ApiResponse.Waiting -> {}
                        is ApiResponse.Failure -> {
                            emit(res)
                        }

                        is ApiResponse.Success -> {
                            vehicleDao.clearVehicleEntity()
                            val remoteVehicles = res.data
                            val vehicleListEntity = remoteVehicles.map { it.toVehicleEntity() }
                            vehicleDao.insertAllVehicles(vehicleListEntity)
                            val localVehicles = vehicleDao.getAllVehicles(transporterId)
                            if (localVehicles != null) {
                                emit(ApiResponse.Success(localVehicles))
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun get(vehicleId: Long): VehicleEntity? {
        val vehicle = vehicleDao.getVehicle(vehicleId)
        return vehicle
    }

    override suspend fun add(vehicleRequest: VehicleRequest): Flow<ApiResponse<MessageResponse>> {
        val transporterId = tokenManager.getUserId().first()
        return apiRequestFlow { vehicleApi.add(transporterId ?: -1L, vehicleRequest) }
    }
}