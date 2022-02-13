package coffeeOrder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PayDTO {
	
	private String pid;
	private String pname;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayDTO [pid=");
		builder.append(pid);
		builder.append(", pname=");
		builder.append(pname);
		builder.append("]");
		return builder.toString();
	}	
	
}
