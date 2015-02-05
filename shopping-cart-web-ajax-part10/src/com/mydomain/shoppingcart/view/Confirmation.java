package com.mydomain.shoppingcart.view;


/** 
 * @author Ross
 */
public class Confirmation {
	private ShoppingViewHelper shoppingViewHelper;
	
	public String restartShopping() {
		getShoppingViewHelper().getBasket().empty();
		getShoppingViewHelper().deleteBasket();
		return "continue";
	}
	
	public ShoppingViewHelper getShoppingViewHelper() {
		return shoppingViewHelper;
	}
	
	public void setShoppingViewHelper(ShoppingViewHelper shoppingViewHelper) {
		this.shoppingViewHelper = shoppingViewHelper;
	}
}