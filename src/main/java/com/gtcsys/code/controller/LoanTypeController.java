package com.gtcsys.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtcsys.code.loantype.LoanTypeEntity;
import com.gtcsys.code.loantype.LoanTypeService;
import com.gtcsys.code.utility.Response;
// use to perform loan type  crud operation 
// kuldeep 28/07/2022
@RestController
@RequestMapping("/loantype")
public class LoanTypeController {

	@Autowired
	private LoanTypeService loanTypeService;

	@RequestMapping(value = "/save", method = { RequestMethod.POST }, consumes = { "application/json" })
	Response save(@RequestBody final LoanTypeEntity loanType, HttpServletRequest request) {
		return loanTypeService.save(loanType, request);
	}

	@RequestMapping(value = "/update", method = { RequestMethod.PUT }, consumes = { "application/json" })
	Response update(@RequestBody final LoanTypeEntity loanType, HttpServletRequest request) {
		return loanTypeService.update(loanType, request);
	}

	@RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE }, produces = { "application/json" })
	Response delete(@PathVariable final int id, HttpServletRequest request) {
		return loanTypeService.delete(id, request);
	}

	@RequestMapping(value = "/get/{id}", method = { RequestMethod.GET }, produces = { "application/json" })
	Response get(@PathVariable final int id, HttpServletRequest request) {
		return loanTypeService.get(id, request);
	}

	@RequestMapping(value = "/getAll", method = { RequestMethod.GET }, produces = { "application/json" })
	Response getAll(HttpServletRequest request) {
		return loanTypeService.getAll(request);
	}
}
