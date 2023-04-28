package com.tamayo.code_base_sdk

import android.content.Context
import android.content.Intent
import com.tamayo.code_base_sdk.presentation.BaseActivity
import com.tamayo.code_base_sdk.utils.CharactersType

interface BaseSdk {
    fun init(context: Context, charactersType: String)
}

object BaseSdkImpl : BaseSdk{
    override fun init(context: Context, charactersType: String) {
       Intent(context, BaseActivity::class.java).apply {
           addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
           addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
           putExtra("CHARACTER_TYPE", charactersType)
           context.startActivity(this)
       }
    }
}