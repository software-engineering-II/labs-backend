package co.edu.unal.software_engineering.labs.pojo;

import java.util.Objects;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.model.Course;

public class EnrolledCoursePOJO {
  
  private final Integer courseId;
  private final String name;
  private final Integer courseHours;
  private final RolePOJO role;



  public EnrolledCoursePOJO(Association association){
    Course course = association.getCourse();
    this.courseId = course.getId();
    this.name = course.getCourseName();
    this.courseHours = course.getDurationHours();
    this.role = new RolePOJO(association.getRole());
  }

  public EnrolledCoursePOJO(Integer courseId, String name, Integer courseHours, RolePOJO role) {
    this.courseId = courseId;
    this.name = name;
    this.courseHours = courseHours;
    this.role = role;
  }

  public Integer getCourseId() {
    return this.courseId;
  }


  public String getName() {
    return this.name;
  }


  public Integer getCourseHours() {
    return this.courseHours;
  }


  public RolePOJO getRole() {
    return this.role;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EnrolledCoursePOJO)) {
            return false;
        }
        EnrolledCoursePOJO enrolledCoursePOJO = (EnrolledCoursePOJO) o;
        return Objects.equals(courseId, enrolledCoursePOJO.courseId) && Objects.equals(name, enrolledCoursePOJO.name) && Objects.equals(courseHours, enrolledCoursePOJO.courseHours) && Objects.equals(role, enrolledCoursePOJO.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, name, courseHours, role);
  }

  @Override
  public String toString() {
    return "{" +
      " courseId='" + getCourseId() + "'" +
      ", name='" + getName() + "'" +
      ", courseHours='" + getCourseHours() + "'" +
      ", role='" + getRole() + "'" +
      "}";
  }
  
}
