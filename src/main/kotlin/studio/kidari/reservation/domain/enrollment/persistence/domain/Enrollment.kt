package studio.kidari.reservation.domain.enrollment.persistence.domain

import jakarta.persistence.*
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.global.config.jpa.CreatedAtBaseEntity

@Entity
class Enrollment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @Column
    val memberId: String,

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    val lecture: Lecture

) : CreatedAtBaseEntity() {


}