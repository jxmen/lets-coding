package dev.jxmen.spring_ai.presentation

import dev.jxmen.spring_ai.domain.answer.Answer
import dev.jxmen.spring_ai.domain.answer.AnswerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AnswerController(
    private val answerRepository: AnswerRepository,
) {
    @GetMapping("/answers")
    fun getAnswers(): List<Answer> = answerRepository.findAll()
}
