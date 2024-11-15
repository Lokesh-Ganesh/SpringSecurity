package SpringSecurity.SpringSecurity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail 
{
	private String recipientMail;
	private String cc;
	private String subject;
	private String body;
}
