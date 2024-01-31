package pojo;

import org.apache.juneau.annotation.Beanc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Location {
	private double lat ;
	private double lng ; 
	
	@Beanc
	public Location() {}
}
