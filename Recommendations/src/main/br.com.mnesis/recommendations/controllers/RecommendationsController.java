package recommendations.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import recommendations.repositories.XTeamProductRepository;
import recommendations.repositories.XTeamPurchaseRepository;
import recommendations.resources.Purchase;
import recommendations.resources.RecommendedPurchases;
import recommendations.util.RecommendedCompare;
import recommendations.resources.Recommended;

@RestController
public class RecommendationsController {

    private final AtomicLong recs_counter = new AtomicLong();
    
    XTeamPurchaseRepository purchaseRepository = new XTeamPurchaseRepository();

    XTeamProductRepository productRepository = new XTeamProductRepository();

    @RequestMapping("/api/recent_purchases")
    public String recommendations() {
    	return new String("Please provide username on Path.");
    }
    
    @RequestMapping("/api/recent_purchases/{username}")
    public String recommendations(@PathVariable("username") String username) {
        System.out.println("Searching recommendations for " + username);
        
        List<Recommended> recs = new ArrayList<Recommended>();
        
//    	USER_PURCHASES = GET /api/purchases/by_user/:username?limit=5
    	Collection<Purchase> userRecentPurchases = purchaseRepository.getPurchasesByBuyer(username, 5);
//		IF USER_PURCHASES.SIZE < 1 
    	if (userRecentPurchases == null || userRecentPurchases.size() < 1){
    		// RESPONSE “NOT FOUND”
    		return new String("User with username of " + username + " was not found");
    	} else {
//			FOR_EACH(PURCHASE IN USER_PURCHASES) DO
    		for(Purchase userPurchase : userRecentPurchases){
//				PURCHASES = GET /api/purchases/by_product/:PURCHASE.product_id
    			Collection<Purchase> productRecentPurchases = purchaseRepository.getPurchasesByProduct(userPurchase.getProductId(), 5);
//				PRODUCT = GET /api/products/:product_id
    			Recommended product = new Recommended(productRepository.getProductById(userPurchase.getProductId()));
//				FOR_EACH(BUYER IN PROD_PURCHASES) DO
				List<String> recentBuyers = new ArrayList<String>();
    			for(Purchase productPurchase : productRecentPurchases){
//					PRODUCT.RECENT.PUSH(BUYER.username)
    				recentBuyers.add(productPurchase.getUsername());
    			}
				product.setRecent(recentBuyers);
//				POP_PRODUCTS.PUSH(PRODUCT)
    			recs.add(product);
    		}
    	}
    	
    	//    	SORT(POP_PRODUCTS, POP_PRODUCTS.RECENT.SIZE)
    	Collections.sort(recs, new RecommendedCompare());

    	RecommendedPurchases purchases = new RecommendedPurchases(recs_counter.incrementAndGet(), recs);
		return purchases.toString();
    }

    @RequestMapping("/api/test/{username}")
    public String test(@PathVariable("username") String username) {
    	System.out.println("Got here with: " + username);
    	Collection<Recommended> recs = new ArrayList<Recommended>();
    	RecommendedPurchases purchases = new RecommendedPurchases(recs_counter.incrementAndGet(), recs);
    	System.out.println("Purchases: " + purchases.toString());
    	return "User with username of " + username + " was not found";
    }
}
