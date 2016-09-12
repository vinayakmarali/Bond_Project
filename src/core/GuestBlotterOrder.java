package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
@SessionAttributes("Order")
public class GuestBlotterOrder {
	
public GuestBlotterOrder(){
	super();
	}

@RequestMapping(method=RequestMethod.GET, value="/guestblotterorder.htm")
public String showIndex(ModelMap m,HttpServletRequest request) throws SQLException
{
	ArrayList<Map<String,String>> list2 = new DAO().currentBlotterOrder(request);
	request.setAttribute("currentblotterorder", list2);
	Order o = new Order();
	m.addAttribute(o);
	return "GuestBlotterOrder";
}

@RequestMapping(method=RequestMethod.POST)
public String onSubmit(@ModelAttribute("blotterorder") Order o, HttpServletRequest request) throws JMSException, SQLException{
	return "GuestBlotterOrder";
}
}