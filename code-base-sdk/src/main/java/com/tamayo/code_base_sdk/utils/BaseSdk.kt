package com.tamayo.code_base_sdk.utils

import android.content.Context
import android.content.Intent
import com.tamayo.code_base_sdk.presentation.ui.BaseActivity

/**
 * Interface representing the Base SDK.
 */
interface BaseSdk {

    /**
     * Initializes the Base SDK with the given context and character type.
     * @param context The context to initialize the SDK with.
     * @param charactersType The type of characters to initialize the SDK with.
     */
    fun init(context: Context, charactersType: String)
}

/**
 * Implementation of the BaseSdk interface.
 */
object BaseSdkImpl : BaseSdk {

    /**
     * Initializes the Base SDK with the given context and character type.
     * @param context The context to initialize the SDK with.
     * @param charactersType The type of characters to initialize the SDK with.
     */
    override fun init(context: Context, charactersType: String) {
       Intent(context, BaseActivity::class.java).apply {
           addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
           addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
           putExtra("CHARACTER_TYPE", charactersType)
           context.startActivity(this)
       }
    }
}