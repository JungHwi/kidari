package studio.kidari.reservation.domain.enrollment.dto

data class EnrollmentResponse(
    val id: Long,
    val lectureId: Long,
    val memberId: String
)
