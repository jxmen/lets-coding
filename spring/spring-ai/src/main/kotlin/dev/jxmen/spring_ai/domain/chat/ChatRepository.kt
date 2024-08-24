package dev.jxmen.spring_ai.domain.chat

interface ChatRepository {
    fun save(message: String, answer: String): Chat

    fun findAll(): List<Chat>
    fun deleteAll()
}
