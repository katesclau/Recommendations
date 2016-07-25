package recommendations.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import recommendations.resources.Product;
import recommendations.resources.ProductWrap;

import org.springframework.cache.annotation.Cacheable;

@Component
public class XTeamProductRepository implements ProductRepository {

	@Override
	@Cacheable("products")
	public Product getProductById(long productId) {
		// GET the last Product bought by {{username}}
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProductWrap> responseEntity;
		try {
			System.out.println("Getting: " + "http://50.116.35.94:8000/api/products/" + productId);
			responseEntity = restTemplate.getForEntity("http://50.116.35.94:8000/api/products/" 
									+ productId , ProductWrap.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while fetching product: " +e.getLocalizedMessage());
			return null;
		}
        Product product = responseEntity.getBody().getProduct();
        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.is2xxSuccessful()){
			System.out.println("Got this product: " + responseEntity.getBody().toString());
        	return product;
        } else
        	return null;
	}

}
