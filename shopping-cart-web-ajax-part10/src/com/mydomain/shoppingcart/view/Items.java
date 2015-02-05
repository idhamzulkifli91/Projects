package com.mydomain.shoppingcart.view;

import java.util.List;

import org.richfaces.component.UIDataTable;

import com.mydomain.shoppingcart.bo.BasketItem;
import com.mydomain.shoppingcart.bo.Item;

/**
 * @author Ross
 */
public class Items {
	private ShoppingViewHelper shoppingViewHelper;
	private UIDataTable basketTable;
	private List<Item> items;
	private UIDataTable itemsTable;

	public String addItemToBasket() {
		Item item = (Item) getItemsTable().getRowData();
		getShoppingViewHelper().addItemToBasket(item);
		return "";
	}

	public String checkout() {
		return "checkout";
	}

	public String removeItemFromBasket() {
		getShoppingViewHelper().removeItemFromBasket((BasketItem) getBasketTable().getRowData());
		return "";
	}

	private List<Item> findItems() {
		return getShoppingViewHelper().findItems();
	}

	public UIDataTable getBasketTable() {
		return basketTable;
	}

	public List<Item> getItems() {
		if (items == null) {
			items = findItems();
		}
		return items;
	}

	public UIDataTable getItemsTable() {
		return itemsTable;
	}

	public ShoppingViewHelper getShoppingViewHelper() {
		return shoppingViewHelper;
	}
	
	public void setShoppingViewHelper(ShoppingViewHelper shoppingViewHelper) {
		this.shoppingViewHelper = shoppingViewHelper;
	}

	public void setBasketTable(UIDataTable basketTable) {
		this.basketTable = basketTable;
	}

	public void setItemsTable(UIDataTable itemsTable) {
		this.itemsTable = itemsTable;
	}
}