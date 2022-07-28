package com.gtcsys.code.loantype;

import javax.servlet.http.HttpServletRequest;

import com.gtcsys.code.utility.Response;

public interface LoanTypeService {

	Response update(LoanTypeEntity loanType, HttpServletRequest request);

	Response delete(int id, HttpServletRequest request);

	Response get(int id, HttpServletRequest request);

	Response getAll(HttpServletRequest request);

	Response save(LoanTypeEntity loanType, HttpServletRequest request);

}
