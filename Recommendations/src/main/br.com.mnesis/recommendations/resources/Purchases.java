package recommendations.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Purchases {
	protected List<Purchase> purchases;
	
	public Purchases(){
		
	}
	
	public Purchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
    public String toString() {
		String str = "{ \"purchases\": [";
		for(Purchase purchase : purchases) str += purchase.toString() + ",";
		if (purchases.size() > 1)
			str = str.substring(0, str.length()-1);;
		str += "]}";
        return str;
    }

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
}