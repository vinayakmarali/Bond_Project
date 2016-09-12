package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGeneration {

	
	OTRIssues getOTR(int number)
	{
		
			OTRIssues o = new OTRDAO().getOTR(number);
			
				double bid = o.getBid();
				double ask = o.getAsk();
				double dBid = Math.random();
				double dAsk = Math.random();
				
				if(number%4==1)
				{
					bid = bid+dBid;
					if(o.getCoupon()==0)
						o.setBid_yield(((100-bid)/bid)*100);
					else
						o.setBid_yield((o.getCoupon()/bid)*100);
				}
				else if(number%4==2)
				{
					ask = ask-dAsk;
					if(o.getCoupon()==0)
						o.setAsk_yield(((100-ask)/ask)*100);
					else
						o.setAsk_yield((o.getCoupon()/ask)*100);
				}
				else if(number%4==3)
				{
					bid = bid-dBid;
					if(o.getCoupon()==0)
						o.setBid_yield(((100-bid)/bid)*100);
					else
						o.setBid_yield((o.getCoupon()/bid)*100);
				}
				else if(number%4==0)
				{
					ask = ask+dAsk;
					if(o.getCoupon()==0)
						o.setAsk_yield(((100-ask)/ask)*100);
					else
						o.setAsk_yield((o.getCoupon()/ask)*100);
				}
				
				o.setBid(bid);
				o.setAsk(ask);
					
			
			System.out.println("Number "+number);
			return o;
			
		
	}
	
	public static void main(String[] args) {
	
		/*int i=1;
		int number=1;
		double updateNum;
		while(i<=10)
		{
			double num = 108.5;
			double d = Math.random();
			if(number==1)
				{
					num = num+d;
					number--;
				}
			else if(number==0)
			{
				num = num-d;
				number++;
			}	
			i++;
			System.out.println(num);
		}
*/
	}

}
