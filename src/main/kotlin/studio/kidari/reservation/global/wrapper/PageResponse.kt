package studio.kidari.reservation.global.wrapper

import org.springframework.data.domain.Page

data class PageResponse<T>(val total: Long, val content: List<T>) {
    constructor(result: Page<T>) : this(result.totalElements, if (result.isEmpty) ArrayList<T>() else result.content)
}