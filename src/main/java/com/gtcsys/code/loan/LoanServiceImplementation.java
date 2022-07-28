package com.gtcsys.code.loan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtcsys.code.loantype.LoanTypeServiceImplementation;
import com.gtcsys.code.utility.Message;
import com.gtcsys.code.utility.Response;
import com.gtcsys.code.utility.Utility;

@Service
public class LoanServiceImplementation implements LoanService {

	private static final Logger logger = Logger.getLogger(LoanServiceImplementation.class);

	@Autowired
	private LoanRepository loanRepo;

	/*
	 * update loan details 28/07/2022
	 */
	@Override
	public Response update(LoanEntity dto, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanEntity fetchLoanDetails = loanRepo.findById(dto.getId());
			if (fetchLoanDetails == null) {
				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
				return response;

			} else {

				// only update request loan amount for perform update operation
				fetchLoanDetails.setStatus(dto.getStatus());
				fetchLoanDetails.setActive(dto.isActive());

				fetchLoanDetails.setModifiedDate(Utility.getDate());
				fetchLoanDetails = loanRepo.save(fetchLoanDetails);
				response.setSuccess(true, 200);
				response.addData(fetchLoanDetails);
				response.addMessage(Message.UPDATE);
				return response;

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

	/*
	 * delete loan details by id 28/07/2022
	 */
	@Override
	public Response delete(int id, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanEntity fetchLoanDetails = loanRepo.findById(id);
			if (fetchLoanDetails == null) {

				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
				return response;

			} else {

				loanRepo.deleteById(id);
				response.setSuccess(true, 200);
				response.addMessage(Message.DELETE);
				return response;

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

	/*
	 * get loan details by id 28/07/2022
	 */
	@Override
	public Response get(int id, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanEntity fetchLoanDetails = loanRepo.findById(id);
			if (fetchLoanDetails == null) {
				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
				return response;

			} else {
				response.setSuccess(true, 200);
				response.addData(fetchLoanDetails);
				response.addMessage(Message.FETCH);
				return response;

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

	/*
	 * fetch loan details 28/07/2022
	 */
	@Override
	public Response getAll(HttpServletRequest request) {
		Response response = new Response();
		try {

			List<LoanEntity> fetchLoanList = loanRepo.findAll();
			if (fetchLoanList.isEmpty()) {
				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
				return response;

			} else {
				response.setSuccess(true, 200);
				response.addData(fetchLoanList);
				response.addMessage(Message.FETCH);
				return response;

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

}
