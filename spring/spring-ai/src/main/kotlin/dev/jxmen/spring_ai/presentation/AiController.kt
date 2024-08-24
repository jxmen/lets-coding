package dev.jxmen.spring_ai.presentation

import dev.jxmen.spring_ai.domain.chat.ChatRepository
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
class AiController(
    private val chatModel: ChatModel,
    private val chatRepository: ChatRepository,
) {
    private val logger = LoggerFactory.getLogger(AiController::class.java)

    @GetMapping
    fun hello(): String = "Hello, World!"

    @GetMapping("/ai/anthropic/chat")
    fun chat(
        @RequestParam(
            value = "message",
            defaultValue = "농담 좀 해봐",
        ) message: String,
    ): String {
        val answer = chatModel.call(message)
        chatRepository.save(message, answer)

        return answer
    }

    @GetMapping("/ai/anthropic/chat/stream")
    fun generateStream(
        @RequestParam(
            value = "message",
            defaultValue = "농담 좀 해봐",
        ) message: String,
    ): Flux<ChatResponse> {
        val prompt = Prompt(UserMessage(message))
        val answer = StringBuilder()

        return chatModel
            .stream(prompt)
            .doOnNext {
                it.results.forEach {
                    answer.append(it.output.content)
                }
            }.doOnComplete {
                logger.info("=== Stream completed")
                val created = chatRepository.save(message, answer.toString())
                logger.info("=== Answer saved: ${created.answer}")
            }
    }
}
