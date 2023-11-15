package studio.kidari.reservation.global.config.jpa

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class CreatedAtBaseEntity {

    @CreatedDate
    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

}