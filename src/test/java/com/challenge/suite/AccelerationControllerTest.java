package com.challenge.suite;

import com.challenge.entity.Acceleration;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Sql("/pre-sql.sql")
public class AccelerationControllerTest extends AbstractTest {

    public static final String ACCELERATION_ONE_NAME = "Metallica Acceleration";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void findById() throws Exception {
        String uri = "/acceleration/" + AccelerationIds.ONE;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String result = mvcResult.getResponse().getContentAsString();
        Acceleration acceleration = super.mapFromJson(result, Acceleration.class);
        assertThat(acceleration, Matchers.notNullValue());
        assertThat(acceleration.getName(), equalTo(ACCELERATION_ONE_NAME));
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        String uri = "/acceleration";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("companyId", CompanyIds.ONE.toString())
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
        List<Acceleration> accelerations = super.mapFromJson(result, new ArrayList<Acceleration>().getClass());
        assertThat(accelerations, Matchers.notNullValue());
        assertThat(accelerations, hasSize(3));
    }

}
