package org.examples.kotlinjdsl.repository

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.examples.kotlinjdsl.entity.Chat
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository: JpaRepository<Chat, Long>, KotlinJdslJpqlExecutor{
}
