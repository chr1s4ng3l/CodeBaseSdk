package com.tamayo.code_base_sdk.utils

/**
 * This is the class that will drive the type of data needed
 *
 * If we require to support more items,
 * we can add the new ENUMS to this class
 */
enum class CharactersType(val realValue: String) {
    SIMPSONS(realValue = "simpsons characters"),
    THE_WIRE(realValue = "the wire characters")

}