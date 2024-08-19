package dev.jxmen.spring_ai.domain.chat

import java.time.LocalDateTime

data class Chat(
    val id: Long,
    val message: String,
    val answer: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
