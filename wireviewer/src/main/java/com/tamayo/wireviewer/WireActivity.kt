package com.tamayo.wireviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamayo.code_base_sdk.BaseSdkImpl
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.wireviewer.databinding.ActivityWireBinding

class WireActivity : AppCompatActivity() {

    private val binding: ActivityWireBinding by lazy{
        ActivityWireBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        BaseSdkImpl.init(applicationContext, CharactersType.THE_WIRE.realValue)

    }
}