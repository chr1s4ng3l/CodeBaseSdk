package com.tamayo.wireviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamayo.code_base_sdk.BaseSdkImpl
import com.tamayo.code_base_sdk.utils.CharactersType

class WireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wire)
        BaseSdkImpl.init(applicationContext, CharactersType.THE_WIRE)

    }
}