package studio.kidari.reservation.domain.enrollment.api.admin

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.enrollment.persistence.repository.EnrollmentRepository
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.repository.LectureRepository
import studio.kidari.reservation.global.config.restdoc.RestDocsIntegrationTestSupport
import studio.kidari.reservation.global.util.test.makeEnrollment
import studio.kidari.reservation.global.util.test.makeLecture

class AdminEnrollmentControllerTest(
    private val enrollmentRepository: EnrollmentRepository,
    private val lectureRepository: LectureRepository
) : RestDocsIntegrationTestSupport() {

    @Test
    fun search() {
        val lecture = saveLecture()
        saveEnrollment(lecture)

        val result: ResultActions = mockMvc
            .perform(
                get("/admin/enrollment")
                    .queryParam("lectureId", lecture.id.toString())
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