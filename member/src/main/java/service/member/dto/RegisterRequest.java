package service.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterRequest {

	@ApiModelProperty(value = "아이디", required = true, example = "jj1234")
	private String id;
	
	@ApiModelProperty(value = "비밀번호", required = false, example = "1234")
	private String password;
	
	@ApiModelProperty(value = "이름", required = false, example = "이**")
	private String name;

	@ApiModelProperty(value = "이메일", required = false, example = "jj1234@jj1234.com")
	private String email;
	
	@ApiModelProperty(value = "전화번호", required = false, example = "010-422*-****")
	private String phone;
	
	@ApiModelProperty(value = "생년월일", required = false, example = "199*-**-**")
	private String birthday;
	
	protected RegisterRequest() {
		
	}
	
}
