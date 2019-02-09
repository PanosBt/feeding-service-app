package gr.hua.dit.feeding_service_app.api.application.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.feeding_service_app.api.application.request_helper.ApplicationSubmitRequestHelper;
import gr.hua.dit.feeding_service_app.api.application.response_helper.ApplicationSubmitResponse;
import gr.hua.dit.feeding_service_app.api.student.exception.StudentNotFoundException;
import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.services.ApplicationService;
import gr.hua.dit.feeding_service_app.services.StudentService;

@RestController
@RequestMapping("/api/application")
public class ApplicationRestController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	StudentService studentService;

	@PostMapping
	public ApplicationSubmitResponse submitApplication(@RequestBody ApplicationSubmitRequestHelper applicationReq) {

		//TODO Add file support
		//TODO Add validation (not null etc) on fields
		//TODO Consider the above on rest controllers...
		Student student;
		if ((student = studentService.getStudent(applicationReq.getStudent_id())) == null)
			throw new StudentNotFoundException("student with id " + applicationReq.getStudent_id() + " not found");

		Application application = new Application(
				student, applicationReq.getFamilyIncome(),
				applicationReq.getNum_siblings(), applicationReq.getOrigin_city(),
				applicationReq.getMother_employeed(), applicationReq.getFather_employeed(),
				new Date());

		applicationService.save(application);
		return null;
	}
}
