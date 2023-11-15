package studio.kidari.reservation.domain.enrollment.service.dao

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import studio.kidari.reservation.domain.enrollment.dto.CreateEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.dto.SearchEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.enrollment.persistence.repository.EnrollmentRepository
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.service.dao.LectureDao
import studio.kidari.reservation.global.exception.BadRequestException

@Service
class EnrollmentDao(
    private val lectureDao: LectureDao,
    private val repository: EnrollmentRepository
) {

    @Transactional(readOnly = true)
    fun search(request: SearchEnrollmentRequest): Page<Enrollment> {
        return repository.search(request)
    }

    @Transactional(readOnly = true)
    fun getEnrolledLecture(request: SearchEnrollmentRequest): Page<Lecture> {
        return repository.getEnrolledLecture(request)
    }

    @Transactional
    fun create(request: CreateEnrollmentRequest): Enrollment {
        val lecture = lectureDao.get(request.lectureId)
        validateCreate(lecture, request)

        val enrollment = Enrollment(
            lecture = lecture,
            memberId = request.memberId
        )

        return repository.save(enrollment)
    }

    @Transactional
    fun delete(id: Long) {
        repository.deleteById(id)
    }

    private fun validateCreate(lecture: Lecture, request: CreateEnrollmentRequest) {
        if (request.memberId.length > 5) {
            throw BadRequestException("잘못된 사번입니다.")
        }

        val enrolledMemberCount = repository.countByLectureId(lecture.id!!)
        if (lecture.maxApplicant <= enrolledMemberCount) {
            throw BadRequestException("강연이 매진되었습니다.")
        }

    }
}