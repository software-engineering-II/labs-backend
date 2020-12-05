package co.edu.unal.software_engineering.labs.service;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class CourseServiceTest {

    @MockBean
    private CourseRepository courseRepository;


    @Autowired
    private CourseService courseService;

/*    @Autowired
    private TestEntityManager entityManager;*/
    
    @Test
    void findById() {
        Course course = new Course();
        course.setCourseName("Ingenieria de Software II");
        course.setDurationHours(60);
        course.setId(1);
        Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(course));
        
        Course found = courseService.findById(1);

        assertThat(found.getCourseName()).isEqualTo("Ingenieria de Software" +
                                                            " II");
    }

    @Test
    void save() {
        Course course = new Course();
        course.setCourseName("Ingenieria de Software II");
        course.setDurationHours(60);

        courseService.save(course);
        ArgumentCaptor<Course> captor = ArgumentCaptor.forClass(Course.class);
        Mockito.verify(courseRepository).save(captor.capture());
        assertEquals(captor.getValue().getCourseName(), course.getCourseName());
    }

    @Test
    void isRightCourse() {
        Course course = new Course();
        course.setCourseName("Ingenieria de Software II");
        course.setDurationHours(60);
        boolean rightCourse = courseService.isRightCourse(course);
        assertThat(rightCourse).isTrue();


    }

    @Test
    void mapperCourseEntity() {
        Course course = new Course();
        course.setCourseName("Ingenieria de Software II");
        course.setDurationHours(60);

        CoursePOJO coursePOJO = new CoursePOJO();
        coursePOJO.setCourseName("Ingenieria de Software II");
        coursePOJO.setDurationHours(60);

        Course mapped = courseService.mapperCourseEntity(coursePOJO);
        assertThat(mapped.getCourseName()).isEqualTo("Ingenieria de Software" +
                                                            " II");
        assertThat(mapped.getDurationHours()).isEqualTo(60);
    }
}