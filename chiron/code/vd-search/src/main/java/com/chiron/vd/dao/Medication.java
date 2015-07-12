package com.chiron.vd.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medication")
public class Medication {

	@Id	    
	@Column(name = "medicationId")
	private String medicationId;
	
	@Column(name = "medication")
	private String medication;
	
	@Column(name = "times")
	private String times;
	
	@Column(name = "days")
	private String days;
	
	@Column(name = "severe")
	private String power;
	
	@Column(name = "depends")
	private String depends;
	
	@Column(name = "mg")
	private String mg;
	
	@Column(name = "strparam1")
	private String strParam1;
	
	@Column(name = "strparam2")
	private String strParam2;
	
	@Override
	public String toString() {
		return "Medication [medicationId=" + medicationId + ", medication="
				+ medication + ", times=" + times + ", days=" + days
				+ ", power=" + power + ", depends=" + depends + ", mg=" + mg
				+ ", strParam1=" + strParam1 + ", strParam2=" + strParam2 + "]";
	}

	
	public String getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getDepends() {
		return depends;
	}

	public void setDepends(String depends) {
		this.depends = depends;
	}

	public String getStrParam1() {
		return strParam1;
	}

	public void setStrParam1(String strParam1) {
		this.strParam1 = strParam1;
	}

	public String getStrParam2() {
		return strParam2;
	}

	public void setStrParam2(String strParam2) {
		this.strParam2 = strParam2;
	}

	public String getMg() {
		return mg;
	}

	public void setMg(String mg) {
		this.mg = mg;
	}

}
