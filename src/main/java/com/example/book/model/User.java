package com.example.book.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

//@Data
@AllArgsConstructor 
@NoArgsConstructor // bean생성자
@Builder // 빌더패턴
@Entity
//@DynamicInsert // insert 시에 null인 필드를 지워준다. 하지만 애노테이션이 너무 많아진다.
public class User {

	@Id // Primary key
	// @GeneratedValue : 해당 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	// 여기서는 mysql을 사용하기 때문에 auto_increment를 사용하게된다.
	// @DynamicInsert : null인 필드 제외
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	//@Column(name = "id")
	private int id; // 시퀀스(오라클), auth_increment(mysql)에 해당
	
	//@Column(name = "PersonCode", nullable = false, length = 100, unique = true) // null이 될 수 없고, 중복값이 있으면 안된다(unique).
	@Column(nullable = false, length = 100, unique = true) // null이 될 수 없고, 중복값이 있으면 안된다(unique).
	private String personCode; // 사번
	
	//@Column(name = "PersonName", nullable = false, length = 100)
	@Column(nullable = false, length = 100)
	private String username; 
	
	// length를 넉넉히 주는 이유는 나중에 hash를 통해 암호화하기 때문에
	// 카카오 로그인서비스를 이용하면서 password가 null이 될 수 있기 때문에
	//@Column(name = "Password", nullable = false, length = 100) 
	@Column(nullable = false, length = 100)
	private String password; 
	
	//@Column(name = "Email", nullable = false, length = 100) 
	@Column(nullable = false, length = 100)
	private String email; 

	// 쿼리문에서 Role자체를 안가져왔을 때 Default값으로 입력되나
	// 현재는 Role을 입력안하면 자동으로 쿼리가 Role = null을 입력하는 상태
	// 쿼리에서 Role을 가져오는것을 막는 작업을 해야한다.
	// @DynamicInsert사용
	// 하지만 이런식으로 애노테이션을 계속해서 붙이게되면 소스가 지저분해지고 실력이 늘지 않는다.
	// @ColumnDefault 자체를 삭제하겠다.
	// 그리고 Controller에서 Role값을 체크해서 null값이면 입력하는 로직으로 변경하겠다.
	// model패키지에 RoleType을 정의해서 입력받을 수 있는 권한의 범위를 제한하겠다.
	// @ColumnDefault도 지운다.
	//	@ColumnDefault("'employee'") 
	//	private String Role; 
	// DB는 RoleType이라는게 없다.
	// Controller에서 RoleType을 입력해준다.
	@Enumerated(EnumType.STRING)
	//@Column(name = "Role")
	private RoleType role; 
	
	@CreationTimestamp // 시간이 자동으로 입력된다.
	//@Column(name = "InsertDT")
	private Timestamp insertDT;

	
	// option + command + s
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Timestamp getInsertDT() {
		return insertDT;
	}

	public void setInsertDT(Timestamp insertDT) {
		this.insertDT = insertDT;
	}	
	
	
}
