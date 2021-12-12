package service.member.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import service.member.dto.DeleteRequest;
import service.member.dto.RegisterRequest;
import service.member.dto.RegisterResult;
import service.member.entity.Member;
import service.member.service.MemberService;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
@Api(tags = {"회원 컨트롤러"})
public class UserController {
	
	@Autowired
	MemberService ms;
	
	final String methodJoin = "회원 등록";
	
	@ApiOperation(value = methodJoin + "API", notes = "회원 등록을 위한 api 입니다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = methodJoin + "등록 성공"),
	})
	
	@PostMapping("/info")
	public RegisterResult join(@RequestBody RegisterRequest mr) throws ParseException {
		
		String msg = "";
		
		if(ms.getMember(mr.getId()).isPresent()) {
			msg = "이미 존재하는 회원입니다.";
			return new RegisterResult(new Member(), msg);
		} else {
			Member result = ms.register(new Member(mr));
			msg = "정상적으로 등록되었습니다.";
			return new RegisterResult(result, msg);
		}
	}

	final String methodDelete = "회원 삭제";
	
	@ApiOperation(value = methodJoin + "API", notes = "회원 삭제를 위한 api 입니다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = methodJoin + "삭제 성공"),
	})
	
	@DeleteMapping("/info")
	public RegisterResult delete(@RequestBody DeleteRequest md) {
		
		String msg = "";
		Optional<Member> m = ms.getMember(md.getId());
		
		if(m.isPresent()) {
			ms.delete(m.get());
			msg = "정상적으로 삭제되었습니다.";
			return new RegisterResult(m.get(), msg);
		} else {
			msg = "존재하지 않는 회원입니다.";
			return new RegisterResult(new Member(), msg);
		}
	}
	
}
