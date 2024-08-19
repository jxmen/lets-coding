package dev.jxmen.spring_ai.presentation

import dev.jxmen.spring_ai.domain.chat.Chat
import dev.jxmen.spring_ai.domain.chat.ChatRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private val chatRepository: ChatRepository,
) {
    @GetMapping("/chats")
    fun getChats(): List<Chat> = chatRepository.findAll()
}
