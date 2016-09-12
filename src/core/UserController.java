package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller
public class UserController {
	
public UserController() {
		super();
	}

@RequestMapping(method=RequestMethod.GET,value="/userlogin.htm")
public String showIndex(HttpServletRequest request)
{
	/*HttpSession session = request.getSession(true);
	Login login = (Login)session.getAttribute("login");
	if(login!=null)
		return "home";*/
	Map<String, Object> map = new DAO().getOTR();
	request.setAttribute("map", map);
	return "index";
}

@RequestMapping(method=RequestMethod.POST,value="/updateOtrIndex.htm")
public @ResponseBody String updateOtr(HttpServletResponse response)
{
	Map<String, Object> map = new DAO().getOTR();
	List<OTRIssues> list = (List<OTRIssues>)map.get("OTRISSUES");

	Double ltp = (Double)map.get("LTP");
	Double close = (Double)map.get("CLOSE");
	Double change = (ltp-close)*100/ltp;
	Double open = (Double)map.get("OPEN");
	Double high = (Double)map.get("HIGH");
	Double low = (Double)map.get("LOW");
	
	try {
		PrintWriter out = response.getWriter();
		out.println("<table id='table' border='2' cellspacing='2' cellpadding='2'>");
		
		out.println("<tr><th>TICKER</th><th>BID</th><th>BID YIELD</th><th>ASK</th><th>ASK YIELD</th><th>LTP</th><th>CLOSE</th><th>%Change</th><th>OPEN</th><th>HIGH</th><th>LOW</th></tr>");
		
		for(OTRIssues o:list)
		{
			String symbol = o.getIssuerId()+"/"+o.getCoupon()+"/"+o.getMaturity();
			out.println("<tr><td>"+symbol+"</td>");
			if(o.getBid_prev()==0)
			{
				out.println("<td style='color: #000000;'><b>"+o.getBid()+"</b></td>");
				out.println("<td style='color: #000000;'><b>"+o.getBid_yield()+"</b></td>");
			}
			else if(o.getBid()>o.getBid_prev())
			{
				/* out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getBid()+"</b></td>");
				out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getBid_yield()+"</b></td>"); */
				 out.println("<td style='color: green;'><b>"+o.getBid()+"</b></td>");
					out.println("<td style='color: red'><b>"+o.getBid_yield()+"</b></td>"); 
			}
			else
			{
			/* 	out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getBid()+"</b></td>");
				out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getBid_yield()+"</b></td>");
			 */
				out.println("<td style='color: red'><b>"+o.getBid()+"</b></td>");
				out.println("<td style='color: green;'><b>"+o.getBid_yield()+"</b></td>");
			 
			
			}
			
			
			if(o.getAsk_prev()==0)
			{
				out.println("<td style='color: #000000;'><b>"+o.getAsk()+"</b></td>");
				out.println("<td style='color: #000000;'><b>"+o.getAsk_yield()+"</b></td>");
			}
			else if(o.getAsk()>o.getAsk_prev())
			{
				/* out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getAsk()+"</b></td>");
				out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getAsk_yield()+"</b></td>"); */
				
				 out.println("<td style='color: green'><b>"+o.getAsk()+"</b></td>");
				out.println("<td style='color:red'><b>"+o.getAsk_yield()+"</b></td>"); 
			}
			else
			{
				/* out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getAsk()+"</b></td>");
				out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getAsk_yield()+"</b></td>"); */
				out.println("<td style='color: red'><b>"+o.getAsk()+"</b></td>");
				out.println("<td style='color:green'><b>"+o.getAsk_yield()+"</b></td>");
			}
			
			out.println("<td>"+ltp+"</td>");
			out.println("<td>"+close+"</td>");
			out.println("<td>"+change+"</td>");
			out.println("<td>"+open+"</td>");
			out.println("<td>"+high+"</td>");
			out.println("<td>"+low+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	} catch (IOException e) {
		
		e.printStackTrace();
	}	
	return null;
}


@RequestMapping(method=RequestMethod.POST)
public String onSubmitLogin(HttpServletRequest request) throws JMSException{
	
	String username = request.getParameter("username");
	String password  =request.getParameter("password");
	
	
	
	Login login = new Login(username, password, "User");
	boolean b = new DAO().validateUser(login);
	
	if(b==true)
	{
		
		Map<String, Object> map = new DAO().getOTR();
		String limitAmount = new DAO().getLimitOfBroker(login.getUserId());
		request.setAttribute("map", map);
		HttpSession session  = request.getSession();
		session.setAttribute("login", login);
		session.setAttribute("uname", login.getUserId());

		session.setAttribute("limitAmount", limitAmount);
		return "home";
	}
	else
		return "InvalidUser";
}

@RequestMapping(method=RequestMethod.GET,value="/trade.htm")
public String onTradeClick(HttpServletRequest request)
{
	Map<String, Object> map = new DAO().getOTR();
	request.setAttribute("map", map);
	return "home";
}

}

