package com.example.graduation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraduationApplicationTests {

    @Value("${spring.mvc.static-path-pattern}")
    String path;

    @Test
    void contextLoads() {
        System.out.println(path);
    }

}
