package com.wagengaragebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest ()
@ContextConfiguration(classes ={WagenGarageBackendApplication.class})
class WagenGarageBackendApplicationTests {

    @Test
    @DisplayName("test")
    void contextLoads() {

        assertNotEquals(1,2);
    }
}
