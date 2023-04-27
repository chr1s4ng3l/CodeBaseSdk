package com.tamayo.code_base_sdk.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tamayo.code_base_sdk.R
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.code_base_sdk.viewmodel.MainBaseViewModel

class BaseActivity : AppCompatActivity() {

    private val vm by lazy {
        ViewModelProvider(this)[MainBaseViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            vm.appType = intent.getSerializableExtra("CHARACTER_TYPE", CharactersType::class.java)
        }else{
            vm.appType = intent.getSerializableExtra("CHARACTER_TYPE") as CharactersType
        }

    }
}