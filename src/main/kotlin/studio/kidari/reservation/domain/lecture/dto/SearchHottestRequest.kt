package studio.kidari.reservation.domain.lecture.dto

import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

data class SearchHottestRequest(
    val searchStartAt: LocalDateTime? = null,
    val pageable: Pageable
)
