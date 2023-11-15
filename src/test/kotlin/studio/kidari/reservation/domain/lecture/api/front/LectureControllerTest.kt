package studio.kidari.reservation.domain.lecture.api.front

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

class LectureControllerTest(
    private val lectureRepository: LectureRepository,
    private val enrollmentRepository: EnrollmentRepository
) : RestDocsIntegrationTestSupport() {

    @Test
    fun search() {
        saveLecture()

        val result: ResultActions = mockMvc
            .perform(
                get("/front/v1/lecture")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk)
            .andDo(print())
    }

    @Test
    fun searchHottest() {
        val lecture = saveLecture()
        saveEnrollment(lecture)

        val result: ResultActions = mockMvc
            .perform(
                get("/front/v1/lecture/hottest")
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