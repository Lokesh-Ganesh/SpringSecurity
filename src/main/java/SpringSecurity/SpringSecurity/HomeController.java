package SpringSecurity.SpringSecurity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HomeController {
	
	
	@Autowired
	private EmailSenderService emailService;
	
	private List<String> resource=new ArrayList<String>();
	
	
	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody Mail mail) throws MessagingException{
		resource.add("/home/lokesh/Downloads/currency(1).csv");
		resource.add("/home/lokesh/Downloads/pic.jpg");
		emailService.sendEmail(mail.getRecipientMail(),mail.getCc(), mail.getSubject(), mail.getBody(),resource);
		return new ResponseEntity<>("Mail sent successfully",HttpStatus.CREATED);
	}
	
	
	List<Student> students=new ArrayList<>(Arrays.asList(
			new Student(1,"Pradeep","B.sc"),
			new Student(2,"Ezhil","MBA"),
			new Student(3,"Lokesh","BCA")
			));
	

	@GetMapping("/students")
	public List<Student> getStudentDetails()
	{
		return students;
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest req){
		return (CsrfToken) req.getAttribute("_csrf");
	}
	
	
	
	@PostMapping("/")
	public Student createStudentData(@RequestBody Student student){
		students.add(student);
		return student;
	}
	
	@GetMapping("/getSessionId")
	public String getSessionId(HttpServletRequest req)
	{
		return "Session Id "+req.getSession().getId();
	}
	
	
	
}
