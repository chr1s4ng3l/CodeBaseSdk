package com.tamayo.code_base_sdk.domain

import com.tamayo.code_base_sdk.data.model.RelatedTopic
import com.tamayo.code_base_sdk.data.rest.BaseService


/**
 * Data class representing a character in the domain layer of the application.
 * @property icon The icon of the character.
 * @property description The description of the character.
 * @property name The name of the character.
 */

data class DomainCharacter(
    val icon: String ? = null,
    val description: String,
    val name: String
)

/**
 * Extension function on a nullable list of [RelatedTopic] objects that maps each [RelatedTopic] object to a [DomainCharacter] object.
 * @return A list of [DomainCharacter] objects.
 */
fun List<RelatedTopic>?.mapToDomain(): List<DomainCharacter> {
    return this?.map {
        val items = it.text?.split("-") ?: emptyList()
        DomainCharacter(
            name = if (items.isNotEmpty()) items[0] else  "Empty name",
            description = it.text ?: "No description",
            icon = it.icon?.uRL?.run { "${BaseService.IMAGE_BASE_URL}${this}" }
        )
    } ?: emptyList()

}
