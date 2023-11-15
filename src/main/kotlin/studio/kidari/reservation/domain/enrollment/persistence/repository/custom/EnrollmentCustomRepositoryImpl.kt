package studio.kidari.reservation.domain.enrollment.persistence.repository.custom

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import studio.kidari.reservation.domain.enrollment.dto.SearchEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.enrollment.persistence.domain.QEnrollment.enrollment
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.domain.QLecture.lecture

class EnrollmentCustomRepositoryImpl(
    private val factory: JPAQueryFactory
) : EnrollmentCustomRepository {

    override fun search(request: SearchEnrollmentRequest): Page<Enrollment> {
        val list = searchList(request)
            .select(enrollment)
            .fetch()

        val count = whereCondition(request)
            .select(enrollment.count())
            .fetchOne() ?: 0L

        return PageImpl(list, request.pageable, count)
    }

    override fun getEnrolledLecture(request: SearchEnrollmentRequest): Page<Lecture> {
        val list = getEnrolledLectureList(request)
            .select(lecture)
            .fetch()

        val count = enrolledLectureWhere(request)
            .select(lecture.count())
            .fetchOne() ?: 0L

        return PageImpl(list, request.pageable, count)
    }

    private fun searchList(request: SearchEnrollmentRequest): JPAQuery<*> {
        return whereCondition(request)
            .offset(request.pageable.offset)
            .limit(request.pageable.pageSize.toLong())
    }

    private fun whereCondition(request: SearchEnrollmentRequest): JPAQuery<*> {
        return factory
            .from(enrollment)
            .where(
                eqLectureId(request.lectureId),
                eqMemberId(request.memberId)
            )
    }

    private fun getEnrolledLectureList(request: SearchEnrollmentRequest): JPAQuery<*> {
        return whereCondition(request)
            .offset(request.pageable.offset)
            .limit(request.pageable.pageSize.toLong())
    }

    private fun enrolledLectureWhere(request: SearchEnrollmentRequest): JPAQuery<*> {
        return factory
            .from(enrollment)
            .where(
                eqMemberId(request.memberId)
            )
    }

    private fun eqLectureId(lectureId: Long?): BooleanExpression? {
        return lectureId?.let { enrollment.lecture.id.eq(lectureId) }
    }

    private fun eqMemberId(memberId: String?): BooleanExpression? {
        return memberId?.let { enrollment.memberId.eq(memberId) }
    }
}