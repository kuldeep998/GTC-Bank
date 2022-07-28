package com.gtcsys.code.loan;

import javax.servlet.http.HttpServletRequest;

import com.gtcsys.code.utility.Response;

public interface LoanService {

	
	
	
	Response update(LoanEntity loanType, HttpServletRequest request);

	Response delete(int id, HttpServletRequest request);

	Response get(int id, HttpServletRequest request);

	Response getAll(HttpServletRequest request);

}
