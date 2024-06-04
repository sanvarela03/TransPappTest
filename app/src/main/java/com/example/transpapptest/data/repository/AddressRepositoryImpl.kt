package com.example.transpapptest.data.repository

import android.util.Log
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.config.common.apiRequestFlow
import com.example.transpapptest.data.local.dao.CustomerDao
import com.example.transpapptest.data.local.dao.TransporterDao
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.remote.api.AddressApi
import com.example.transpapptest.data.remote.payload.request.AddressRequest
import com.example.transpapptest.data.remote.payload.request.UpdateAddressRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.customer.address.AddressResponse
import com.example.transpapptest.domain.repository.AddressRepository
import com.example.transpapptest.security.TokenManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRepositoryImpl @Inject constructor(
    private val api: AddressApi,
    private val dao: CustomerDao,
    private val transporterDao: TransporterDao,
    private val tokenManager: TokenManager
) : AddressRepository {
    override suspend fun addNewAddress(addressRequest: AddressRequest): Flow<ApiResponse<MessageResponse>> {
        return apiRequestFlow { api.addAddress(addressRequest) }
    }

    override suspend fun updateAddress(updateAddressRequest: UpdateAddressRequest): Flow<ApiResponse<MessageResponse>> {
        Log.d("AddressRepositoryImpl", "updateAddressRequest =$updateAddressRequest")
        val userId = tokenManager.getUserId().first()
        return apiRequestFlow {
            api.updateAddress(
                userId = userId ?: -1L,
                addressId = updateAddressRequest.id,
                updateAddressRequest = updateAddressRequest
            )
        }
    }

    override suspend fun deleteAddress(addressId: Long): Flow<ApiResponse<MessageResponse>> {
        val userId = tokenManager.getUserId().first()

        return apiRequestFlow {
            api.deleteAddress(
                userId = userId ?: -1L,
                addressId = addressId
            )
        }
    }

    override suspend fun getAllAddresses(
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<List<AddressEntity>>> {
        return flow {
            emit(ApiResponse.Loading)
            val userId = tokenManager.getUserId().first()
            userId?.let {
                val transporterWithAddress = transporterDao.getTransporterAndAddress(userId)

                Log.d("manzana", "customerWithAddress = $transporterWithAddress")

                if (transporterWithAddress.isNotEmpty()) {
                    val localAddresses = transporterWithAddress[0].addressList
                    emit(ApiResponse.Success(localAddresses))
                }

                val isDbEmpty = transporterWithAddress.isEmpty()

                val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

                if (shouldJustLoadFromCache) {
                    return@flow
                }



                val remote = getRemote(it)

                remote?.let { addresesResponse ->
                    dao.clearAddressEntity()
                    val remoteAddresses = addresesResponse

                    remoteAddresses.forEach {
                        Log.d("AddressRepositoryImpl", "getAllAddresses | insert : $it")
                        dao.insertAddress(it.toAddressEntity())
                    }

                    val addresses = dao.getAllAddresses()

                    Log.d("Addresses", "addresses $addresses")
                    val newCustomerWithAddress = dao.getCustomerAndAddress(userId)
                    Log.d("Addresses", "addresses $newCustomerWithAddress")
                    Log.d("Addresses", "customer and address : ${dao.getCustomerAndAddressTest()}")

                    newCustomerWithAddress.let {
                        if (newCustomerWithAddress.isNotEmpty()) {
                            val newLocalAddresses = newCustomerWithAddress[0].addressList
                            emit(ApiResponse.Success(newLocalAddresses))
                        }
                    }


                }
            }
        }
    }


    private suspend fun <T, E> FlowCollector<ApiResponse<E>>.testRemote(
        request: T,
        apiCall: (T) -> Response<E>
    ) = try {
        val res = apiCall(request)
        if (res.isSuccessful) {
            res.body()
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

    private suspend fun FlowCollector<ApiResponse<List<AddressEntity>>>.getRemote(
        it: Long
    ) = try {
        val response = api.getAllAddress(it)
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

    override suspend fun getAddressById(id: Long): AddressEntity? {
        return dao.getAddress(id)
    }

    override suspend fun getCurrentAddressId(producerId: Long): Long? {
        return dao.getCurrentAddressId(producerId)
    }
}