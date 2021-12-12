package service.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import service.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{

}
