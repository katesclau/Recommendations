package recommendations.repositories;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import recommendations.resources.Purchase;
import recommendations.resources.Purchases;

public class XTeamPurchaseRepository implements PurchaseRepository {

	@Override
	@Cacheable("userPurchases")
	public Collection<Purchase> getPurchasesByBuyer(String username, int limit) {
		// GET the last Product bought by {{username}}
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Purchases> responseEntity;
		try {
			responseEntity = restTemplate.getForEntity("http://50.116.35.94:8000/api/purchases/by_user/" 
									+ username + "?limit=" + limit, Purchases.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while fetching purchases: " +e.getLocalizedMessage());
			return null;
		}
        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.is2xxSuccessful()){
			System.out.println("Got these by user: " + responseEntity.getBody().toString());
        	return responseEntity.getBody().getPurchases();
        } else
        	return null;
	}

	@Override
	@Cacheable("productPurchases")
	public Collection<Purchase> getPurchasesByProduct(long productId, int limit) {
		RestTemplate restTemplate = new RestTemplate();
		// GET the last {{quantity}} Products bought by {{username}}
        ResponseEntity<Purchases> responseEntity;
		try {
			responseEntity = restTemplate.getForEntity("http://50.116.35.94:8000/api/purchases/by_product/" 
					+ productId + "?limit=" + (limit > 0 ? limit : 30), Purchases.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while fetching purchases: " +e.getLocalizedMessage());
			return null;
		}
        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.is2xxSuccessful()){
			System.out.println("Got these by product: " + responseEntity.getBody().toString());
        	return responseEntity.getBody().getPurchases();
        } else
        	return null;
	}

}
