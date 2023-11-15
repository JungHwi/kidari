package studio.kidari.reservation.domain.enrollment.dto

import org.springframework.data.domain.Pageable

data class SearchEnrollmentRequest(
    val lectureId: Long? = null,
    val memberId: String? = null,
    val pageable: Pageable
)
