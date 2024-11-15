package SpringSecurity.SpringSecurity;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String recipientMail,String cc,String subject,String body,List<String> attachments) throws MessagingException{
		MimeMessage mimeMessage=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
		helper.setTo(recipientMail);
		helper.setCc(cc);
		helper.setSubject(subject);
		helper.setText(body);
		for(String file:attachments){
		  FileSystemResource resource=new FileSystemResource(new File(file));
		  helper.addAttachment(resource.getFilename(), resource);
		}
		mailSender.send(mimeMessage);
	}
}
