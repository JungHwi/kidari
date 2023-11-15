package studio.kidari.reservation.domain.lecture.persistence.repository

import org.springframework.stereotype.Repository
import studio.kidari.reservation.domain.lecture.persistence.domain.Lecture
import studio.kidari.reservation.domain.lecture.persistence.repository.custom.LectureCustomRepository
import studio.kidari.reservation.global.config.jpa.DefaultJpaRepository

@Repository
interface LectureRepository : DefaultJpaRepository<Lecture, Long>, LectureCustomRepository {

}