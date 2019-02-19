package gr.hua.dit.feeding_service_app.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;
import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Clerk;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.entities.StudentLimit;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;
import gr.hua.dit.feeding_service_app.services.AccompanyingDocumentService;
import gr.hua.dit.feeding_service_app.services.ApplicationService;
import gr.hua.dit.feeding_service_app.services.ClerkService;
import gr.hua.dit.feeding_service_app.services.FileService;
import gr.hua.dit.feeding_service_app.services.StudentLimitService;
import gr.hua.dit.feeding_service_app.services.StudentService;
import gr.hua.dit.feeding_service_app.services.UserService;
import gr.hua.dit.feeding_service_app.utilites.CustomAuthorityUtilities;
import gr.hua.dit.feeding_service_app.utilites.Utilities;

@Controller
@RequestMapping("/clerk")
@Secured(CustomAuthorityUtilities.CLERK_ROLE)
public class ClerkController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ClerkService clerkService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private AccompanyingDocumentService accompanyingDocumentService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private StudentLimitService studentLimitService;
	
	@RequestMapping
	public String ClerkHomePage(Model model, @RequestParam Map<String, String> params, Principal principal) {
		
		Clerk clerk = clerkService.getClerk(principal.getName());
		
		model.addAttribute("curUserUsername", principal.getName());
		model.addAttribute("userFirstName", clerk.getFirstName());
		model.addAttribute("userLastName", clerk.getLastName());
		
		// checks if the limit was updated when the updateStudentLimit was called
		if (params.containsKey("studentLimitUpdated"))
			model.addAttribute("studentLimitUpdated", Boolean.parseBoolean(params.get("studentLimitUpdated")));
		// model attribute to show current student limit
		if (params.containsKey("limitUpd"))
			model.addAttribute("limitUpd", Boolean.parseBoolean(params.get("limitUpd")));
		
		
//		model.addAttribute("limit", studentLimitService.getStudentLimit().getStudent_limit());
		model.addAttribute("limits", studentLimitService.getAllStudentLimits());
		
		return "clerk-home";
	}

	// returns all the students that need a new card
	@GetMapping("/studentlist")
	public String StudentList(Model model, Principal principal) {
		
		//Gets user info about the clerk that has logged in
		Clerk clerk = clerkService.getClerk(principal.getName());
		//Gets a list of students that the logged in clerk has to modify
		List<Student> students = studentService.getStudentsWithNoData(clerk.getSupervising_dept());

		model.addAttribute("students", students);
		return "student-list";
	}

	@RequestMapping("/modify_student_search")
	public String ModifyStudent(Model model, @RequestParam Map<String, String> params) {

		// checks if the student was updated when the StudentModified was called
		if (params.containsKey("studentUpdated"))
			model.addAttribute("studentUpdated", Boolean.parseBoolean(params.get("studentUpdated")));

		// add check if needed for username
		Student student;
		student = studentService.getStudent(params.get("username"));

		model.addAttribute("student", student);
		model.addAttribute("mod_student", new ModUserHelper(params.get("username")));
		return "modify-student";
	}

	@PostMapping("/modify_student/{username}")
	public String StudentModified(@ModelAttribute("mod_student") ModUserHelper modStudent,
			@PathVariable("username") String username) {
		
		String userUpdated = "true";
		try {
			username = URLEncoder.encode(username, "UTF-8"); // encode username to UTF-8 so usernames with greek
																// characters are supported in redirect link
			modStudent.setData_init(true);
			modStudent.setIdentityCardNO(modStudent.getIdentityCardNO().replaceAll("\\s+", ""));
			System.out.println(modStudent.getIdentityCardNO());
			userService.updateUser(modStudent);
		} catch (UnsupportedEncodingException e) {
			userUpdated = "false";
			e.printStackTrace();
		}
		// make redirection string when finished
		String redStr = "redirect:/clerk/modify_student_search?" + "username=" + username + "&studentUpdated=";
		return redStr + userUpdated;

	}

	@PostMapping("/update_student_limit")
	public String updateStudentLimit(@RequestParam(name="dept") String dept, @RequestParam(name="newLimit") String[] newLimitarr,
			Model model) {
		
		boolean limitUpdated = false;
		
//		String dept = null;
//		Integer newLimit = null;
//		ArrayList<Integer> newLimitArr = new ArrayList<>();
		int newLimit = -1;
		
		for(int i=0; i<newLimitarr.length; i++) {
			if(!newLimitarr[i].isEmpty())
				try {
					newLimit = Integer.parseInt(newLimitarr[i]);
					break;
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
		}
		
		StudentLimit studentLimit;
		
		if (newLimit != -1) {
			studentLimit = studentLimitService.getStudentLimitOf(dept);
			if (studentLimit != null) {
				studentLimit.setStudent_limit(newLimit);
				studentLimitService.update(studentLimit);
				limitUpdated = true;
			}
			
		}
		
		return "redirect:/clerk?limitUpd=" + limitUpdated;
		
		
//		if (params.containsKey("dept"))
//			dept = (String) params.get("dept");
//		
//		if (dept != null) {
//			if (params.containsKey("newLimit")) {
//				newLimitArr = params.get("newLimit");
//				for (int limit : )
//			}
//		}
//		
//		StudentLimit stud = new StudentLimit();
//		stud.gets
//		
//		
//		System.out.println(newStudentLimit.getDept());
//		System.out.println(newStudentLimit.getStudent_limit());
//		return "unimplemented";
//		Integer newlimit;
//		boolean studentLimitUpdated = false;
//
//		// checks if params contain limit and the updateStudentLimit returns true/false
//		if (params.containsKey("limit")) {
//			try {
//				newlimit = Integer.parseInt(params.get("limit"));
//				StudentLimit studentLimit = studentLimitService.getStudentLimit();
//				studentLimit.setStudent_limit(newlimit);
//				studentLimitService.update(studentLimit);
//				studentLimitUpdated = true;
//			
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		String redStr = "redirect:/clerk?" + "&studentLimitUpdated=";
//		return redStr + studentLimitUpdated;
	}

	// return all applications that need to be checked
	@GetMapping("/applicationlist")
	public String applicationList(Model model, Principal principal) {
		
		//Gets user info about the clerk that has logged in
		Clerk clerk = clerkService.getClerk(principal.getName());
		
		//returns unchecked applications for clerk's dpt
		List<Application> applications = applicationService.getUncheckedApplicationsByDpt(clerk.getSupervising_dept());
		model.addAttribute("applications", applications);

		return "application-list";
	}

	@PostMapping("/applicationinfo")
	public String getApplication(Model model, @RequestParam Map<String, String> params) {

		// Load Application info, Student info and Documents to model
		int appl_id = Integer.parseInt(params.get("appl_id"));
		Application application = applicationService.getApplication(appl_id);
		Student student = application.getStudent();
		List<AccompanyingDocument> accompanyingDocuments = accompanyingDocumentService.getAccompanyingDocuments(appl_id);

		model.addAttribute("application", application);
		model.addAttribute("student", student);
		model.addAttribute("accompanyingDocuments", accompanyingDocuments);

		return "application-info";
	}
	
	@PostMapping("/applcationchecked/{appl_id}")
	public String applcationChecked(Model model, @RequestParam Map<String, String> params, 
			@PathVariable("appl_id") int appl_id, Principal principal) {
		
		Clerk clerk = clerkService.getClerk(principal.getName());
		Application application = applicationService.getApplication(appl_id);
		application.setClerk(clerk);
		boolean applApproved = Boolean.parseBoolean(params.get("approve"));
		if (applApproved == true) {
			//Get application and update it
			application.setApproved(applApproved);
			application.setScore(Utilities.scoreApplication(application));
			applicationService.update(application);	
		} else 	{
			application.setApproved(applApproved);
			applicationService.update(application);
		}
		
		return "application-checked";
	}	
	
	@PostMapping("/document")
	public ResponseEntity<byte[]> getDocument(@RequestParam Map<String, String> params) {
		
		byte[] contents = fileService.fetchFile(params.get("file_path"));
		
		if (contents == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}

		//Create response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		// If you want to download file and choose a name use this:
		//String filename = "output.pdf";
		//headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}


}
