package com.tamayo.codebasesdk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tamayo.code_base_sdk.BaseSdkImpl
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.codebasesdk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        BaseSdkImpl.init(applicationContext, CharactersType.SIMPSONS.realValue)
        this.supportActionBar?.title = "Simpson Characters"

    }
}