package com.challenge.suite;

import com.challenge.dto.SubmissionDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Sql("/pre-sql.sql")
public class SubmissionControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        String uri = "/submission";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("challengeId", ChallengeIds.TRHEE.toString())
                .param("accelerationId", AccelerationIds.TWO.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<SubmissionDTO> accelerations = Arrays.asList(super.mapFromJson(result, SubmissionDTO[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(3));
    }

}
