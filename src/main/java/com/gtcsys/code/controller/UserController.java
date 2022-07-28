package com.gtcsys.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtcsys.code.custom.ApplyLoan;
import com.gtcsys.code.user.UserService;
import com.gtcsys.code.utility.Response;
// user controller to perform user crud
// kuldeep 28/07/2022
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/apply-loan", method = { RequestMethod.POST }, consumes = { "application/json" })
	Response signup(@RequestBody final ApplyLoan loan, HttpServletRequest request) {
		return userService.applyLoan(loan, request);
	}
	
	@RequestMapping(value = "/my-loan/{id}", method = { RequestMethod.GET }, produces = { "application/json" })
	Response myLoans(@PathVariable final int id, HttpServletRequest request) {
		return userService.myLoans(id, request);
	}

}
