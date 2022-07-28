package com.gtcsys.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtcsys.code.utility.Response;
import com.gtcsys.code.utility.Utility;
// home controller to check application working or not 
// kuldeep 28/07/2022
@RestController
@RequestMapping("/home")
public class HomeController {

	@RequestMapping(value = "/check", method = { RequestMethod.GET }, produces = { "application/json" })
	Response checkController(HttpServletRequest request) {
		Response response = new Response();

		response.setSuccess(true, 200);
		response.addMessage("Welcome to Home Controller " + Utility.getCurrentDate());

		return response;
	}

}
