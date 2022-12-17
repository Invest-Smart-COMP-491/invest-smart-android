package com.comp491.investsmart.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Result<T>(data = data)
    class SuccessWithoutBody<T>() : Result<T>()

    class Error<T>(errorMessage: String) : Result<T>(message = errorMessage)
}

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                if(response.body() == null){
                    Result.SuccessWithoutBody()
                }else{
                    Result.Success(data = response.body()!!)
                }
            }else {
                Result.Error("Something went wrong")
            }

        } catch (e: HttpException) {
            Result.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            Result.Error("Please check your network connection")
        } catch (e: Exception) {
            Result.Error(errorMessage = "Something went wrong")
        }
    }
}
