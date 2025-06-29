package br.com.projeto.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> ApiApplication.main(new String[] {}));
    }
}