package com.challenge.suite;

import com.challenge.entity.Company;
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
public class CompanyControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void findById() throws Exception {
        String uri = "/company/" + CompanyIds.ONE;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        Company object = super.mapFromJson(result, Company.class);
        assertThat(object, notNullValue());
    }

    @Test
    @Transactional
    public void findAllByAccelerationId() throws Exception {
        String uri = "/company";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("accelerationId", AccelerationIds.TWO.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<Company> accelerations = Arrays.asList(super.mapFromJson(result, Company[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(1));
    }

    @Test
    @Transactional
    public void findAllByUserId() throws Exception {
        String uri = "/company";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("userId", UserIds.TWO.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<Company> accelerations = Arrays.asList(super.mapFromJson(result, Company[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(1));
    }

}
