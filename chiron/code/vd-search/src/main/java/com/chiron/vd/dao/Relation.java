package com.chiron.vd.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relation")
public class Relation {

	@Id	    
	@Column(name = "relationid")
	private String relationId;
	
	@Column(name = "medicationid")
	private String medicationId;
	
	@Column(name = "symptomid")
	private String symptomId;
	
	@Column(name = "strparam1")
	private String strParam1;
	
	@Column(name = "strparam2")
	private String strParam2;

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(String medicationId) {
		this.medicationId = medicationId;
	}

	public String getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(String symptomId) {
		this.symptomId = symptomId;
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
		return "Relation [relationId=" + relationId + ", medicationId="
				+ medicationId + ", symptomId=" + symptomId + ", strParam1="
				+ strParam1 + ", strParam2=" + strParam2 + "]";
	}

	
	
}
