package poly.com.model;

public class Items {
private  String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getDiscount() {
	return discount;
}
public void setDiscount(double discount) {
	this.discount = discount;
}
private  String image;
private  double price;
private  double discount;
public Items(String name, String image, double price, double discount) {
	super();
	this.name = name;
	this.image = image;
	this.price = price;
	this.discount = discount;
}
public Items() {
	
}
}
