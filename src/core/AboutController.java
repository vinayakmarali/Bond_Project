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
public class AboutController {
	
	@RequestMapping(method=RequestMethod.GET,value="/aboutUs.htm")
	public String showAboutUs(HttpServletRequest request) throws JMSException
	{
		return "About";
	}

	@RequestMapping(method=RequestMethod.GET,value="/aboutBonds.htm")
	public String showBonds(HttpServletRequest request) throws JMSException
	{
		return "AboutBonds";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/aboutUsAfterLogin.htm")
	public String showAboutUsAfterLogin(HttpServletRequest request) throws JMSException
	{
		return "aboutUsAfterLogin";
	}

	
	
	
}
