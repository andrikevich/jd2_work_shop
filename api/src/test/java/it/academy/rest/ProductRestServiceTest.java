package it.academy.rest;

import it.academy.RestTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestTestConfiguration.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ProductRestServiceTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void readProduct() throws Exception {

    }

    @Test
    public void readProducts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/products")
                .param("pageNum","1").param("pageSize","10")).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString() );
    }

    @Test
    public void createProduct() {
    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void deleteProduct() {
    }
}