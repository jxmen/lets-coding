package org.examples.kotlinjdsl.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.Comment

@Suppress("ktlint:standard:no-blank-line-in-list")
@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["email"])],
)
class Member(

    @Id
    @GeneratedValue
    @Comment("아이디")
    val id: Long? = null,

    @Column(nullable = false)
    @Comment("이름")
    val name: String,

    @Column(nullable = false, unique = true)
    @Comment("이메일")
    val email: String,
)
