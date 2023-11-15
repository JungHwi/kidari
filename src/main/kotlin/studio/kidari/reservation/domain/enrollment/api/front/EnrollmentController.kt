package studio.kidari.reservation.domain.enrollment.api.front

import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studio.kidari.reservation.domain.enrollment.dto.CreateEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.dto.SearchEnrollmentRequest
import studio.kidari.reservation.domain.enrollment.service.EnrollmentService
import studio.kidari.reservation.domain.lecture.dto.LectureResponse
import studio.kidari.reservation.global.wrapper.PageResponse

@RestController
@RequestMapping("/front")
class EnrollmentController(
    private val service: EnrollmentService
) {

    @PostMapping("/v1/enrollment")
    fun create(@RequestBody request: CreateEnrollmentRequest): ResponseEntity<Any> {
        service.create(request)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/v1/enrollment")
    fun getEnrolledLecture(
        @RequestParam memberId: String,
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): PageResponse<LectureResponse> {

        val request = SearchEnrollmentRequest(
            memberId = memberId,
            pageable = PageRequest.of(page - 1, size)
        )

        return service.getEnrolledLecture(request)
    }

    @DeleteMapping("/v1/enrollment/{enrollmentId}")
    fun delete(@PathVariable enrollmentId: Long): ResponseEntity<Any> {
        service.delete(enrollmentId)
        return ResponseEntity(HttpStatus.OK)
    }
}