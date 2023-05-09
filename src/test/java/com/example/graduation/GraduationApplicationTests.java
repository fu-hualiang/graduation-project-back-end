package com.example.graduation;

import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.exception.MyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class GraduationApplicationTests {
    @Test
    void contextLoads() throws Exception {
        List<Long> longList = new ArrayList<>();
        longList.add(123L);
        longList.add(123L);
        longList.add(123L);
        System.out.println(Arrays.toString(longList.toArray()));
    }

    @Test
    void time() throws Exception {
        String dateString = "2023-04-28";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        long timestamp = date.getTime();
        System.out.println("Timestamp in milliseconds: " + timestamp);
        System.out.println(new Date(1683216000000L));
    }

}
