package studio.kidari.reservation.domain.lecture.service.dao

import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import studio.kidari.reservation.domain.lecture.dto.CreateLectureRequest
import studio.kidari.reservation.domain.lecture.dto.SearchHottestRequest
import studio.kidari.reservation.domain.lecture.dto.SearchLectureRequest
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.repository.LectureRepository
import studio.kidari.reservation.global.exception.NotFoundException

@Service
class LectureDao(
    private val repository: LectureRepository
) {

    @Transactional(readOnly = true)
    fun search(request: SearchLectureRequest): Page<Lecture> {
        return repository.search(request)
    }

    @Transactional(readOnly = true)
    fun searchHottest(request: SearchHottestRequest): Page<Lecture> {
        return repository.searchHottest(request)
    }

    @Transactional(readOnly = true)
    fun get(id: Long): Lecture {
        return repository.findByIdOrNull(id)
            ?: throw NotFoundException("강연 정보를 찾을 수 없습니다. id - $id")
    }

    @Transactional
    fun create(request: CreateLectureRequest): Lecture {
        val lecture = Lecture(request)
        return repository.save(lecture)
    }
}