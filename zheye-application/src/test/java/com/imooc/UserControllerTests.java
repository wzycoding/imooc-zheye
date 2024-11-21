package com.imooc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author wzy
 * @date 2024/11/20 21:25
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Rollback
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        String requestBody = "{\"email\":\"123@qq.com\", \"password\":\"123456\"}";

        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON).
                        content(requestBody))
                .andExpect(status().isOk()) // 验证返回状态码 200
                .andExpect(jsonPath("$.data.token").isNotEmpty()); // 验证返回的 token 不为空
    }
}
