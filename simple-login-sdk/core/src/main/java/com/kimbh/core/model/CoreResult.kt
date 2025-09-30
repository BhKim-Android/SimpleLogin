package com.kimbh.core.model

sealed class CoreResult<out T> {
    data class onSuccess<T>(val data: T) : CoreResult<T>()
    data class onFail(val throwable: Throwable) : CoreResult<Nothing>()
}