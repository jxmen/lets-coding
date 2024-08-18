package org.examples.kotlinjdsl.select

import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import jakarta.persistence.EntityManager
import org.examples.kotlinjdsl.entity.Chat
import org.examples.kotlinjdsl.entity.Member
import org.examples.kotlinjdsl.entity.Subject
import org.examples.kotlinjdsl.entity.SubjectCategory
import org.examples.kotlinjdsl.repository.ChatRepository
import org.examples.kotlinjdsl.repository.MemberRepository
import org.examples.kotlinjdsl.repository.SubjectRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SelectExample(
    private val chatRepository: ChatRepository,
    private val memberRepository: MemberRepository,
    private val subjectRepository: SubjectRepository,
    private val entityManger: EntityManager,
) {
    val context = JpqlRenderContext()

    lateinit var subject1: Subject
    lateinit var subject2: Subject
    lateinit var subject3: Subject
    lateinit var member: Member

    @BeforeEach
    fun setUp() {
        member = memberRepository.save(Member(name = "name", email = "email"))

        subject1 = subjectRepository.save(Subject(title = "title1", question = "question1"))
        subject2 = subjectRepository.save(Subject(title = "title2", question = "question2"))
        subject3 = subjectRepository.save(Subject(title = "title3", question = "question3"))

        chatRepository.saveAll(
            listOf(
                Chat(subject = subject1, member = member, message = "message1", score = 10),
                Chat(subject = subject1, member = member, message = "message2", score = 20),
                Chat(subject = subject1, member = member, message = "message3", score = 30),
                Chat(subject = subject2, member = member, message = "message4", score = 40),
                Chat(subject = subject2, member = member, message = "message4", score = 50),
                Chat(subject = subject2, member = member, message = "message4", score = 60),
            ),
        )
    }

    @Test
    fun memberChats() {
        val chats =
            chatRepository.findAll {
                select(
                    path(Chat::score),
                ).from(
                    entity(Chat::class),
                ).where(
                    path(Chat::member)
                        .eq(member)
                        .and(path(Chat::subject).eq(subject1)),
                )
            }

        assert(chats.size == 3)
    }

    @Test
    fun `채팅 전체 내역 조회`() {
        val maxScoreChats =
            chatRepository.findAll {
                selectNew<MemberChatMaxScoreInfo>(
                    path(Chat::subject).path(Subject::id),
                    path(Chat::subject).path(Subject::title),
                    path(Chat::subject).path(Subject::question),
                    max(path(Chat::score)),
                ).from(
                    entity(Chat::class),
                    leftJoin(Chat::subject),
                ).where(
                    path(Chat::member).eq(member),
                ).groupBy(
                    path(Chat::subject),
                ).orderBy(
                    path(Subject::id).asc(),
                )
            }

        maxScoreChats.forEach { println(it) }

        assert(maxScoreChats.size == 2)
        assert(maxScoreChats[0]?.maxScore == 30)
        assert(maxScoreChats[1]?.maxScore == 60)
    }

    @Test
    fun `본인 최대 점수 포함한 전체 주제 조회`() {
        val query =
            jpql {
                selectNew<SubjectDTO>(
                    path(Subject::id),
                    path(Subject::title),
                    path(Subject::category),
                    max(path(Chat::score)),
                ).from(
                    entity(Subject::class),
                    leftJoin(Chat::class).on(
                        path(Subject::id).eq(path(Chat::subject).path(Subject::id)),
                    ),
                ).where(
                    path(Chat::member).eq(member),
                ).groupBy(
                    path(Subject::id),
                )
            }
        val renderer = JpqlRenderer()

        val rendered = renderer.render(query, context)
        val resultList = entityManger
            .createQuery(rendered.query)
            .apply {
                rendered.params.forEach { (name, value) ->
                    setParameter(name, value)
                }
            }.resultList as List<SubjectDTO>

        assert(resultList.size == 2)
        assert(resultList[0].title == "title1")
        assert(resultList[0].maxScore == 30)
        assert(resultList[1].title == "title2")
        assert(resultList[1].maxScore == 60)
    }
}

data class MemberChatMaxScoreInfo(
    val id: Long,
    val title: String,
    val question: String,
    val maxScore: Int,
)

data class SubjectDTO(
    val id: Long,
    val title: String,
    val category: SubjectCategory,
    val maxScore: Int?,
)
