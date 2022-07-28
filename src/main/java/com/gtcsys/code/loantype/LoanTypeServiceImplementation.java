package com.gtcsys.code.loantype;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtcsys.code.utility.Message;
import com.gtcsys.code.utility.Response;
import com.gtcsys.code.utility.Utility;

@Service
public class LoanTypeServiceImplementation implements LoanTypeService {

	private static final Logger logger = Logger.getLogger(LoanTypeServiceImplementation.class);

	@Autowired
	private LoanTypeRepository loanTypeRepo;

	/*
	 * save loan type details 28/07/2022
	 */
	@Override
	public Response save(LoanTypeEntity dto, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanTypeEntity fetchLoanDetails = loanTypeRepo.findByLoanType(dto.getLoanType());
			if (fetchLoanDetails == null) {
				dto.setCreatedDate(Utility.getDate());
				dto.setModifiedDate(Utility.getDate());
				fetchLoanDetails = loanTypeRepo.save(dto);
				response.setSuccess(true, 200);
				response.addData(fetchLoanDetails);
				response.addMessage(Message.SAVE);
				return response;
			} else {
				response.setSuccess(false, 400);
				response.addMessage("Type " + Message.ALREADYEXIST);
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
	 * update loan type details 28/07/2022
	 */
	@Override
	public Response update(LoanTypeEntity dto, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanTypeEntity fetchLoanDetails = loanTypeRepo.findById(dto.getId());
			if (fetchLoanDetails == null) {
				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
				return response;

			} else {

				if (dto.getLoanType().equalsIgnoreCase(fetchLoanDetails.getLoanType())) {
					fetchLoanDetails.setLoanType(dto.getLoanType());
				} else {
					LoanTypeEntity fetch_LoanType = loanTypeRepo.findByLoanType(dto.getLoanType());
					if (fetch_LoanType != null) {
						response.setSuccess(false, 400);
						response.addMessage("Loan type " + Message.ALREADYEXIST);
						return response;
					} else {
						fetchLoanDetails.setLoanType(dto.getLoanType());
					}
				}

				fetchLoanDetails.setModifiedDate(Utility.getDate());
				fetchLoanDetails.setInterest(dto.getInterest());
				fetchLoanDetails.setAmount(dto.getAmount());
				fetchLoanDetails.setActive(dto.isActive());
				fetchLoanDetails = loanTypeRepo.save(fetchLoanDetails);
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
	 * delete loan type details by id 28/07/2022
	 */
	@Override
	public Response delete(int id, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanTypeEntity fetchLoanDetails = loanTypeRepo.findById(id);
			if (fetchLoanDetails == null) {

				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
				return response;

			} else {

				loanTypeRepo.deleteById(id);
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
	 * get loan type details by id 28/07/2022
	 */
	@Override
	public Response get(int id, HttpServletRequest request) {
		Response response = new Response();
		try {

			LoanTypeEntity fetchLoanDetails = loanTypeRepo.findById(id);
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
	 * fetch loan type details 28/07/2022
	 */
	@Override
	public Response getAll(HttpServletRequest request) {
		Response response = new Response();
		try {

			List<LoanTypeEntity> fetchLoanList = loanTypeRepo.findAll();
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
