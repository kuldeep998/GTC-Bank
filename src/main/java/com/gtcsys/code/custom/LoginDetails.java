package com.gtcsys.code.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// return login details 
// kuldeep 28/07/2022

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDetails {

	private int id;
	private String email;
	private String name;
	private String authToken;

}
