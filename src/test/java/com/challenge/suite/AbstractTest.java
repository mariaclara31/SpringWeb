package com.challenge.suite;

import com.challenge.SpringHttpApplication;
import com.challenge.util.LocalDateTimeJsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringHttpApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz) {
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter()).create().fromJson(json, clazz);
    }

    public static class UserIds {
        public static Long ONE = 6001L;
        public static Long TWO = 6002L;
        public static Long THREE = 6003L;
        public static Long FOUR = 6004L;
        public static Long FIVE = 6005L;
    }

    public static class CompanyIds {
        public static Long ONE = 5001L;
        public static Long TWO = 5002L;
    }

    public static class ChallengeIds {
        public static Long ONE = 1001L;
        public static Long TWO = 1002L;
        public static Long TRHEE = 1003L;
    }

    public static class AccelerationIds {
        public static Long ONE = 2001L;
        public static Long TWO = 2002L;
    }
}