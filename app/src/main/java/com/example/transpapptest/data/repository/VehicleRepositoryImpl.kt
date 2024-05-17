package com.example.transpapptest.data.repository

import com.example.transpapptest.data.local.dao.VehicleDao
import com.example.transpapptest.data.local.entities.VehicleEntity
import com.example.transpapptest.data.remote.api.VehicleApi
import com.example.transpapptest.domain.repository.VehicleRepository
import com.example.transpapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager,
    private val vehicleApi: VehicleApi,
    private val vehicleDao: VehicleDao,
) : VehicleRepository { 

    /**
     * Si no hay nada en la base de datos intente obtener datos remotos
     * Si obtener de remoto es verdadero entonces obtener datos remotos
     * */
    override fun getAll(fetchFromRemote: Boolean): Flow<List<VehicleEntity>> {
        return flow {

            val transporterId = tokenManager.getUserId().first()
        }
    }
}