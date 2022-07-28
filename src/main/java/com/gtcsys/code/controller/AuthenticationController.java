package com.gtcsys.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtcsys.code.user.UserEntity;
import com.gtcsys.code.user.UserService;
import com.gtcsys.code.utility.Response;

// authentication controller for login and registration 
// kuldeep 28/07/2022

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = { RequestMethod.POST }, consumes = { "application/json" })
	Response signup(@RequestBody final UserEntity user, HttpServletRequest request) {
		return userService.registration(user, request);
	}

	@RequestMapping(value = "/signin", method = { RequestMethod.POST }, consumes = { "application/json" })
	Response signin(@RequestBody final UserEntity user, HttpServletRequest request) {
		return userService.login(user, request);
	}

}
