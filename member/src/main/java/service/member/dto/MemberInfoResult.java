package service.member.dto;

import lombok.Getter;
import lombok.Setter;
import service.member.entity.Member;

@Getter @Setter
public class MemberInfoResult {
	
	private String id; // 아이디
	
	public MemberInfoResult(Member m) {
		this.id = m.getId();
	}
}
