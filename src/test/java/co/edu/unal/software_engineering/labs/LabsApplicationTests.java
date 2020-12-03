package co.edu.unal.software_engineering.labs;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import co.edu.unal.software_engineering.labs.service.CourseService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.mock;
import java.io.IOException;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class LabsApplicationTests {

    @InjectMocks
    CourseService courseService;

    CourseRepository courseRepository = mock(CourseRepository.class);

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> LabsApplication.main(new String[]{}));
    }

    @Test
    void saveAndFindByIdTest() {
        //INSERT INTO "labs"."public"."course" (course_id, course_name, duration_hours) VALUES (1, 'Matemática Aplicada', 100);
        //INSERT INTO "labs"."public"."course" (course_id, course_name, duration_hours) VALUES (2, 'Ingeniería de Software II', 148);
        //ALTER SEQUENCE course_course_id_seq RESTART WITH 3;
        Course course = new Course();
        course.setId(1);
        course.setCourseName("Matemática Aplicada");
        course.setDurationHours(100);
        courseRepository.save(course);

        when(courseRepository.getOne(1)).thenReturn(course);
        Course courseTest = courseRepository.getOne(1);
        assertNotNull(course);
        assertNotNull(courseTest);
        boolean isTheCourse = (courseTest.getId() == 1) && (courseTest.getCourseName().equals("Matemática Aplicada")) && (courseTest.getDurationHours() == 100);

        assertEquals(true, isTheCourse);


    }


    @Test
    void isRightCourseTestTrue() {
        Course course = new Course();
        course.setId(null);
        course.setCourseName("Curso del test");
        course.setDurationHours(40);

        assertEquals(true, courseService.isRightCourse(course));
    }

    @Test
    void isRightCourseTestwhitID() {
        Course course = new Course();
        course.setId(15);
        course.setCourseName("Curso del test");
        course.setDurationHours(40);
        assertEquals(false, courseService.isRightCourse(course));
    }

    @Test
    void isRightCourseTestwhitoutCourseName() {
        Course course = new Course();
        course.setId(null);
        course.setCourseName("");
        course.setDurationHours(40);
        assertEquals(false, courseService.isRightCourse(course));
    }

    @Test
    void isRightCourseTestwhitoutDurationHours() {
        Course course = new Course();
        course.setId(null);
        course.setCourseName("Curso del test");
        course.setDurationHours(null);
        assertEquals(false, courseService.isRightCourse(course));
    }

    @Test
    void mapperCourseEntitytest(){
        CoursePOJO coursePOJO = new CoursePOJO();

        String nombre = "Curso prueba";
        coursePOJO.setCourseName(nombre);
        int horas = 5;
        coursePOJO.setDurationHours(horas);

        Course course = courseService.mapperCourseEntity(coursePOJO);

        boolean isCourse = course.getCourseName().equals(nombre) && (course.getDurationHours() == horas);

        assertEquals(true, isCourse);
    }
}