package com.chiron.vd.paypal.api.payments;

import com.paypal.core.rest.JSONFormatter;

public class Transactions  {

	/**
	 * Amount being collected.
	 */
	private Amount amount;
	
	/**
	 * Default Constructor
	 */
	public Transactions() {
	}

	/**
	 * Parameterized Constructor
	 */
	public Transactions(Amount amount) {
		this.amount = amount;
	}
	

	/**
	 * Setter for amount
	 */
	public Transactions setAmount(Amount amount) {
		this.amount = amount;
		return this;
	}
	
	/**
	 * Getter for amount
	 */
	public Amount getAmount() {
		return this.amount;
	}

	/**
	 * Returns a JSON string corresponding to object state
	 * 
	 * @return JSON representation
	 */
	public String toJSON() {
		return JSONFormatter.toJSON(this);
	}

	@Override
	public String toString() {
		return toJSON();
	}
}