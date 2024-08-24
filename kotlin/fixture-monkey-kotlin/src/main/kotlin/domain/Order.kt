package org.example.domain

import java.time.Instant

data class Order (
    val id: Long,

    val orderNo: String,

    val productName: String,

    val quantity: Int,

    val price: Long,

    val items: List<String>,

    val orderedAt: Instant
)
