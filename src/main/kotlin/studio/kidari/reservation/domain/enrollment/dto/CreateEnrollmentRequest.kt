package studio.kidari.reservation.domain.enrollment.dto

data class CreateEnrollmentRequest(
    val lectureId: Long,
    val memberId: String
)
