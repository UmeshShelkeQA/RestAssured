package pojo;

import org.apache.juneau.annotation.Beanc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class Place {
	private Location location;
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String[] types;
	private String website;
	private String language;
	private String place_id;
	
	@Beanc
	public Place() {}
}
