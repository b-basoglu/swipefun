package com.bbasoglu.swipefun.network

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T?) : NetworkResponse<T>()
    data class Error<T>(val message: String?, val data: T? = null,val code:Int?) : NetworkResponse<T>()
    class Loading<T> : NetworkResponse<T>()
}
