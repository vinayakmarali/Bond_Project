package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller
public class OTRController {

	
	@RequestMapping(method=RequestMethod.POST,value="/updateOtr.htm")
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
			out.println("<table border='2' id='table' cellspacing='2' cellpadding='2'>");
			
			out.println("<tr><th align='center'>TICKER</th><th align='center'>BID</th><th align='center'>BID YIELD</th><th align='center'>ASK</th><th align='center'>ASK YIELD</th><th align='center'>LTP</th><th align='center'>CLOSE</th><th align='center'>%Change</th><th align='center'>OPEN</th><th align='center'>HIGH</th><th align='center'>LOW</th><th align='center' colspan='2'>ACTION</th></tr>");
			
			for(OTRIssues o:list)
			{
				String symbol = o.getIssuerId()+"/"+o.getCoupon()+"/"+o.getMaturity();
		out.println("<tr>");
				out.println("<td>"+symbol+"</td>");
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
				out.println("<td><button type='button' name='click' value='buy1-"+o.getCusip()+"-"+o.getBid()+"' onclick='popItUp(this.value)'><font style='color: green'><b>Buy</b></font></button></td>");
				out.println("<td><button type='button' name='click' value='sell-"+o.getCusip()+"-"+o.getAsk()+"' onclick='popItUp(this.value)'><font style='color: red'><b>Sell</b></font></button></td>");

				out.println("</tr>");
			}
			out.println("</table>");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getLimit.htm")
	String getLimit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		String limitAmount = new DAO().getLimitOfBroker(login.getUserId());
		out.println("<h3 align='left'>Your limit is <b>"+limitAmount+"</b></h3>");
		session.setAttribute("limitAmount", limitAmount);
		return null;
	}
	
}
