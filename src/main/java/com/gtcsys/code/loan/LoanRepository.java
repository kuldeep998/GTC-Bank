package com.gtcsys.code.loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {

	@Query(value = "select * from Loan where user_id=?1 and is_active=?2", nativeQuery = true)
	LoanEntity findByUserIdAndLoanStatus(int userId, boolean isActive);

	LoanEntity findByLoanType(String loanType);

	LoanEntity findById(int id);

	List<LoanEntity> findByUserId(int userId);

}
