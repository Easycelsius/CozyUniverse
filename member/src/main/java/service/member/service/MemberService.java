package service.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.member.entity.Member;
import service.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberService {
	
	@Autowired
	private MemberRepository mr;
	
	/* 회원 조회 */
	
	// get info
	public Optional<Member> getMember(String memberId) {
		return mr.findById(memberId);
	}
	
	// get info
	public Member getMember(Member m) {
		return mr.getById(m.getId());
	}
	
	// get memberList
	public List<Member> getMembers() {
		return mr.findAll();
	}
	
	/* 회원가입 및 삭제 */
	
	// add, mod
	@ Transactional
	public Member register(Member m) {
		return mr.save(m);
	}
	
	// delete
	@ Transactional
	public void delete(Member m) {
		mr.delete(m);
	}
	
}
