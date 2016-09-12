package core;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BondController {

	
	@RequestMapping(method=RequestMethod.GET,value="/tradeBond.htm")
	public String onClickBuy(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("login");
		String getData[] = request.getParameter("cusip").split("-");
		/*System.out.println("getBUY "+getData[0]+"---"+getData[1]);*/
		String cusip = getData[1];
		
		/*System.out.println("CUSIP = "+cusip);*/
		request.setAttribute("cusip", cusip);
		Map<String, Object> map = new HashMap<String, Object>();
		if(getData[0].equals("buy1"))
		{
			new TestForBuy().askBuyData(login.getUserId(),cusip);
			request.setAttribute("bid", getData[2]);
			request.setAttribute("trade", "buy");
			map = new TestForBuy().getBuyData(request);
		}
		else if(getData[0].equals("sell"))
		{
			new TestForSell().SellModule(login.getUserId(),cusip);
			request.setAttribute("ask", getData[2]);
			request.setAttribute("trade", "sell");
			map = new TestForSell().getSellData(request);
		}
		double accruedInterest = 0;
		if(map.get("FIRSTCOUPON_DATE")!=null)
			accruedInterest = new CalculateAccruedInterest().getAccruedInterest(map.get("FIRSTCOUPON_DATE").toString(), map.get("COUPON").toString());
		request.setAttribute("map", map);
		request.setAttribute("accrued", accruedInterest);
		if(getData[0].equals("buy1"))
		return "TradeBuy";
		else 
			if(getData[0].equals("sell"))
			{
			Double grossAmt = new DAO().getTradePortfolio(login.getUserId(),cusip);
			if(grossAmt==null)
			{
				session.setAttribute("gross", null);
			}
			else
				session.setAttribute("gross", grossAmt.toString());
				
			return "TradeSell";
				
			}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/placeOrder.htm")
	public String placeOrder(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Login login = (Login)session.getAttribute("login");
		
		Map<String, Object> map = (Map<String, Object>) session.getAttribute("map");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy");
		Date date = new Date();
		String orderDate = simpleDateFormat.format(date);
		String currTime = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		
		String broker_id = login.getUserId();
		String cusip = (String) map.get("CUSIP");
		String direction = (String) map.get("DIRECTION");
		String coupon = (String) map.get("COUPON").toString();
		String maturity = (String)map.get("MATURITY_DATE");
		String notional = request.getParameter("principal");
		String price = request.getParameter("price");
		String yield = request.getParameter("yield");
		String status = "OPEN";
		String order_date = orderDate;
		String settlement_date = orderDate;
		String order_time = currTime;
		String issuer = (String) map.get("ISSUER_ID").toString();
		String tenor = (String) map.get("TENOR").toString();
		String value_till = request.getParameter("radio2");
		String user_order = request.getParameter("order_id");
		
		System.out.println("Place an order");
		System.out.println(broker_id+"----"+cusip+"----"+direction+"----"+coupon+"----"+maturity+"----"+notional+"----"+price+"----"+yield+"----"+status+"----"+order_date+"----"+settlement_date+"----"+order_time+"----"+issuer+"----"+tenor+"----"+value_till+"----"+user_order);
		
		String limitAmount = request.getParameter("limitAmount");
		new DAO().updateLimit(broker_id,limitAmount);
		new PlaceOrder().placeOrder(broker_id, cusip, direction, coupon, maturity, notional, price, yield, status, order_date, settlement_date, order_time, issuer, tenor, value_till, user_order);
		
		return "OrderPlace";
		
	}
}
	
		

