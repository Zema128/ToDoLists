package by.ita.je.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public abstract class TestControllerIT {
    @Autowired
    public TestRestTemplate restTemplate;

    @DynamicPropertySource
    static void getProp(DynamicPropertyRegistry property){
        property.add("spring.liquibase.enabled", () -> false);
        property.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }
}
