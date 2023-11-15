package studio.kidari.reservation.domain.lecture.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import studio.kidari.reservation.domain.lecture.converter.LectureConverter
import studio.kidari.reservation.domain.lecture.dto.CreateLectureRequest
import studio.kidari.reservation.domain.lecture.dto.LectureResponse
import studio.kidari.reservation.domain.lecture.dto.SearchHottestRequest
import studio.kidari.reservation.domain.lecture.dto.SearchLectureRequest
import studio.kidari.reservation.domain.lecture.service.dao.LectureDao
import studio.kidari.reservation.global.wrapper.PageResponse

@Service
class LectureService(
    private val dao: LectureDao,
    private val converter: LectureConverter
) {

    @Transactional(readOnly = true)
    fun search(request: SearchLectureRequest): PageResponse<LectureResponse> {
        val lecturePage = dao.search(request)
        val content = converter.converts(lecturePage.content)
        return PageResponse(lecturePage.totalElements, content)
    }

    @Transactional(readOnly = true)
    fun searchHottest(request: SearchHottestRequest): PageResponse<LectureResponse> {
        val hottestPage = dao.searchHottest(request)
        val content = converter.converts(hottestPage.content)
        return PageResponse(hottestPage.totalElements, content)
    }

    @Transactional
    fun create(request: CreateLectureRequest): LectureResponse {
        val lecture = dao.create(request)
        return converter.converts(lecture)
    }
}