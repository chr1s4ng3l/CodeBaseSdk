package com.tamayo.code_base_sdk.utils

/**
 * Represents the various states of a UI action that involves a result of type T.
 * @param T the type of data returned upon successful completion of the action
 */
sealed class UIState<out T> {

    /**
     * Represents the loading state of the UI action.
     */
    object LOADING : UIState<Nothing>()

    /**
     * Represents the successful state of the UI action.
     * @param data the data returned upon successful completion of the action
     */
    data class SUCCESS<T>(val data: T) : UIState<T>()

    /**
     * Represents the error state of the UI action.
     * @param ERROR the error that occurred during the action
     */
    data class ERROR(val ERROR: Exception) : UIState<Nothing>()
}
