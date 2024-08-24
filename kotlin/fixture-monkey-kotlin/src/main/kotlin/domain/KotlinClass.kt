package org.example.domain

data class KotlinClass(
    val field: String,

    val list: List<String>,

    val nestedObject: Nested,

    val nestedObjectList: List<Nested>
) {
    data class Nested(
        val nestedField: String
    )
}
