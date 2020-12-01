package co.edu.unal.software_engineering.labs;

import co.edu.unal.software_engineering.labs.controller.CourseController;
import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.RoleService;
import co.edu.unal.software_engineering.labs.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    private final Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();

    private final ObjectMapper objectMapper = mapperBuilder.build();

    User setupStudent(){
       User user = new User();

       LinkedList<Role> roles = new LinkedList<>();
       roles.add(roleService.findById(1));

       user.setNames("names");
       user.setPassword(passwordEncoder.encode("password"));
       user.setUsername("student");
       user.setSurnames("surnames");
       user.setRoles(roles);

       return userService.save(user);
    }

    User setupTeacher(){
        User user = new User();

        LinkedList<Role> roles = new LinkedList<>();
        roles.add(roleService.findById(2));

        user.setNames("names");
        user.setPassword(passwordEncoder.encode("password"));
        user.setUsername("student");
        user.setSurnames("surnames");
        user.setRoles(roles);

        return userService.save(user);
    }

    String getToken() throws Exception {
        String uri = "/oauth/token";

        ResultActions resultActions = this.mockMvc.perform(post(uri)
                .param("client","soft-eng-ii")
                .param("secret","secret")
                .header("grant_type","client_credentials")
                .header("scope","trust")).andExpect(status().isOk());

        String response = resultActions.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(response).get("access_token").toString();
    }

    @Test
    void contextLoads() {
        assertTrue(true);
    }

//    @Test
//    void createCourse() throws Exception{
//        String uri = "/profesor/crear-curso";
//        String token = getToken();
//
//        CoursePOJO requestCourse = new CoursePOJO();
//
//        requestCourse.setCourseName("ingesoft");
//
//        this.mockMvc.perform(post(uri).header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(requestCourse))).andExpect(status().isBadRequest());
//
//        requestCourse.setDurationHours(60);
//
//        this.mockMvc.perform(post(uri).header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(requestCourse))).andExpect(status().isCreated());
//    }



}
