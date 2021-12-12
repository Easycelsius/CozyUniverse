package service.member.dto;

import java.text.SimpleDateFormat;

import lombok.Setter;
import lombok.Getter;
import service.member.entity.Member;

@Getter @Setter
public class RegisterResult {
	
	private String message; // 메시지
	private String id; // 아이디
	private String joinDate; // 가입일
	
	public RegisterResult(Member m, String message) {
		this.message = message;
		this.id = m.getId();
		this.joinDate = m.getJoinDate() == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(m.getJoinDate());
	}
}
