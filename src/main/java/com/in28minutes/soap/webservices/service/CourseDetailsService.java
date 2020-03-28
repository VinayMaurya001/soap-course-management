package com.in28minutes.soap.webservices.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.courses.CourseStatus;
import com.in28minutes.soap.webservices.bean.Course;

@Component
public class CourseDetailsService {
	private static List<Course> courses = new ArrayList<>();
	static {
		Course course1 = new Course(1, "Spring", "10 Steps");
		courses.add(course1);
		Course course2 = new Course(2, "Spring MVC", "10 Examples");
		courses.add(course2);
		Course course3 = new Course(3, "Spring Boot", "6K Students");
		courses.add(course3);
		Course course4 = new Course(4, "Maven", "Most popular maven course on internet!");
		courses.add(course4);
	}

	// course - 1
	public Course findById(BigInteger bigInteger) {
		for (Course course : courses) {
			if (course.getId().compareTo(bigInteger)==0)
				return course;
		}
		return null;
	}

	// courses
	public List<Course> findAllCources() { 
		return courses;
	}

	public CourseStatus deleteById(BigInteger id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId().compareTo(id)==0){
				iterator.remove();
				return CourseStatus.SUCCESS;
			}
		}
		return CourseStatus.FAILURE;
	}

	// updating course & new course
}
