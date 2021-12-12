package service.member.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.member.dto.DeleteRequest;
import service.member.dto.RegisterRequest;

@Entity
@Getter @Setter
@Table(name = "MBR")
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	private String id;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "birthDay")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	@Column(name = "joinDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinDate;
	
	@Column(name = "lastVisitDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastVisitDate;
	
	@Enumerated(EnumType.STRING)
	private roleType roleType;
	
	public Member(RegisterRequest mr) throws ParseException {
		this.id = mr.getId();
		this.password = mr.getPassword();
		this.name = mr.getEmail();
		this.email = mr.getEmail();
		this.phone = mr.getPhone();
		this.birthday = (mr.getBirthday() != null) ? null : new SimpleDateFormat("yyyy-MM-dd").parse(mr.getBirthday());
		this.joinDate = new Date();
		this.roleType = roleType.USER;
	}
	
	public Member(DeleteRequest md) {
		this.id = md.getId();
	}
}
