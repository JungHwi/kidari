package studio.kidari.reservation.domain.enrollment.persistence.repository

import org.springframework.stereotype.Repository
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.enrollment.persistence.repository.custom.EnrollmentCustomRepository
import studio.kidari.reservation.global.config.jpa.DefaultJpaRepository

@Repository
interface EnrollmentRepository : DefaultJpaRepository<Enrollment, Long>, EnrollmentCustomRepository {

    fun countByLectureId(lectureId: Long): Int
}