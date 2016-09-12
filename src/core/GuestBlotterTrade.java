package core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
@SessionAttributes("Trade")
public class GuestBlotterTrade {
	
public GuestBlotterTrade() {
		super();
	}

@RequestMapping(method=RequestMethod.GET, value="/guestblottertrade.htm")
public String showIndex(ModelMap m, HttpServletRequest request) throws SQLException
{
	ArrayList<Map<String,String>> list2 = new DAO().currentBlotterTrade();
	System.out.println("list 2 : " + list2);
	request.setAttribute("currentblottertrade", list2);
	Trade t = new Trade();
	m.addAttribute(t);
	return "GuestBlotterTrade";
}

@RequestMapping(method=RequestMethod.POST)
public String onSubmit(@ModelAttribute("blottertrade") Trade t,HttpServletRequest request) throws JMSException{
	
	return "GuestBlotterTrade";
}
}

