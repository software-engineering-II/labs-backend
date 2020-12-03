package co.edu.unal.software_engineering.labs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.repository.CourseRepository;
import co.edu.unal.software_engineering.labs.service.CourseService;

public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById(){
        Course course = new Course();
        course.setId(1);
        course.setCourseName("Discrete Math III");
        course.setDurationHours(48);
        when( courseRepository.findById( 1 ) ).thenReturn( Optional.of(course) );
        when( courseRepository.findById( 2 ) ).thenReturn( Optional.empty() );

        Course firstCourse = courseService.findById( 1 );
        Course secondCourse = courseService.findById( 2 );

        verify(courseRepository).findById(1);
        verify(courseRepository).findById(2);

        assertEquals(course, firstCourse);
        assertTrue( secondCourse == null );
    }

    @Test
    public void testIsRightCourse(){
        Course course1 = new Course();
        course1.setId(null);
        course1.setCourseName("Discrete Math III");
        course1.setDurationHours(48);
        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseName("Galois Fields and Criptography");
        course2.setDurationHours(96);
        Course course3 = new Course();
        course3.setId(null);
        course3.setCourseName("");
        course3.setDurationHours(55);
        Course course4 = new Course();
        course4.setId(null);
        course4.setCourseName("Topological Analysis");
        course4.setDurationHours(null);

        boolean result1 = courseService.isRightCourse( course1 );
        boolean result2 = courseService.isRightCourse( course2 );
        boolean result3 = courseService.isRightCourse( course3 );
        boolean result4 = courseService.isRightCourse( course4 );

        assertTrue( result1 == true );
        assertTrue( result2 == false );
        assertTrue( result3 == false );
        assertTrue( result4 == false );
    }

    @Test
    public void testMapperCourseEntity(){
        CoursePOJO coursePOJO = new CoursePOJO();
        coursePOJO.setCourseName("Discrete Math III");
        coursePOJO.setDurationHours(48);
        Course course = new Course();
        course.setCourseName("Discrete Math III");
        course.setDurationHours(48);

        Course result = courseService.mapperCourseEntity( coursePOJO );

        assertEquals(course.getCourseName(), result.getCourseName());
        assertEquals(course.getDurationHours(), result.getDurationHours());
    }

}
