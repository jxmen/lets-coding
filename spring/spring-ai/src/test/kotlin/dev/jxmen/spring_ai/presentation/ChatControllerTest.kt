package dev.jxmen.spring_ai.presentation

import dev.jxmen.spring_ai.domain.chat.ChatRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.model.Generation
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.client.MockMvcWebTestClient
import org.springframework.web.context.WebApplicationContext
import reactor.core.publisher.Flux

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ChatControllerTest(
    private val webApplicationContext: WebApplicationContext,
    private val chatRepository: ChatRepository
) {

    @MockBean
    lateinit var chatModel: ChatModel

    lateinit var webTestClient: WebTestClient

    @BeforeEach
    fun setUp() {
        /**
         * WebTestClient는 WebFlux를 사용하는 경우에 사용. mockMvc는 MockMvcWebTestClient를 사용
         *
         * https://stackoverflow.com/questions/60792015/springboot-test-fails-with-no-bean-named-webhandler-available
         */
        webTestClient = MockMvcWebTestClient.bindToApplicationContext(webApplicationContext).build()

        chatRepository.deleteAll()
    }

    @Test
    fun testChatStreamApi() {
        // given
        val answer = "Hello, World!"
        `when`(chatModel.stream(any<Prompt>())).thenReturn(
            Flux.just(
                ChatResponse(listOf(Generation(answer)))
            )
        )

        // when
        val returnResult = webTestClient.get()
            .uri("/ai/anthropic/chat/stream")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .returnResult()

        // then
        val responseBody = returnResult.responseBody
        assertThat(responseBody).isNotNull

        val chats = chatRepository.findAll()
        assertThat(chats).hasSize(1)
        assertThat(chats[0].id).isEqualTo(1)
        assertThat(chats[0].message).isEqualTo("농담 좀 해봐") // default value
        assertThat(chats[0].answer).isEqualTo(answer)
    }
}
