package com.gtcsys.code.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// read apply loan input form client side 
// kuldeep 28/07/2022
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplyLoan {

	private int loanTypeId;
	private int userId;
	private float requestLoanAmount;
}
