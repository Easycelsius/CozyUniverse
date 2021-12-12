package service.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteRequest {
	
	@ApiModelProperty(value = "아이디", required = true, example = "jj1234")
	private String id;

	protected DeleteRequest() {
		
	}
}
