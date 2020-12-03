package co.edu.unal.software_engineering.labs;

import co.edu.unal.software_engineering.labs.controller.CourseController;
import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.pojo.RegisterUserPOJO;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
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
    private UserService userService;

    private final Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();

    private final ObjectMapper objectMapper = mapperBuilder.build();

    void setupRoleTeacher() {
        if (this.roleService.findById(2) != null) return;
        Role role = new Role();
        role.setId(2);
        role.setRoleName("PROFESOR");
        this.roleService.save(role);
    }

    String getToken(String username, String password) throws Exception {
        String uri = "/oauth/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        params.add("scope", "trust");

        ResultActions resultActions = this.mockMvc.perform(post(uri)
                .with(httpBasic("soft-eng-ii","secret"))
                .header("Authorization", "Basic c29mdC1lbmctaWk6c2VjcmV0")
                .params(params)
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());

        String response = resultActions.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(response).get("access_token").toString();
    }

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void createCourse() throws Exception {
        setupRoleTeacher();
        String uriCreateUser = "/registro/nuevo-usuario/rol/2";
        RegisterUserPOJO userPOJO = new RegisterUserPOJO();
        userPOJO.setNames("pepito");
        userPOJO.setSurnames("perez");
        userPOJO.setUsername("profe2020");
        userPOJO.setPassword("password");

        this.mockMvc.perform(post(uriCreateUser).contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(this.objectMapper.writeValueAsString(userPOJO))).andExpect(status().isCreated());

        String uriCreateCourse = "/profesor/crear-curso";
        String token = getToken(userPOJO.getUsername(), userPOJO.getPassword());

        CoursePOJO requestCourse = new CoursePOJO();
        requestCourse.setCourseName("ingesoft");

        this.mockMvc.perform(post(uriCreateCourse).header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(requestCourse))).andExpect(status().isBadRequest());

        requestCourse.setDurationHours(60);

        this.mockMvc.perform(post(uriCreateCourse).header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(requestCourse))).andExpect(status().isCreated());

        this.courseService.deleteById(this.courseService.findByCourseName(requestCourse.getCourseName()).getId());
        this.userService.deleteById(this.userService.findByUsername(userPOJO.getUsername()).getId());
    }

    @Test
    void getCoursesByUser() throws Exception {
        setupRoleTeacher();
        String uriCreateUser = "/registro/nuevo-usuario/rol/2";
        RegisterUserPOJO userPOJO = new RegisterUserPOJO();
        userPOJO.setNames("pepito");
        userPOJO.setSurnames("perez");
        userPOJO.setUsername("profe2020");
        userPOJO.setPassword("password");

        this.mockMvc.perform(post(uriCreateUser).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.objectMapper.writeValueAsString(userPOJO))).andExpect(status().isCreated());

        String uriGetCourses = "/mis-cursos";
        String token = getToken(userPOJO.getUsername(), userPOJO.getPassword());

        this.mockMvc.perform(get(uriGetCourses).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        this.userService.deleteById(this.userService.findByUsername(userPOJO.getUsername()).getId());
    }

}
