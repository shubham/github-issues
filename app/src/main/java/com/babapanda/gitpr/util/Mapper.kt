package com.babapanda.gitpr.util

import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.Response

class Mapper<T : Any, R : Any>(val transform: (T?) -> R?) : Function<Response<T>, Resource<R>> {
    @Suppress("ReturnCount")
    override fun apply(response: Response<T>): Resource<R> {
        val responseBody = response.body()
        return if (response.isSuccessful && response.body() != null) {
            Resource.Success(transform(responseBody))
        } else {
            handleApiError(response)
        }
    }

    private fun handleApiError(response: Response<T>): Resource<R> {
        val errorCode = response.code()
        return if (errorCode != 200) {
            Resource.Error(
                throwable = Throwable("Something went wrong"),
                message = "Something went wrong"
            )
        } else
            return Resource.Error(
                throwable = Throwable("API response incorrect"),
                message = "API giving error, please check"
            )
    }
}

fun <T : Any, R : Any> Single<Response<T>>.convertResponseToResult(transform: (T?) -> R?): Single<Resource<R>> {
    return this.map(Mapper(transform))
        .onErrorReturn {
            it.printStackTrace()
            return@onErrorReturn Resource.Error(throwable = it, message = "Something went wrong")
        }
}