package gr.hua.dit.feeding_service_app.api.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.feeding_service_app.api.student.exception.StudentNotFoundException;
import gr.hua.dit.feeding_service_app.api.student.response_helper.ApplicationStatusResponse;
import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.services.ApplicationService;
import gr.hua.dit.feeding_service_app.services.StudentService;
import gr.hua.dit.feeding_service_app.utilites.Utilities;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	@Autowired
	StudentService studentService;

	@Autowired
	ApplicationService applicationService;

	@GetMapping("/{username}")
	public Student getStudent(@PathVariable String username) {
		Student student;
		if ((student = studentService.getStudent(username)) == null)
			throw new StudentNotFoundException("Student with username " + username + " not found");
		else
			return student;

	}

	@GetMapping("/{id}/appl_status/{year}")
	public ApplicationStatusResponse getStudentApplStatus(@PathVariable int id, @PathVariable int year) {
		Student student;
		if ((student = studentService.getStudent(id)) == null)
			throw new StudentNotFoundException("Student with id " + id + " not found");

		Application application = null;

		// search for the application submitted on requested year
		for (Application app : student.getApplications()) {
			if (Utilities.getYearFromDate(app.getSubm_date()) == year) {
				application = app;
				break;
			}
		}

		// prepare response

		Boolean submited = application != null;

		Boolean checked = null;
		Integer rank = null;
		Integer score = null;
		Boolean feed_qualified = null;

		if (submited)
			// if isApproved is null, then the application has not been checked yet
			if (checked = application.isApproved() != null)
				if (application.isApproved()) {
					rank = applicationService.getRank(application);
					score = application.getScore();
					feed_qualified = rank.intValue() <= Utilities.getStudentLimit().intValue();
				}

		return new ApplicationStatusResponse(
				submited, checked, rank, score,
				Utilities.getStudentLimit(),feed_qualified);

	}

}