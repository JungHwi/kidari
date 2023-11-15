package studio.kidari.reservation.global.util.test

import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture

fun makeEnrollment(lecture: Lecture): Enrollment {
    return Enrollment(
        lecture = lecture,
        memberId = "123"
    )
}