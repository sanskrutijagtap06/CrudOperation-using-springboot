package com.crud.repository;
 
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.entity.Product;

  
  public interface ProductRepository extends JpaRepository<Product, Integer> 
  {

	Product findByuId(UUID uId);
	Product findTopByOrderByTimestampMillisDesc();
	
	
}
 