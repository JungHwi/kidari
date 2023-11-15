package studio.kidari.reservation.domain.lecture.persistence.domain

import jakarta.persistence.*
import studio.kidari.reservation.domain.lecture.dto.CreateLectureRequest
import studio.kidari.reservation.global.config.jpa.CreatedAtBaseEntity
import java.time.LocalDateTime

@Entity
class Lecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @Column
    val lecturer: String,

    @Column
    val hall: String,

    @Column
    val maxApplicant: Int,

    @Column
    val description: String,

    @Column
    val startAt: LocalDateTime

) : CreatedAtBaseEntity() {

    constructor(request: CreateLectureRequest) :
            this(
                lecturer = request.lecturer,
                hall = request.hall,
                maxApplicant = request.maxApplicant,
                description = request.description,
                startAt = request.startAt,
            )
}