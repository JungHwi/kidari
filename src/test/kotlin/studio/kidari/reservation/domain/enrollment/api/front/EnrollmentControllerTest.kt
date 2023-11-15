package studio.kidari.reservation.domain.enrollment.api.front

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import studio.kidari.reservation.domain.enrollment.dto.CreateEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.enrollment.persistence.repository.EnrollmentRepository
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.repository.LectureRepository
import studio.kidari.reservation.global.config.restdoc.RestDocsIntegrationTestSupport
import studio.kidari.reservation.global.util.test.makeEnrollment
import studio.kidari.reservation.global.util.test.makeLecture

class EnrollmentControllerTest(
    private val enrollmentRepository: EnrollmentRepository,
    private val lectureRepository: LectureRepository
) : RestDocsIntegrationTestSupport() {

    @Test
    fun create() {
        val lecture = saveLecture()

        val request = CreateEnrollmentRequest(
            lectureId = lecture.id!!,
            memberId = "123"
        )

        val result: ResultActions = mockMvc
            .perform(
                post("/front/v1/enrollment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    fun getEnrolledLecture() {
        val lecture = saveLecture()
        val enrollment = saveEnrollment(lecture)

        val result: ResultActions = mockMvc
            .perform(
                get("/front/v1/enrollment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .queryParam("memberId", enrollment.memberId)
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    fun delete() {
        val lecture = saveLecture()

        val enrollment = saveEnrollment(lecture)

        val result: ResultActions = mockMvc
            .perform(
                delete("/front/v1/enrollment/{enrollmentId}", enrollment.id)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    private fun saveLecture(): Lecture {
        val lecture = makeLecture()
        return lectureRepository.save(lecture)
    }

    private fun saveEnrollment(lecture: Lecture): Enrollment {
        val enrollment = makeEnrollment(lecture)
        return enrollmentRepository.save(enrollment)
    }
}