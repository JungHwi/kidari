package studio.kidari.reservation.domain.enrollment.converter

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import studio.kidari.reservation.domain.enrollment.dto.EnrollmentResponse
import studio.kidari.reservation.domain.enrollment.persistence.domain.Enrollment

@Mapper(componentModel = "spring")
interface EnrollmentConverter {

    @Mapping(source = "lecture.id", target = "lectureId")
    fun converts(enrollment: Enrollment): EnrollmentResponse
    fun converts(enrollments: List<Enrollment>): List<EnrollmentResponse>
}