package recommendations.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import recommendations.resources.Product;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recommended extends Product {
	protected List<String> recent;
	
	public Recommended(long id, String face, int price, int size, List<String> recent) {
		super(id, face, price, size);
		this.recent = recent;
	}

	public Recommended(Product product, List<String> recent) {
		super(product.getId(), product.getFace(), product.getPrice(), product.getSize());
		this.recent = recent;
	}

	public Recommended(Product product) {
		super(product.getId(), product.getFace(), product.getPrice(), product.getSize());
	}
	
		public List<String> getRecent() {
		return recent;
	}

	public void setRecent(List<String> recent) {
		this.recent = recent;
	}

	@Override
    public String toString() {
		String str = "{ \"id\":" + id +
					", \"face\":" + face + 
					", \"price\":" + price + 
					", \"size\":" + size + 
					", \"recent\": [";
		for(String buyer : recent) str += buyer + ",";
		if (recent.size() > 1 )
			str = str.substring(0, str.length()-1);;
		str += "]}";
        return str;
    }
}
