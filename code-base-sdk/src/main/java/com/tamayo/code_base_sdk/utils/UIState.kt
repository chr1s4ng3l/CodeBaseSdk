package com.tamayo.code_base_sdk.utils

sealed class UIState<out T>{
    object LOADING : UIState<Nothing>()
    data class SUCCESS<T>(val data: T): UIState<T>()
    data class ERROR(val ERROR: Exception): UIState<Nothing>()
}
