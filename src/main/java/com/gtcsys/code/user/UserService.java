package com.gtcsys.code.user;

import javax.servlet.http.HttpServletRequest;

import com.gtcsys.code.custom.ApplyLoan;
import com.gtcsys.code.utility.Response;

public interface UserService {

	Response registration(UserEntity user, HttpServletRequest request);

	Response login(UserEntity user, HttpServletRequest request);

	Response applyLoan(ApplyLoan loan, HttpServletRequest request);

	Response myLoans(int id, HttpServletRequest request);

}
