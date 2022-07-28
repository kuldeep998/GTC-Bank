package com.gtcsys.code.user;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4885572586687382990L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "AuthToken")
	private String authToken;

	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Column(name = "ModifiedDate")
	private Timestamp modifiedDate;

	@Column(name = "Role")
	private String role;
}
