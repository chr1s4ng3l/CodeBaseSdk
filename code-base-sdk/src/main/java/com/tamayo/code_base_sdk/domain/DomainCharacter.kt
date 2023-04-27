package com.tamayo.code_base_sdk.domain

import com.tamayo.code_base_sdk.model.RelatedTopic
import com.tamayo.code_base_sdk.rest.BaseService

data class DomainCharacter(
    val icon: String ? = null,
    val description: String,
    val name: String
)

fun List<RelatedTopic>?.mapToDomain(): List<DomainCharacter> {
    return this?.map {
        val items = it.text?.split("-")
        DomainCharacter(
            name = items?.get(0) ?: "",
            description = items?.get(1) ?: "",
            icon = it.icon?.uRL?.run { "${BaseService.IMAGE_BASE_URL}${this}" }
        )
    } ?: emptyList()

}
