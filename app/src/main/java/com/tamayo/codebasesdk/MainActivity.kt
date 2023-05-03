package com.tamayo.codebasesdk

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.tamayo.code_base_sdk.BaseSdkImpl
import com.tamayo.code_base_sdk.utils.CharactersType
import com.tamayo.codebasesdk.databinding.ActivityMainBinding

/**
 * Represents the main activity of the application, extending the AppCompatActivity class.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Lazily initializes the binding for the main activity.
     */
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /**
     * Called when the activity is created, initializes the UI and the Base SDK.
     * @param savedInstanceState The saved instance state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /**
         *Initialize the Base SDK with the application context and the Simpsons character type
         */
        BaseSdkImpl.init(applicationContext, CharactersType.SIMPSONS.realValue)


    }
}