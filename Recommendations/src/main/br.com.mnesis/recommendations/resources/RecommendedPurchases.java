package recommendations.resources;

import java.util.Collection;

public class RecommendedPurchases {
	private long id;
	private Collection<Recommended> purchases;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Recommended> getRecommendations() {
		return purchases;
	}

	public void setRecommendations( Collection<Recommended> recommendations) {
		this.purchases = recommendations;
	}

	public RecommendedPurchases(long id, Collection<Recommended> recommendations) {
		super();
		this.id = id;
		this.purchases = recommendations;
	}

	@Override
	public String toString() {
		if(purchases != null && purchases.size() > 0){
			String str = "{\"purchases\": [";
			for (Recommended purchase : purchases){
				str += purchase.toString() + ",";
			}
			if (purchases.size() > 2)
				str = str.substring(0, str.length()-1);
			str += "]}";
			return str;
		}
		return "";
	}
	
}
