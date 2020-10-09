/**
 * @author Shilpi
 * @version 1.1
 */

package com.lti.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.lti.idgen.IdGenerator;

/* This class represents an entity for Policy */

@Entity
@Table (name = "policy_details")
public class Policy {
	@Id
	@Column(name = "pol_no")
	@GeneratedValue(generator = "polseq", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "polseq", strategy = "com.lti.idgen.IdGenerator", parameters = {
			@Parameter(name = IdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "PN_"),
			@Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")

	})
	private String policyNo; // stores policy no, primary key, auto generated
	
	@Column (length = 20)
	private String type; // stores plan type

	private int term; // stores plan term
	
	@Column (length = 30)
	private String expDate; // stores expiry Date
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "claim_id")
	private Claim claim; // one to one mapping, one policy has only one claim
	
	@OneToOne
	@JoinColumn (name = "reg_no")
	private Vehicle vehicle; // one to one mapping, one policy has only one vehicle
	
	// Getters and Setters
	
	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String pno) {
		this.policyNo = pno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
