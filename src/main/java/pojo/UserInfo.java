package pojo;

import org.apache.juneau.annotation.Beanc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserInfo {
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String password;
	private int id;

//	comming from apache.jeneau
	@Beanc
	public UserInfo() {
		
	}
}
