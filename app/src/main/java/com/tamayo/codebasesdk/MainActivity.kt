package com.tamayo.codebasesdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamayo.code_base_sdk.BaseSdk
import com.tamayo.code_base_sdk.BaseSdkImpl
import com.tamayo.code_base_sdk.utils.CharactersType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseSdkImpl.init(applicationContext, CharactersType.SIMPSONS)
    }
}