# **CodeBase Simpsons Sdk**

⚠️ WARNING: This is a development version and is not yet suitable for a production environment.

This is the Simpsons Code Base SDK, a library created to provide easy access to data about characters from the famous animated TV show "The Simpsons".

The SDK provides access to information about characters from both "The Simpsons" and "The Wire", allowing developers to easily incorporate character data into their applications.

# **Features**
Retrieves character data from the API and returns it in a format that is easy to work with
Handles various types of errors and provides informative error messages to help with debugging
Provides access to data about characters from both "The Simpsons" and "The Wire"

# **Requirements**
Kotlin 1.5.20 or higher
Gradle 7.0 or higher

# **Usage**
To use the Simpsons Code Base SDK, you will need to create an init of the Type of character in your Main Activity and use its getCharacters() method to retrieve character data.
Here's an example:


```kotlin

   // Implement in your Module: app 
    implementation project(":code-base-sdk")

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


