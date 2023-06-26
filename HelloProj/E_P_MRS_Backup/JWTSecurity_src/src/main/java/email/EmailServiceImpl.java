//package email;
//
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import com.jpa.s.entity.MyOTP;
//
//
//
//@Service
//public class EmailServiceImpl implements EmailService{
//    @Autowired
//    private JavaMailSender emailSender;
//	
//    // Use to send mail to outlook user
//    String SMTP_HOST_NAME = "smtp.gmail.com"; // Your organization domain
//	String SMTP_AUTH_USER = "myprojhelper@gmail.com"; // Your network id or email
//	String SMTP_AUTH_PWD = "obvyxibaksfobhjc"; // Your window password 
//	   @Override
//	    public void sendTextMail(MyOTP motpe)  { 
//		   SimpleMailMessage mailMessage= new SimpleMailMessage();
//           mailMessage.setFrom("myprojhelper@gmail.com");
//           mailMessage.setTo(motpe.getTo());
//           mailMessage.setText(motpe.getOtp());
//           mailMessage.setSubject("RE: OTP");
//
//           // Sending the mail
//           emailSender.send(mailMessage);
//	    }
//}
