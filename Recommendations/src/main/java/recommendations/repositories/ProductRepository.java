package recommendations.repositories;

import recommendations.resources.Product;

public interface ProductRepository {
	Product getProductById(long productId);
}
