package org.examples.kotlinjdsl.repository

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.examples.kotlinjdsl.entity.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<Subject, Long>, KotlinJdslJpqlExecutor{
}
