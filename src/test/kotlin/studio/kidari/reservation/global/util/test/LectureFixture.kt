package studio.kidari.reservation.global.util.test

import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import java.time.LocalDateTime

fun makeLecture(): Lecture {
    return Lecture(
        lecturer = "테스트 강연자",
        hall = "테스트 강연장",
        maxApplicant = 10,
        description = "테스트 강연",
        startAt = LocalDateTime.now()
    )
}