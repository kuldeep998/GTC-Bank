package com.gtcsys.code.loan;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Loan")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9128783431238891069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "UserId")
	private int userId;

	@Column(name = "loanType")
	private String loanType;

	@Column(name = "LoanTypeAmount")
	private float loanTypeAmount = 0.0f;

	@Column(name = "loanInterest")
	private float loanInterest = 0.0f;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "Status")
	private String status;

	@Column(name = "RequestLoanAmount")
	private float requestLoanAmount;

	@Column(name = "createdDate")
	private Timestamp createdDate;

	@Column(name = "modifiedDate")
	private Timestamp modifiedDate;

}
