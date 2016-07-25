package recommendations.repositories;

import java.util.Collection;

import recommendations.resources.Purchase;

public interface PurchaseRepository {

	Collection<Purchase> getPurchasesByBuyer(String username, int limit);
	Collection<Purchase> getPurchasesByProduct(long productId, int limit);

}
