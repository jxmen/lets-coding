package dev.jxmen.spring_ai.domain.answer

import java.time.LocalDateTime

data class Answer(
    val id: Long,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
