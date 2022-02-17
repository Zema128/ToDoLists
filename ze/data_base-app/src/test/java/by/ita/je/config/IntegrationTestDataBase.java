package by.ita.je.config;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest
@DirtiesContext
public abstract class IntegrationTestDataBase {

    @Container
    private static PostgreSQLContainer CONTAINER = (PostgreSQLContainer) new PostgreSQLContainer("postgres")
            .withInitScript("data-test.sql");

    @DynamicPropertySource
    static void getProp(DynamicPropertyRegistry property){
        CONTAINER.start();
        property.add("spring.datasource.url", () -> CONTAINER.getJdbcUrl());
        property.add("spring.datasource.username", () -> CONTAINER.getUsername());
        property.add("spring.datasource.password", () -> CONTAINER.getPassword());
    }
}
