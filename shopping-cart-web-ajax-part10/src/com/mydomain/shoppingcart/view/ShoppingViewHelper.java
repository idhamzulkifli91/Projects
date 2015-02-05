package com.mydomain.shoppingcart.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mydomain.shoppingcart.bo.Basket;
import com.mydomain.shoppingcart.bo.BasketItem;
import com.mydomain.shoppingcart.bo.Item;
import com.mydomain.shoppingcart.exception.ShoppingException;
import com.mydomain.shoppingcart.service.ShoppingService;

/** 
 * @author Ross
 */
public class ShoppingViewHelper {
	private Basket basket;
	private ShoppingService shoppingManager;

	public void addItemToBasket(Item item) {
		getBasket().addItem(item);
		try {
			// We could have just left the rest of the code below out and only kept the state of the basket in the session
			// and at checkout persisted the object however for proving concepts lets persist to the database here as well
			getShoppingManager().updateBasket(getBasket());
		} catch (ShoppingException e) {
			addErrorMessage("There was an error adding an item to the basket");
		}
	}

	public void deleteBasket() {
		try {
			getShoppingManager().deleteBasket(getBasket());
			setBasket(null);
		} catch (ShoppingException e) {
			addErrorMessage("There was an error deleting the basket");
		}
	}

	public List<Item> findItems() {
		try {
			return new ArrayList<Item>(getShoppingManager().findItems());
		} catch (ShoppingException e) {
			addErrorMessage("There was an error finding the items");
			return null;
		}
	}

	public Basket getBasket() {
		if (basket == null) {
			basket = new Basket();
		}
		return basket;
	}
	
	public ShoppingService getShoppingManager() {
		return this.shoppingManager;
	}
	
	public void removeItemFromBasket(BasketItem basketItem) {
		try {
			setBasket(getShoppingManager().removeItemFromBasket(basketItem));
		} catch (ShoppingException e) {
			addErrorMessage("There was an error adding an item to the basket");
		}
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	public void setShoppingManager(ShoppingService shoppingManager) {
		this.shoppingManager = shoppingManager;
	}
	
	private void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
}