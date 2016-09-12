package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

public class CalculateAccruedInterest {

	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}
	
	public double getAccruedInterest(String getDate, String coupon)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date date1=null;
		try {
			date1 = simpleDateFormat.parse(getDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		String currDate="";
		
		calendar.add(Calendar.YEAR, 1);
		Date d = calendar.getTime();
		if(d.after(date))
		{
			calendar.add(Calendar.YEAR, -1);
			d = calendar.getTime();
			double days = new CalculateAccruedInterest().daysBetween(d, date);
			double accruedInterest = (days/365)*Double.parseDouble(coupon);
			System.out.println(accruedInterest);
			if(accruedInterest<0)
				return accruedInterest*(-1);
			return accruedInterest;
		}
		while(true)
		{
			calendar.add(Calendar.YEAR, 1);
			d = calendar.getTime();
			if(d.after(date))
			{
				calendar.add(Calendar.YEAR, -1);
				d = calendar.getTime();
				break;
			}
		}
		double days = new CalculateAccruedInterest().daysBetween(d, date);
		double accruedInterest = (days/365)*Double.parseDouble(coupon);
		//System.out.println(accruedInterest);
		if(accruedInterest<0)
			return accruedInterest*(-1);
		return accruedInterest;
		
	}
	
	
	public static void main(String[] args) {
		new CalculateAccruedInterest().getAccruedInterest("2013-01-15","4.5");

	}

}
