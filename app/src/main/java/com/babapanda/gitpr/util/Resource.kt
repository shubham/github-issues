package com.babapanda.gitpr.util

sealed class Resource<T>(val isSuccess: Boolean, val message: String? = null) {
    class Success<T>(val data: T?, message: String? = null) : Resource<T>(
        true,
        message = message
    )

    class Error<T>(val throwable: Throwable? = null, message: String? = null) : Resource<T>(
        false,
        message = message
    )
}
