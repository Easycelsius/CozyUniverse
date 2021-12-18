package service.member.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import service.member.dto.DeleteRequest;
import service.member.dto.MemberInfoResult;
import service.member.dto.RegisterRequest;
import service.member.dto.RegisterResult;
import service.member.entity.Member;
import service.member.error.NotFoundException;
import service.member.service.MemberService;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
@Api(tags = {"회원 컨트롤러"})
public class UserController {
	
	@Autowired
	MemberService ms;
	
	
	/* 회원상세조회 */
	final String methodGetInfo = "회원 조회";
	
	@ApiOperation(value = methodGetInfo + "API", notes = "회원 조회를 위한 api 입니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = methodGetInfo + " 성공"),
	})
	
	@GetMapping("/info/{memberId}")
	public MemberInfoResult getMember(@PathVariable("memberId") String memberId) {
		return ms.getMember(memberId).map(MemberInfoResult::new).orElseThrow(()->new NotFoundException("회원 정보를 조회할 수 없습니다."));
	}
	
	
	/* 회원목록조회 */
	final String methodGetList = "회원 목록 조회";
	
	@ApiOperation(value = methodGetList + "API", notes = "회원 목록 조회를 위한 api 입니다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = methodGetList + " 성공"),
	})
	
	@GetMapping("/info")
	public Page<MemberInfoResult> getMemberList(
			@RequestParam(value = "page", required=false, defaultValue = "0") String page,
			@RequestParam(value = "size", required=true, defaultValue = "10") String size
		) {
		
		Pageable p = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by("joinDate").descending());

		return ms.getMembers(p).map(MemberInfoResult::new);
	}
	
	
	/* 회원등록 */
	final String methodJoin = "회원 등록";
	
	@ApiOperation(value = methodJoin + "API", notes = "회원 등록을 위한 api 입니다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = methodJoin + " 성공"),
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

	
	/* 회원삭제 */
	final String methodDelete = "회원 삭제";
	
	@ApiOperation(value = methodDelete + "API", notes = "회원 삭제를 위한 api 입니다.")
	@ApiResponses({
		@ApiResponse(code = 201, message = methodDelete + " 성공"),
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
