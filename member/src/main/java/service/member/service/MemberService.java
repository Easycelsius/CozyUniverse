package service.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	// get info - 아이디 존재여부 확인용
	public Optional<Member> getMember(String memberId) {
		return mr.findById(memberId);
	}
	
	// get memberList
	public Page<Member> getMembers(Pageable p) {
		return mr.findAll(p);
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
