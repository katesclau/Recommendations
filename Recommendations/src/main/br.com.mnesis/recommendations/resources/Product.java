package recommendations.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	protected long id;
	protected String face;
    protected int price;
    protected int size;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Product(){
    	//default constructor for Jackson
    }
    
    public Product(long id, String face, int price, int size) {
        this.id = id;
        this.face = face;
        this.price = price;
        this.size = size;
    }

	@Override
	public String toString() {
		return 	"{ \"id\":" + id +
				", \"face\":" + face + 
				", \"price\":" + price + 
				", \"size\":" + size + "}";
	}

}
