package com.crud.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Helper;
import com.crud.entity.Product;
import com.crud.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController2 {

	
	@Autowired 
	private ProductService service;

    @GetMapping("/endpoint")
    public ResponseEntity<String> getEndpoint() {
        return ResponseEntity.ok("   *********************This is the GET response.");
        
    }
    
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        UUID uId = Helper.generateUUID();
        product.setuId(uId);
        product.setTimestampMillis(System.currentTimeMillis());
        return service.saveProduct(product);
    }
    

    @PostMapping("/addProducts") 
    public List<Product> addProducts(@RequestBody List<Product> products) 
    { 
    	return service.saveProducts(products); 
    }
     
    @GetMapping("/products")
    public List<Product> getAllProducts() 
    {
        return service.getProducts();
    }
    
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id)
    {
    	return service.getProductById(id);
    }
    
    @GetMapping("/mostRecent")
    public ResponseEntity<Product> getMostRecentProduct() {//http request
        Product product = service.getMostRecentProduct();

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        service.deleteProductById(id);
    }
    
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product existingProduct = service.getProductById(id);

        updatedProduct.setuId(existingProduct.getuId());
        
        if(updatedProduct.getName() != null)
        {
        	updatedProduct.setName(updatedProduct.getName());
        }
        else
        {
        	updatedProduct.setName(existingProduct.getName());
        }
        
        if(updatedProduct.getPrice() != 0)
        {
        	updatedProduct.setPrice(updatedProduct.getPrice());
        }
        else
        {
        	updatedProduct.setPrice(existingProduct.getPrice());
        }
        
        updatedProduct.setTimestampMillis(System.currentTimeMillis());
        
        Product savedNewProduct = service.saveProduct(updatedProduct);

        return ResponseEntity.ok(savedNewProduct);
    	} 
    
    
    
    
}