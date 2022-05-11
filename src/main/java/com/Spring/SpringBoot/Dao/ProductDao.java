package com.Spring.SpringBoot.Dao;

import com.Spring.SpringBoot.entity.Products;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("ProductDao")
public interface ProductDao extends PagingAndSortingRepository<Products,Long> {
}
