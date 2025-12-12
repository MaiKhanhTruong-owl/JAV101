package poly.com.model;

public class CartItem1 
{
	private Items items;
    private int quantity;

    public CartItem1(Items items, int quantity) 
    {
        this.items = items;
        this.quantity = quantity;
    }

    public Items getItem() {
        return items;
    }

    public void setItem(Items items) {
        this.items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() 
    {
        return (items.getPrice() - items.getDiscount()) * quantity;
    }
}
