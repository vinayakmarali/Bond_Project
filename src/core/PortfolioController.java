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
public class PortfolioController {
	
	@RequestMapping(method=RequestMethod.GET,value="/page.htm")
	public String showRegister(HttpServletRequest request) throws JMSException
	{
		return "UnderConstruction";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(HttpServletRequest request) throws JMSException{
		return "UnderConstruction";
	}
}
