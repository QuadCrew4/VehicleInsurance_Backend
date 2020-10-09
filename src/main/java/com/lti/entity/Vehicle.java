/**
 * @author Priyam
 * @version 1.0
 */
package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;


/* This class represents an entity for Vehicle */

@Entity
@Table (name="vehicle_details")
public class Vehicle {
	@Id
	@Column(length = 20, name = "reg_no")
	private String regNo; // stores registration no, primary key
	
	@Column(length = 10)
	private String type; // stores vehicle type
	
	@Column(length = 20)
	private String manufacturer; // stores manufacturer
	
	@Column(length = 20)
	private String model; // stores model
	
	private double price; // stores price
	
	@NaturalId
	@Column(length = 20)
	private String driverLicence; // stores driverLicence
	
	@Column(length = 20)
	private String purchaseDate; // stores purchase date
	
	@NaturalId
	@Column(length = 20)
	private String engineNo; // stores engine number
	
	@NaturalId
	@Column(length = 20)
	private String chasisNo; // stores chasis number
	
	//Getters and Setters

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String mfr) {
		this.manufacturer = mfr;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(String dlicence) {
		this.driverLicence = dlicence;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String pdate) {
		this.purchaseDate = pdate;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engNo) {
		this.engineNo = engNo;
	}

	public String getChasisNo() {
		return chasisNo;
	}

	public void setChasisNo(String chNo) {
		this.chasisNo = chNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
