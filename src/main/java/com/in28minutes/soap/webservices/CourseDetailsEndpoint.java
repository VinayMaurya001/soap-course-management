package com.in28minutes.soap.webservices;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.CourseStatus;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.soap.webservices.bean.Course;
import com.in28minutes.soap.webservices.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	CourseDetailsService service;

	// method
	// input - GetCourseDetailsRequest
	// output - GetCourseDetailsResponse

	// http://in28minutes.com/courses
	// GetCourseDetailsRequest
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		if(course==null){
			//throw new RuntimeException("Invalid course id "+request.getId());
			throw new CourseNotFoundException("2Invalid course id "+request.getId());
		}
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courseList = service.findAllCources();
		return mapAllCourseDetails(courseList);
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart ="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processDeleteCourseDetailsRequest(
			@RequestPayload DeleteCourseDetailsRequest request) {
		CourseStatus status = service.deleteById(request.getId());
		DeleteCourseDetailsResponse deleteCourseDetailsResponse=new DeleteCourseDetailsResponse();
		deleteCourseDetailsResponse.setStatus(status);
		return deleteCourseDetailsResponse;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courseList) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		List<CourseDetails> courseDetailsList = new ArrayList<>();
		for (Course course : courseList) {
			response.getCourseDetails().add(mapCourse(course));
		}
		return response;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

}