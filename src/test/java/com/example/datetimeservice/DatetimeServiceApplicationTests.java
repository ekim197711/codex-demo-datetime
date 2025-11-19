package com.example.datetimeservice;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatetimeServiceApplicationTests {

    @BeforeEach
    void logHello() {
        System.out.println("hello " + new Random().nextInt());
    }

	@Test
	void contextLoads() {
	}

}
