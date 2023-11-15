package studio.kidari.reservation.domain.lecture.persistence.repository.custom

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import studio.kidari.reservation.domain.enrollment.persistence.domain.QEnrollment.enrollment
import studio.kidari.reservation.domain.lecture.dto.SearchHottestRequest
import studio.kidari.reservation.domain.lecture.dto.SearchLectureRequest
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.domain.QLecture.lecture
import java.time.LocalDateTime

class LectureCustomRepositoryImpl(
    private val factory: JPAQueryFactory
) : LectureCustomRepository {

    override fun search(request: SearchLectureRequest): Page<Lecture> {
        val list = searchList(request)
            .select(lecture)
            .fetch()

        val count = whereCondition(request)
            .select(lecture.count())
            .fetchOne() ?: 0L

        return PageImpl(list, request.pageable, count)
    }

    override fun searchHottest(request: SearchHottestRequest): Page<Lecture> {
        val list = searchHottestList(request)
            .select(lecture)
            .fetch()

        val count = hottestWhereCondition(request)
            .select(lecture.count())
            .fetchOne() ?: 0L

        return PageImpl(list, request.pageable, count)
    }

    private fun searchList(request: SearchLectureRequest): JPAQuery<*> {
        return whereCondition(request)
            .offset(request.pageable.offset)
            .limit(request.pageable.pageSize.toLong())
    }

    private fun searchHottestList(request: SearchHottestRequest): JPAQuery<*> {
        return hottestWhereCondition(request)
            .groupBy(lecture.id)
            .orderBy(lecture.id.count().desc())
            .offset(request.pageable.offset)
            .limit(request.pageable.pageSize.toLong())
    }

    private fun whereCondition(request: SearchLectureRequest): JPAQuery<*> {
        return factory
            .from(lecture)
            .where(
                gtStartAt(request.searchStartAt),
                ltStartAt(request.searchEndAt)
            )
    }

    private fun hottestWhereCondition(request: SearchHottestRequest): JPAQuery<*> {
        return factory
            .from(lecture)
            .leftJoin(enrollment).on(lecture.id.eq(enrollment.lecture.id))
            .where(
                gtEnrollmentCreateAt(request.searchStartAt)
            )
    }

    private fun gtStartAt(searchStartAt: LocalDateTime?): BooleanExpression? {
        return searchStartAt?.let { lecture.startAt.gt(searchStartAt) }
    }

    private fun ltStartAt(searchEndAt: LocalDateTime?): BooleanExpression? {
        return searchEndAt?.let { lecture.startAt.lt(searchEndAt) }
    }

    private fun gtEnrollmentCreateAt(searchStartAt: LocalDateTime?): BooleanExpression? {
        return searchStartAt?.let { enrollment.createdAt.gt(searchStartAt) }
    }
}