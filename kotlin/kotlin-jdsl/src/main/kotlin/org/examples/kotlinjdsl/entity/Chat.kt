package org.examples.kotlinjdsl.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
class Chat(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @Comment("주제")
    val subject: Subject,
    @ManyToOne(fetch = FetchType.LAZY, optional = true) // NOTE: sessionId 컬럼 제거 시 nullable 제거
    @JoinColumn(name = "member_id")
    @Comment("멤버 아이디")
    val member: Member? = null,
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    @Comment("채팅 내용")
    val message: String,
    @Column(nullable = true)
    @Comment("점수")
    val score: Int? = null,
) : BaseEntity() {
    init {
        subject.chats.add(this)
    }
}
