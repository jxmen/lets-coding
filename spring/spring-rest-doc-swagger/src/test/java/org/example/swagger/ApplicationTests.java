package org.example.swagger;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class ApplicationTests {
	private final MockMvc mockMvc;

	public ApplicationTests(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("hello"))
				.andDo(
						MockMvcRestDocumentationWrapper.document("get-advertisement-transmission",
								responseFields(
										fieldWithPath("message").description("it must be hello")
								)
						)
				);
	}

}
