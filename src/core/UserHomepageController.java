package core;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller
@RequestMapping(value="/otrissues.htm")
@SessionAttributes("OTRIssues")
public class UserHomepageController {
	
public UserHomepageController() {
		super();
	}

@RequestMapping(method=RequestMethod.GET)
public String showIndex(ModelMap m, HttpServletRequest request)
{
	OTRIssues otr = new OTRIssues();
	m.addAttribute(otr);
	return "Success";
}

@RequestMapping(method=RequestMethod.POST)
public String onSubmit(@ModelAttribute("OTRIssues") OTRIssues otr) throws JMSException{
	
	
	return "SuccessOTR";
}
}

