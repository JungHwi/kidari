package studio.kidari.reservation.domain.lecture.api.front

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import studio.kidari.reservation.domain.lecture.dto.LectureResponse
import studio.kidari.reservation.domain.lecture.dto.SearchHottestRequest
import studio.kidari.reservation.domain.lecture.dto.SearchLectureRequest
import studio.kidari.reservation.domain.lecture.service.LectureService
import studio.kidari.reservation.global.wrapper.PageResponse
import java.time.LocalDateTime

@RestController
@RequestMapping("/front")
class LectureController(
    private val service: LectureService
) {

    @GetMapping("/v1/lecture")
    fun search(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): PageResponse<LectureResponse> {

        val request = SearchLectureRequest(
            searchStartAt = LocalDateTime.now().minusDays(1),
            searchEndAt = LocalDateTime.now().plusDays(7),
            pageable = PageRequest.of(page - 1, size)
        )

        return service.search(request)
    }

    @GetMapping("/v1/lecture/hottest")
    fun searchHottest(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): PageResponse<LectureResponse> {

        val request = SearchHottestRequest(
            searchStartAt = LocalDateTime.now().minusDays(3),
            pageable = PageRequest.of(page - 1, size)
        )
        return service.searchHottest(request)
    }
}