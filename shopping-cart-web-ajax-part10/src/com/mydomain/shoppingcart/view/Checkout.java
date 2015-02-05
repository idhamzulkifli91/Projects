package com.mydomain.shoppingcart.view;

import com.mydomain.shoppingcart.bo.BasketItem;

/** 
 * @author Ross
 */
public class Checkout {
	private ShoppingViewHelper shoppingViewHelper;
	private double total;

	public String cancelOrder() {
		getShoppingViewHelper().getBasket().empty();
		getShoppingViewHelper().deleteBasket();
		return "cancel";
	}

	public String processPayment() {
		return "pay";
	}

	public ShoppingViewHelper getShoppingViewHelper() {
		return shoppingViewHelper;
	}
	
	public void setShoppingViewHelper(ShoppingViewHelper shoppingViewHelper) {
		this.shoppingViewHelper = shoppingViewHelper;
	}

	public double getTotal() {
		total = 0;
		for (BasketItem basketItem : getShoppingViewHelper().getBasket().getBasketItems()) {
			total = total + basketItem.getPrice();
		}
		return total;
	}
}