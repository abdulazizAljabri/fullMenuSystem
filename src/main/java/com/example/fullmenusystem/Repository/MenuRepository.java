package com.example.fullmenusystem.Repository;

import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
    Menu findByMenuId(Integer menuId);

    List<Menu> findByCategory(String category);

    Menu findMenuByProductName(String productName);

    List<Menu> findMenuByProductPrice(Double productPrice);

    @Query("select product from Menu product where product.productCount=?1")
    List<Menu> findByProductCount(Integer productCount);

}
