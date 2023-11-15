package studio.kidari.reservation.global.config.container

import org.springframework.stereotype.Component
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

@Component
class MySqlTestContainer {

    companion object {
        @Container
        @JvmStatic
        val MYSQL_CONTAINER: MySQLContainer<*> = MySQLContainer<Nothing>("mysql:8.0")
            .apply { withDatabaseName(DATABASE_NAME) }
            .apply { withUsername(USERNAME) }
            .apply { withPassword(PASSWORD) }
            .apply { withReuse(true) }
            .apply { start() }

        const val DATABASE_NAME = "reservation"
        const val USERNAME = "kidari"
        const val PASSWORD = "kidari_pass"
    }
}