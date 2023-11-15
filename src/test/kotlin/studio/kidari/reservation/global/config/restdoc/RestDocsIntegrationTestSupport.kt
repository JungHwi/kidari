package studio.kidari.reservation.global.config.restdoc

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@Disabled
@Import(RestDocsConfig::class)
@ExtendWith(RestDocumentationExtension::class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RestDocsIntegrationTestSupport {
    @Autowired
    protected lateinit var restDocs: RestDocumentationResultHandler

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var mockMvc: MockMvc


    @PostConstruct
    private fun postConstruct() {
    }

    @PreDestroy
    private fun preDestroy() {

    }

    @BeforeEach
    fun setUp(
        context: WebApplicationContext,
        provider: RestDocumentationContextProvider
    ) {
        val configuration = MockMvcRestDocumentation.documentationConfiguration(provider)
            .operationPreprocessors()
            .withRequestDefaults(Preprocessors.prettyPrint())
            .withResponseDefaults(Preprocessors.prettyPrint())

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(configuration)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .alwaysDo<DefaultMockMvcBuilder>(restDocs)
            .addFilters<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
            .build()
    }

    private fun <T> anyObject(type: Class<T>): T {
        Mockito.any<T>()
        return null as T
    }
}