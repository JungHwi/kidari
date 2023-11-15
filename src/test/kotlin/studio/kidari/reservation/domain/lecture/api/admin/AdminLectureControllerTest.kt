package studio.kidari.reservation.domain.lecture.api.admin

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import studio.kidari.reservation.domain.lecture.dto.CreateLectureRequest
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.repository.LectureRepository
import studio.kidari.reservation.global.config.restdoc.RestDocsIntegrationTestSupport
import studio.kidari.reservation.global.util.test.makeLecture
import java.time.LocalDateTime

class AdminLectureControllerTest(
    private val lectureRepository: LectureRepository
) : RestDocsIntegrationTestSupport() {

    @Test
    fun search() {
        val lecture = saveLecture()

        val result: ResultActions = mockMvc
            .perform(
                get("/admin/lecture")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    fun create() {
        val request = CreateLectureRequest(
            lecturer = "생성 테스트 강연자",
            hall = "생성 테스트 강연장",
            maxApplicant = 100,
            description = "생성 테스트용 강연",
            startAt = LocalDateTime.now()
        )

        val result: ResultActions = mockMvc
            .perform(
                post("/admin/lecture")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    private fun saveLecture(): Lecture {
        val lecture = makeLecture()
        return lectureRepository.save(lecture)
    }
}