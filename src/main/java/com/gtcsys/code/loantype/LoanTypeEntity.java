package com.gtcsys.code.loantype;

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
@Table(name = "LoanType")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4653160646439063662L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "LoanType")
	private String loanType;

	@Column(name = "Interest")
	private float interest = 0.0f;

	@Column(name = "Amount")
	private float amount = 0.0f;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "createdDate")
	private Timestamp createdDate;

	@Column(name = "modifiedDate")
	private Timestamp modifiedDate;

}
