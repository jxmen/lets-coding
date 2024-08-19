package dev.jxmen.spring_ai.controller

import org.slf4j.LoggerFactory
import org.springframework.ai.anthropic.AnthropicChatModel
import org.springframework.ai.chat.messages.UserMessage
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
    private val anthropicChatModel: AnthropicChatModel,
) {
    private val logger = LoggerFactory.getLogger(AiController::class.java)

    @GetMapping
    fun hello(): String = "Hello, World!"

    @GetMapping("/ai/anthropic/chat")
    fun chat(
        @RequestParam(
            value = "message",
            defaultValue = "농담 좀 해봐",
        ) message: String?,
    ): String = anthropicChatModel.call(message)

    @GetMapping("/ai/anthropic/chat/stream")
    fun generateStream(
        @RequestParam(
            value = "message",
            defaultValue = "농담 좀 해봐",
        ) message: String?,
    ): Flux<ChatResponse> {
        val prompt = Prompt(UserMessage(message))
        val answer = StringBuilder()

        return anthropicChatModel
            .stream(prompt)
            .doOnNext {
                it.results.forEach {
                    logger.info("Content: ${it.output.content}")
                    answer.append(it.output.content)
                }
            }.doOnComplete {
                logger.info("=== Stream completed")
                logger.info("answer: $answer")
            }
    }
}
