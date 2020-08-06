package com.challenge.suite;

import com.challenge.dto.CandidateDTO;
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
public class CandidateControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void findById() throws Exception {
        String uri = "/candidate/" + UserIds.THREE + "/" + AccelerationIds.ONE + "/" + CompanyIds.ONE;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        CandidateDTO object = super.mapFromJson(result, CandidateDTO.class);
        assertThat(object, notNullValue());
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        String uri = "/candidate";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("companyId", CompanyIds.ONE.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<CandidateDTO> accelerations = Arrays.asList(super.mapFromJson(result, CandidateDTO[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(3));
    }

}
