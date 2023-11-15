package studio.kidari.reservation.domain.enrollment.api.admin

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import studio.kidari.reservation.domain.enrollment.dto.SearchEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.service.EnrollmentService
import studio.kidari.reservation.global.wrapper.PageResponse

@RestController
@RequestMapping("/admin/enrollment")
class AdminEnrollmentController(
    private val service: EnrollmentService
) {

    @GetMapping("")
    fun search(
        @RequestParam lectureId: Long,
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): PageResponse<String> {

        val request = SearchEnrollmentRequest(
            lectureId = lectureId,
            pageable = PageRequest.of(page - 1, size)
        )

        return service.search(request)
    }
}