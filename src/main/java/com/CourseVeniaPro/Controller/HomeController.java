package com.CourseVeniaPro.Controller;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.SplittableRandom;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostMapping("/Signup")
	
		public String Signup(HttpServletRequest req) throws SQLException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String psw = req.getParameter("psw");
		String mobile = req.getParameter("mobile");

		Connection con = jdbcTemplate.getDataSource().getConnection();
		Statement stmt = con.createStatement();
		String query = "select * from  user_info where email='" + email + "' ";
		ResultSet rs = stmt.executeQuery(query);

		if (rs.next()) {
			req.setAttribute("emailexist", "you are already signup");
			return "Signup";

		} else {

			
			String otp = genrateOtp(6);
			System.out.print("your otp is "+otp);
			
			sendMail(email, otp, "your one time otp");
			
			
			
			Connection con1 = jdbcTemplate.getDataSource().getConnection();
			Statement stmt1 = con1.createStatement();
			String query1 = "insert into user_info (name,email,psw,otp,mobile) values ('" + name + "','" + email + "','" + psw+ "','"+otp+"','"+mobile+"')";
			
			stmt1.execute(query1);

		}
		req.setAttribute("email", email);
		
		return "otp";//otpverificationpage
	}
	

	public void sendMail(String emailTo, String body, String subject) {
		// TODO Auto-generated method stub
		Properties p= new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.auth", "true");
		
		MailAuthenticator m= new MailAuthenticator("coursevenia@gmail.com", "Course123");
		Session session= Session.getInstance(p, m);
		session.setDebug(true);
		MimeMessage msg= new MimeMessage(session);
		
		try {
			msg.setFrom("roomdekho84@gmail.com");
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			msg.setSubject(subject);
			msg.setText(body);
			 Transport.send(msg);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private String genrateOtp(int size) {
		StringBuilder sb = new StringBuilder();
		SplittableRandom st = new SplittableRandom();
		for(int i=0;i<size;i++) {
			int rn = st.nextInt(0 ,9);
			sb.append(rn);
		}
		return sb.toString();
	}


	@PostMapping("/login")
	public String login(HttpServletRequest req) throws SQLException {
		String email = req.getParameter("email");
	
		String psw = req.getParameter("psw");
		//System.out.println(email+""+psw);
		Connection con = jdbcTemplate.getDataSource().getConnection();
		Statement stmt = con.createStatement();
		String query = "select * from  user_info where email='" + email + "' ";
		ResultSet rs = stmt.executeQuery(query);

		if (rs.next()) {
			if (rs.getString("psw").equals(psw)) {
				req.setAttribute("text", "you are Succefully login");
				//System.out.println("aagay");
				return "index";// home
			} else {
				req.setAttribute("text", "your password or email is not valid");
				return "login";
			}
		}
		req.setAttribute("text", "you are not registerd yet");
		return "login";
	}
	@PostMapping("/otpVerification")
	
	public String otpVerification(HttpServletRequest req) throws SQLException {
	String otp = req.getParameter("otp");
	String email = req.getParameter("email");
	
	Connection con = jdbcTemplate.getDataSource().getConnection();
	Statement stmt = con.createStatement();
	String query = "select * from  user_info where email='" + email + "' ";
	ResultSet rs = stmt.executeQuery(query);

	if(rs.next()) {
		if(rs.getString("otp").equals(otp)) {
			
			Connection con1 = jdbcTemplate.getDataSource().getConnection();
			Statement stmt1 = con1.createStatement();
			String query1 = "update user_info set is_verified=1 where email='"+email+"'";
			stmt1.executeUpdate(query1);
			
			req.setAttribute("text", "your otp is succefully verified");
		}
		else {
			req.setAttribute("text", "your otp is not valid");
		}
	}
		
	return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
