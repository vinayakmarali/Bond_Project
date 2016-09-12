package core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
public class RegisterController {
	

	
	@RequestMapping(method=RequestMethod.GET,value="/Register.htm")
	public String showRegister(HttpServletRequest request) throws JMSException
	{
		TestForRegister test=new TestForRegister();
       test.registerModule();
	  ArrayList<ArrayList<Map<String, String>>> list=test.getRegisterData();
	
	request.setAttribute("list", list);
		return "Registration";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(HttpServletRequest request) throws JMSException{
		
	System.out.println("post called");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName=request.getParameter("firstname");
		String  lastName=request.getParameter("lastname");
		String firmId=request.getParameter("firmname");
		String bookId=request.getParameter("bookname");
		String bankAccNo=request.getParameter("accountno");
		String depositoryAccNo=request.getParameter("dpaccountno");
		
		DAO d=new DAO();
		d.saveRegistrationData(username,password,firstName,lastName,firmId,bookId,bankAccNo,depositoryAccNo);
		return "SuccessRegister";
	}
}
