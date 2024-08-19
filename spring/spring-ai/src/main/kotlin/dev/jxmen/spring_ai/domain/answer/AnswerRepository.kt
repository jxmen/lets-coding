package dev.jxmen.spring_ai.domain.answer

interface AnswerRepository {
    fun save(message: String): Answer

    fun findAll(): List<Answer>
}
