package dev.jxmen.spring_ai.persistance

import dev.jxmen.spring_ai.domain.chat.Chat
import dev.jxmen.spring_ai.domain.chat.ChatRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong

@Repository
class InMemoryChatRepository : ChatRepository {
    private val chats = mutableListOf<Chat>()

    private var id = AtomicLong(1)

    override fun save(message: String, answer: String): Chat {
        val chat = Chat(id.getAndIncrement(), message, answer)
        chats.add(chat)

        return chat
    }

    override fun findAll(): List<Chat> {
        return chats
    }

    override fun deleteAll() {
        chats.clear()
    }
}

