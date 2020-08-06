package com.challenge.suite;

import com.challenge.entity.Company;
import com.challenge.entity.User;
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
public class UserControllerTest extends AbstractTest {

    private static final String ACCELERATION_NAME = "Metallica Acceleration";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void findById() throws Exception {
        String uri = "/user/" + UserIds.ONE;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        User object = super.mapFromJson(result, User.class);
        assertThat(object, notNullValue());
    }

    @Test
    @Transactional
    public void findByAccelerationName() throws Exception {
        String uri = "/user";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("accelerationName", ACCELERATION_NAME)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<Company> accelerations = Arrays.asList(super.mapFromJson(result, Company[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(3));
    }

    @Test
    @Transactional
    public void findByCompanyId() throws Exception {
        String uri = "/user";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .param("companyId", CompanyIds.TWO.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<Company> accelerations = Arrays.asList(super.mapFromJson(result, Company[].class));
        assertThat(accelerations, notNullValue());
        assertThat(accelerations, hasSize(3));
    }


}
