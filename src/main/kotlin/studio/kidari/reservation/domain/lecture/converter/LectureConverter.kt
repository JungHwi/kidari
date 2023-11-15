package studio.kidari.reservation.domain.lecture.converter

import org.mapstruct.Mapper
import studio.kidari.reservation.domain.lecture.dto.LectureResponse
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture

@Mapper(componentModel = "spring")
interface LectureConverter {

    fun converts(lecture: Lecture): LectureResponse
    fun converts(lectures: List<Lecture>): List<LectureResponse>
}