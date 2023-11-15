package studio.kidari.reservation.domain.lecture.persistence.repository.custom

import org.springframework.data.domain.Page
import studio.kidari.reservation.domain.lecture.dto.SearchHottestRequest
import studio.kidari.reservation.domain.lecture.dto.SearchLectureRequest
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture

interface LectureCustomRepository {

    fun search(request: SearchLectureRequest): Page<Lecture>
    fun searchHottest(request: SearchHottestRequest): Page<Lecture>
}