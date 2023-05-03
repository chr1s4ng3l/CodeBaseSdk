package com.tamayo.wireviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tamayo.code_base_sdk.utils.BaseSdkImpl
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.wireviewer.databinding.ActivityWireBinding

/**
 * Represents the main activity of the application, extending the AppCompatActivity class.
 */
class WireActivity : AppCompatActivity() {

    /**
     * Lazily initializes the binding for the main activity.
     */
    private val binding: ActivityWireBinding by lazy{
        ActivityWireBinding.inflate(layoutInflater)
    }

    /**
     * Called when the activity is created, initializes the UI and the Base SDK.
     * @param savedInstanceState The saved instance state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         *Initialize the Base SDK with the application context and the Wire character type
         */
        BaseSdkImpl.init(applicationContext, CharactersType.THE_WIRE.realValue)

    }
}