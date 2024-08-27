package com.my.product.controller;

import com.my.config.AppConfig;
import com.my.config.WebConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class) //스프링용 단위테스트
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class} )
@WebAppConfiguration //Spring Web MVC모듈 사용
class ProductControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc; //컨트롤러 테스트용 모의객체

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    void list() throws Exception {
        String uri = "/product/list"; //http://localhost:8888/bckboard/boardlist
        MockHttpServletRequestBuilder  mockRequestBuilder =
                MockMvcRequestBuilders.get(uri);
        //응답정보: ResultAction
        ResultActions resultActions = mockMvc.perform(mockRequestBuilder);//요청
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()); //응답상태코드200번 성공 예상

    }
}
