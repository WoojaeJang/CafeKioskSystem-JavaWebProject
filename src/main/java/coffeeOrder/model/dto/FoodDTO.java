package coffeeOrder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodDTO {
	
	private String fid;
	private String fname;
	private int price;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FoodDTO [fid=");
		builder.append(fid);
		builder.append(", fname=");
		builder.append(fname);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}	
	
}
