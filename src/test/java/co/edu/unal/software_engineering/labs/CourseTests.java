package co.edu.unal.software_engineering.labs;

import co.edu.unal.software_engineering.labs.controller.CourseController;
import co.edu.unal.software_engineering.labs.model.Role;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CourseTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    User setupUser(){
       User user = new User();

       LinkedList<Role> roles = new LinkedList<>();
       roles.add(roleService.findById(1));

       user.setNames("names");
       user.setPassword(passwordEncoder.encode("password"));
       user.setUsername("student");
       user.setSurnames("surnames");
       user.setRoles(roles);

       return user;
    }

    @Test
    void contextLoads() {
        assertTrue(true);
    }

}
