package studio.kidari.reservation.domain.enrollment.persistence.repository.custom

import org.springframework.data.domain.Page
import studio.kidari.reservation.domain.enrollment.dto.SearchEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture

interface EnrollmentCustomRepository {

    fun search(request: SearchEnrollmentRequest): Page<Enrollment>
    fun getEnrolledLecture(request: SearchEnrollmentRequest): Page<Lecture>
}