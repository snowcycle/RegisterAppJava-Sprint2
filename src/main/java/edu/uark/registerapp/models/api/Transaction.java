//------------------------------------------------------------
//Jacob Dedman
//Created Transaction.java

package edu.uark.registerapp.models.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;


//Jacob Dedman
//Created Transaction class so I can add functionality for updating database

public class Transaction extends ApiResponse {
    
    //Jacob Dedman
    //Cart class to hold items
    private class Cart {
        //Jacob Dedman
        //Array of strings holds lookupcode of products
        private ArrayList<String> products = new ArrayList<String>();
        public void addProduct(Product prod) {
            products.add(prod.getLookupCode());
        }

        public void remProduct(Product prod) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i) == prod.getLookupCode()) {
                    products.remove(i);
                    i--;
                }
            }
        }
    }


    //Jacob Dedman
    //Properties
    
    private Cart productCart;
    public Transaction addToCart(Product prod) {
        this.productCart.addProduct(prod);
        return this;
    }
    public Transaction remFromCart(Product prod) {
        this.productCart.remProduct(prod);
        return this;
    }

    private String lookupCode;
    public String getLookupCode() {
		return this.lookupCode;
	}

	public Product setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}


    public Transaction() {
		super();
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;
	}

	public Transaction(final TransactionEntity transactionEntity) {
		super(false);

		this.id = transactionEntity.getId();
		this.lookupCode = transactionEntity.getLookupCode();
	}
}