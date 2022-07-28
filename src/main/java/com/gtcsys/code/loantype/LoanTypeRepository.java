package com.gtcsys.code.loantype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanTypeEntity, Integer> {

	LoanTypeEntity findByLoanType(String loanType);

	LoanTypeEntity findById(int id);
}
