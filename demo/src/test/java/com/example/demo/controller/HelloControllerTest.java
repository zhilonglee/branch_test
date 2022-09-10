package com.example.demo.controller;

import com.example.demo.pojo.Person;
import com.example.demo.utils.PingUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

//@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Spy
    private Person person;

    @Test
    @DisplayName("日志响应测试用例")
    public void sayHelloTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/logback/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("hello caichao !!!"))
                .andReturn();
    }

    @Test
    @DisplayName("JSON响应测试用例")
    public void getHeaderTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getHeader")
                .header("token", "aqazwsxEDCRFV123$%^")
                .param("id", "123456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("access_token").value("aqazwsxEDCRFV123$%^"))
                .andReturn();
    }

    @Test
    @DisplayName("mock个对象测试用例")
    public void mockPerson() {
        log.error(person.toString());
    }

    @Test
    @DisplayName("pingUtils测试用例")
    public void pingUtilsTest() {
        PingUtils.ping("127.0.0.1", 3);
    }
}
