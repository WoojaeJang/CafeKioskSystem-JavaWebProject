package coffeeOrder.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeOrderDTO {
	
	private int orderNo;
	private String cid;
	private String fname;
	private int payment;
	private String pname;
	private Timestamp orderTime;
	private String orderCheck;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CoffeeOrderDTO [orderNo=");
		builder.append(orderNo);
		builder.append(", cid=");
		builder.append(cid);
		builder.append(", fname=");
		builder.append(fname);
		builder.append(", payment=");
		builder.append(payment);
		builder.append(", pname=");
		builder.append(pname);
		builder.append(", orderTime=");
		builder.append(orderTime);
		builder.append(", orderCheck=");
		builder.append(orderCheck);
		builder.append("]");
		return builder.toString();
	}
		
}
