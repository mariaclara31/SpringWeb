package com.challenge.suite;

import com.challenge.entity.Challenge;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Sql("/pre-sql.sql")
public class ChallengeControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        String uri = "/challenge";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("accelerationId", AccelerationIds.TWO.toString())
                .param("userId", UserIds.THREE.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<Challenge> accelerations = Arrays.asList(super.mapFromJson(result, Challenge[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(1));
    }

}
