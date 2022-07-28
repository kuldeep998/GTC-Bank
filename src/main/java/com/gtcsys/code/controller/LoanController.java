package com.gtcsys.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtcsys.code.loan.LoanEntity;
import com.gtcsys.code.loan.LoanService;
import com.gtcsys.code.utility.Response;

// loan controller to perform crud operation
// kuldeep 28/07/2022
@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@RequestMapping(value = "/update", method = { RequestMethod.PUT }, consumes = { "application/json" })
	Response update(@RequestBody final LoanEntity loanType, HttpServletRequest request) {
		return loanService.update(loanType, request);
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE }, produces = { "application/json" })
	Response delete(@PathVariable final int id, HttpServletRequest request) {
		return loanService.delete(id, request);
	}

	@RequestMapping(value = "/get/{id}", method = { RequestMethod.GET }, produces = { "application/json" })
	Response get(@PathVariable final int id, HttpServletRequest request) {
		return loanService.get(id, request);
	}

	@RequestMapping(value = "/getAll", method = { RequestMethod.GET }, produces = { "application/json" })
	Response getAll(HttpServletRequest request) {
		return loanService.getAll(request);
	}
}
