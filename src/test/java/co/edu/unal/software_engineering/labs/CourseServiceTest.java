package co.edu.unal.software_engineering.labs;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import co.edu.unal.software_engineering.labs.service.CourseService;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Test
    void isRightCourseFailedId(){
        Course course = new Course();
        course.setId(1);
        course.setCourseName("Ingesoft");
        course.setDurationHours(10);
        Assertions.assertFalse(courseService.isRightCourse(course));
    }
    @Test
    void isRightCourseFailedCourseName(){
        Course course = new Course();
        course.setCourseName("");
        course.setDurationHours(10);
        Assertions.assertFalse(courseService.isRightCourse(course));
    }
    @Test
    void isRightCourseFailedDurationHours(){
        Course course = new Course();
        course.setCourseName("Ingesoft");
        Assertions.assertFalse(courseService.isRightCourse(course));
    }
    @Test
    void isRightCourseSuccess(){
        Course course = new Course();
        course.setCourseName("Ingesoft");
        course.setDurationHours(10);
        Assertions.assertTrue(courseService.isRightCourse(course));
    }

    @Test
    void mapperCourseEntityRightCoursePojo(){
        CoursePOJO coursePOJO= new CoursePOJO();
        coursePOJO.setCourseName("Ingesoft");
        coursePOJO.setDurationHours(10);
        Course course = courseService.mapperCourseEntity(coursePOJO);
        Assertions.assertTrue(courseService.isRightCourse(course));
    }
    @Test
    void mapperCourseEntityPreservesProperties(){
        CoursePOJO coursePOJO= new CoursePOJO();
        coursePOJO.setCourseName("Ingesoft");
        coursePOJO.setDurationHours(10);
        Course course = courseService.mapperCourseEntity(coursePOJO);
        Assertions.assertEquals(coursePOJO.getCourseName(), course.getCourseName());
        Assertions.assertEquals(coursePOJO.getDurationHours(), course.getDurationHours());
    }
    @Test
    void saveAndFindByIdPreservesObject(){
        CourseRepository courseRepositoryMock = mock(CourseRepository.class);
        CourseService courseService1 = new CourseService(courseRepositoryMock);
        Course course = new Course();
        course.setCourseName("Ingesoft");
        course.setDurationHours(10);
        courseService1.save(course);
        when(courseRepositoryMock.findById(1)).thenReturn(Optional.of(course));
        Course foundCourse = courseService1.findById(1);
        Assertions.assertEquals(foundCourse.getCourseName(),course.getCourseName());
        Assertions.assertEquals(foundCourse.getDurationHours(),course.getDurationHours());

    }
    @Test
    void findByIdReturnNullWhenIdNotExist(){
        CourseRepository courseRepositoryMock = mock(CourseRepository.class);
        CourseService courseService1 = new CourseService(courseRepositoryMock);
        when(courseRepositoryMock.findById(1)).thenReturn(Optional.empty());
        Assertions.assertNull(courseService1.findById(1));

    }


}

