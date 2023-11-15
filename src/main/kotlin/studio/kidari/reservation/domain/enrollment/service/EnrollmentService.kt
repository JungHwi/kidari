package studio.kidari.reservation.domain.enrollment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import studio.kidari.reservation.domain.enrollment.converter.EnrollmentConverter
import studio.kidari.reservation.domain.enrollment.dto.CreateEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.dto.EnrollmentResponse
import studio.kidari.reservation.domain.enrollment.dto.SearchEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.enrollment.service.dao.EnrollmentDao
import studio.kidari.reservation.domain.lecture.converter.LectureConverter
import studio.kidari.reservation.domain.lecture.dto.LectureResponse
import studio.kidari.reservation.global.wrapper.PageResponse

@Service
class EnrollmentService(
    private val dao: EnrollmentDao,
    private val converts: EnrollmentConverter,
    private val lectureConverts: LectureConverter
) {

    @Transactional(readOnly = true)
    fun search(request: SearchEnrollmentRequest): PageResponse<String> {
        val applicantPage = dao.search(request)
        val content = applicantPage.content.map(Enrollment::memberId)

        return PageResponse(applicantPage.totalElements, content)
    }

    @Transactional(readOnly = true)
    fun getEnrolledLecture(request: SearchEnrollmentRequest): PageResponse<LectureResponse> {
        val enrolledLecturePage = dao.getEnrolledLecture(request)
        val content = lectureConverts.converts(enrolledLecturePage.content)

        return PageResponse(enrolledLecturePage.totalElements, content)
    }

    @Transactional
    fun create(request: CreateEnrollmentRequest): EnrollmentResponse {
        val enrollment = dao.create(request)

        return converts.converts(enrollment)
    }

    @Transactional
    fun delete(id: Long) {
        return dao.delete(id)
    }
}