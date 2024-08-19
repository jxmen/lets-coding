package dev.jxmen.spring_ai.persistance

import dev.jxmen.spring_ai.domain.answer.Answer
import dev.jxmen.spring_ai.domain.answer.AnswerRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong

@Repository
class InMemoryAnswerRepository : AnswerRepository {
    private val answers = mutableListOf<Answer>()

    private var id = AtomicLong(1)

    override fun save(message: String): Answer {
        val answer = Answer(id.getAndIncrement(), message)
        answers.add(answer)

        return answer
    }

    override fun findAll(): List<Answer> {
        return answers
    }
}

