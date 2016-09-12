package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="UNIQUETRADEID")
public class UniqueTradeId {
	
	String tradeId;

	
	
	
	public UniqueTradeId() {
		super();
	}

	


	public UniqueTradeId(String tradeId) {
		super();
		this.tradeId = tradeId;
	}




	@Id
	@Column(name="TRADE_ID")
	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	
	

}
