package com.chiron.vd.paypal.api.payments;

import java.util.List;

import com.paypal.core.rest.JSONFormatter;

public class ItemList  {

	/**
	 * List of items.
	 */
	private List<Item> items;
	
	/**
	 * Shipping address.
	 */
	private ShippingAddress shippingAddress;
	
	/**
	 * Default Constructor
	 */
	public ItemList() {
	}


	/**
	 * Setter for items
	 */
	public ItemList setItems(List<Item> items) {
		this.items = items;
		return this;
	}
	
	/**
	 * Getter for items
	 */
	public List<Item> getItems() {
		return this.items;
	}


	/**
	 * Setter for shippingAddress
	 */
	public ItemList setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
		return this;
	}
	
	/**
	 * Getter for shippingAddress
	 */
	public ShippingAddress getShippingAddress() {
		return this.shippingAddress;
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