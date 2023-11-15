package studio.kidari.reservation.domain.lecture.dto

import java.time.LocalDateTime

data class CreateLectureRequest(
    val lecturer: String,
    val hall: String,
    val maxApplicant: Int,
    val description: String,
    val startAt: LocalDateTime,
)