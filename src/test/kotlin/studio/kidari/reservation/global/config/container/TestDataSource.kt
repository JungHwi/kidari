package studio.kidari.reservation.global.config.container

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.sql.DataSource

@Configuration
class TestDataSource {

    @Bean
    @DependsOn("mySqlTestContainer")
    fun dataSource(): DataSource =
        DataSourceBuilder.create()
            .url(
                "jdbc:mysql://localhost:" +
                        "${MySqlTestContainer.MYSQL_CONTAINER.getMappedPort(3306)}" +
                        "/${MySqlTestContainer.DATABASE_NAME}"
            )
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .username(MySqlTestContainer.USERNAME)
            .password(MySqlTestContainer.PASSWORD)
            .build()
}