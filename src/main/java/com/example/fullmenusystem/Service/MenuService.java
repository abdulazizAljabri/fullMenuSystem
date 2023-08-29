package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Api.ApiException;
import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Model.Menu;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Repository.AuthRepository;
import com.example.fullmenusystem.Repository.CustomerRepository;
import com.example.fullmenusystem.Repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final CustomerRepository customerRepository;

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

//    public List<Menu> getProductsOfCustomer(Integer customer_id) {
//      Customer customer = customerRepository.findCustomerById(customer_id);
//        return menuRepository.findAllByCustomer(customer);
//    }
//    public void addProductToUser(Integer customer_id,Menu product) {
//        Customer customer = customerRepository.findCustomerById(customer_id);
//        product.setCustomer(customer);
//
//        menuRepository.save(product);
//    }
    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public void deleteMenu(Integer id) {
        Menu menu = menuRepository.findByMenuId(id);
        if (menu == null) {
            throw new ApiException("Menu not found");
        }
        menuRepository.delete(menu);
    }

    public void updateMenu(Integer id, Menu menu) {
        Menu menus = menuRepository.findByMenuId(id);
        if (menus == null) {
            throw new ApiException("Menu not found");
        }
        menus.setProductName(menu.getProductName());
        menus.setCategory(menu.getCategory());
        menus.setProductPrice(menu.getProductPrice());
        menuRepository.save(menus);
    }

    public List<Menu> findByCategory(String category) {
        List<Menu> menus = menuRepository.findByCategory(category);
        if (menus.isEmpty()) {
            throw new ApiException("Category not found");
        }
        return menus;
    }

    public List<Menu> findCountProducts(Integer product) {
        List<Menu> menus = menuRepository.findByProductCount(product);
        return menus;
    }

    public List<Menu> findProductsPrice(Double productPrice) {
        List<Menu> menus = menuRepository.findMenuByProductPrice(productPrice);
        if (menus.isEmpty()) {
            throw new ApiException("we not have any products with this price");
        }
        return menus;
    }

    public void findProductsAfterdiscount(String productName) {
        Menu menu = menuRepository.findMenuByProductName(productName);
        if (menu == null) {
            throw new ApiException("product not found");
        }
        double newPrice = menu.getProductPrice() * 0.1;
        menu.setProductPrice(newPrice);
        menuRepository.save(menu);
    }
}
