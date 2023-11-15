package studio.kidari.reservation.domain.lecture.api.admin

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import studio.kidari.reservation.domain.lecture.dto.CreateLectureRequest
import studio.kidari.reservation.domain.lecture.dto.LectureResponse
import studio.kidari.reservation.domain.lecture.dto.SearchLectureRequest
import studio.kidari.reservation.domain.lecture.service.LectureService
import studio.kidari.reservation.global.wrapper.PageResponse

@RestController
@RequestMapping("/admin/lecture")
class AdminLectureController(
    private val service: LectureService
) {

    @GetMapping
    fun search(
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): PageResponse<LectureResponse>? {

        val request = SearchLectureRequest(
            pageable = PageRequest.of(page - 1, size)
        )

        return service.search(request)
    }

    @PostMapping
    fun create(@RequestBody request: CreateLectureRequest): LectureResponse {
        return service.create(request)
    }
}