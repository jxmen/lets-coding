package org.examples.kotlinjdsl.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.annotations.Comment

/**
 * 주제
 */
@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["title", "question"]),
    ],
)
class Subject(
    @Column(nullable = false, unique = true)
    @Comment("제목")
    val title: String,
    @Column(nullable = false, unique = true)
    @Comment("질문")
    val question: String,
    @Enumerated(EnumType.STRING)
    @Comment("카테고리")
    val category: SubjectCategory,
    @OneToMany(mappedBy = "subject")
    val chats: MutableSet<Chat> = mutableSetOf(),
) : BaseEntity() {
    constructor(
        title: String,
        question: String,
    ) : this(title, question, SubjectCategory.OS)
}
