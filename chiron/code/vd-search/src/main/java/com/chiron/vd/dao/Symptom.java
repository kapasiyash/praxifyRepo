package com.chiron.vd.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "symptom")
public class Symptom {
	
	@Id	    
	@Column(name = "symptomid")
	private String symptomId;
	
	@Column(name = "symptom")
	private String symptom;
	
	@Column(name = "strparam1")
	private String strParam1;
	
	@Column(name = "strparam2")
	private String strParam2;

	public String getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
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

	public String toString() {
		return "Symptom [symptomId=" + symptomId + ", symptom=" + symptom
				+ ", strParam1=" + strParam1 + ", strParam2=" + strParam2 + "]";
	}		

}
