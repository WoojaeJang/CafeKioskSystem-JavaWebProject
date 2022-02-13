package coffeeOrder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
	
	private String cid;
	private String cpw;
	private String cname;
	private String phone_number;
	private int point;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDTO [cid=");
		builder.append(cid);
		builder.append(", cpw=");
		builder.append(cpw);
		builder.append(", cname=");
		builder.append(cname);
		builder.append(", phone_number=");
		builder.append(phone_number);
		builder.append(", point=");
		builder.append(point);
		builder.append("]");
		return builder.toString();
	}	
	
}
