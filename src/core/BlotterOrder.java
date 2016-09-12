package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class BlotterOrder {
	
public BlotterOrder(){
	super();
	}

@RequestMapping(method=RequestMethod.GET, value="/blotterorder.htm")
public String showIndex(ModelMap m,HttpServletRequest request) throws SQLException, JMSException
{
	ArrayList<Map<String,String>> list2 = new DAO().currentBlotterOrder(request);
	request.setAttribute("currentblotterorder", list2);
	Order o = new Order();
	m.addAttribute(o);
	return "BlotterOrder";
}

@RequestMapping(method=RequestMethod.POST)
public String onSubmit(@ModelAttribute("blotterorder") Order o, HttpServletRequest request) throws JMSException, SQLException{
if(request.getParameter("btn").equals("Search"))
{
	System.out.println("search");
	new ProducerBlotter().putBlotterOrder(o, request);
	ArrayList<Map<String,String>> list = new ProducerBlotter().getBlotterOrder();
	System.out.println("list 1 : " + list);
	HttpSession session  = request.getSession(true);
	request.setAttribute("blotterlist", list);
	return "BlotterOrder";
}
else if(request.getParameter("btn").equals("Save"))
{
	System.out.println("save");
	new ProducerBlotter().putEditBlotterOrder(o, request);
	return "BlotterOrder";
}
else if(request.getParameter("btn").equals("Cancel Order"))
{
	System.out.println("cancel");
	new ProducerBlotter().putCancelBlotterOrder(o, request);
	return "BlotterOrder";
}
return null;
}
}