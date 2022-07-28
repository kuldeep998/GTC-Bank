package com.gtcsys.code.user;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gtcsys.code.GtcBankApplication;
import com.gtcsys.code.custom.ApplyLoan;
import com.gtcsys.code.custom.LoginDetails;
import com.gtcsys.code.loan.LoanEntity;
import com.gtcsys.code.loan.LoanRepository;
import com.gtcsys.code.loantype.LoanTypeEntity;
import com.gtcsys.code.loantype.LoanTypeRepository;
import com.gtcsys.code.utility.JwtUtil;
import com.gtcsys.code.utility.Message;
import com.gtcsys.code.utility.Response;
import com.gtcsys.code.utility.Utility;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

	private static final Logger logger = Logger.getLogger(GtcBankApplication.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LoanRepository loanRepo;

	@Autowired
	private LoanTypeRepository loanTypeRepo;

	@Autowired
	private JwtUtil jwtTokenUtil;

	// registered new user
	// 28/07/2022
	@Override
	public Response registration(UserEntity user, HttpServletRequest request) {
		logger.info("new user registration method calling " + user.toString());
		Response response = new Response();
		try {
			UserEntity fetchUser = userRepo.findByEmail(user.getEmail());
			if (fetchUser == null) {
				user.setCreatedDate(Utility.getDate());
				user.setModifiedDate(Utility.getDate());
				user.setPassword(bcryptEncoder.encode(user.getPassword()));
				user.setRole("ROLE_USER");
				System.out.println(user.toString());
				userRepo.save(user);
				response.setSuccess(true, 200);
				response.addMessage(Message.REGISTER);
				logger.info("new user registered successfully");
				return response;
			} else {
				response.setSuccess(false, 400);
				response.addMessage("Email already exist");
				logger.info("email already exist");
				return response;

			}
		} catch (Exception e) {
			logger.error(" Expcetion Occure" + e.getMessage());
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		UserEntity user = userRepo.findByEmail(email);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
			return new User(user.getEmail(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + email);

	}

	// login existing user by email and password
	// 28/07/2022
	@Override
	public Response login(UserEntity user, HttpServletRequest request) {
		logger.info("login method calling " + user.toString());
		Response response = new Response();
		try {

			try {
				authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			} catch (Exception e) {
				response.setSuccess(false, 400);
				response.addMessage(Message.INVALID_CREDENTIAL);
				logger.info("invalid credential");
				return response;
			}

			final UserDetails userDetails = loadUserByUsername(user.getEmail());
			if (userDetails == null) {
				response.setSuccess(false, 400);
				response.addMessage(Message.INVALID_CREDENTIAL);
				logger.info("invalid credential");
				return response;
			} else {

				String token = jwtTokenUtil.generateToken(userDetails);

				LoginDetails loginDetails = new LoginDetails();
				loginDetails.setEmail(user.getEmail());
				loginDetails.setName(user.getFirstName() + user.getLastName());
				loginDetails.setAuthToken(token);
				response.setSuccess(true, 200);
				response.addMessage(Message.LOGIN);
				response.addData(loginDetails);
				logger.info("login successfully");
				return response;

			}

		} catch (Exception e) {
			logger.error(" Expcetion Occure" + e.getMessage());
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

	// apply for new loan
	// 28/07/2022
	@Override
	public Response applyLoan(ApplyLoan loan, HttpServletRequest request) {
		logger.info("apply loan method block calling " + loan.toString());
		Response response = new Response();
		try {

			UserEntity fetchUser = userRepo.findById(loan.getUserId());
			if (fetchUser == null) {
				response.setSuccess(false, 400);
				response.addMessage(" Invalid User details ");
				logger.info("invalid user details");
				return response;
			}

			LoanTypeEntity fetchLoanType = loanTypeRepo.findById(loan.getLoanTypeId());

			if (fetchLoanType == null) {
				response.setSuccess(false, 400);
				response.addMessage("Invalid Loan Type Details ");
				logger.info("invalid load type details");
				return response;
			}

			LoanEntity fetchLoan = loanRepo.findByUserIdAndLoanStatus(loan.getUserId(), true);
			if (fetchLoan == null) {
				LoanEntity newLoan = new LoanEntity();
				newLoan.setCreatedDate(Utility.getDate());
				newLoan.setModifiedDate(Utility.getDate());
				newLoan.setLoanInterest(fetchLoanType.getInterest());
				newLoan.setLoanTypeAmount(fetchLoanType.getAmount());
				newLoan.setLoanType(fetchLoanType.getLoanType());
				newLoan.setStatus(Message.LoanStatus.PENDING.toString());
				newLoan.setRequestLoanAmount(loan.getRequestLoanAmount());
				newLoan.setUserId(fetchUser.getId());
				newLoan.setActive(true);
				loanRepo.save(newLoan);
				response.setSuccess(true, 200);
				response.addMessage("Your request submitted successfully. Team will contact you soon");
				logger.info("loan request successfully submited");
				return response;

			} else {
				response.setSuccess(false, 400);
				response.addMessage("Already " + fetchLoan.getLoanType() + " request under process");
				logger.info("already one is submitted");
				return response;
			}

		} catch (Exception e) {
			logger.error(" Expcetion Occure" + e.getMessage());
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

	@Override
	public Response myLoans(int id, HttpServletRequest request) {
		logger.info("my loans method calling");
		Response response = new Response();
		try {

			List<LoanEntity> fetchLoan = loanRepo.findByUserId(id);

			if (fetchLoan.isEmpty()) {
				response.setSuccess(false, 400);
				response.addMessage(Message.NOTFOUND);
			
				return response;
			} else {
				response.setSuccess(true, 200);
				response.addMessage(Message.FETCH);
				response.addData(fetchLoan);
				return response;
			}

		} catch (Exception e) {
			logger.error(" Expcetion Occure" + e.getMessage());
			e.printStackTrace();
			response.setSuccess(false, 500);
			response.addMessage(Message.SERVERERROR);
			return response;
		}
	}

}
