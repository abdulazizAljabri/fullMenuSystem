package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Api.ApiResponse;
import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Model.Menu;
import com.example.fullmenusystem.Service.MenuService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/")
    public ResponseEntity getMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenus());
    }


//    @GetMapping("/myProduct")
//    public ResponseEntity getAllOfCustomer(@AuthenticationPrincipal Customer customer){
//        return ResponseEntity.status(HttpStatus.OK).body(menuService.getProductsOfCustomer(customer.getId()));
//    }

    @PostMapping("/add")
    public ResponseEntity addMenu(@RequestBody @Valid Menu menu) {
        menuService.addMenu(menu);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("menu added"));
    }
//    @PostMapping("/addtocustomer")
//    public ResponseEntity addProductToCustomer(@AuthenticationPrincipal Customer customer,@RequestBody @Valid Menu product) {
//        menuService.addProductToUser(customer.getId(),product);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added product"));
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeMenu(@PathVariable Integer id) {
        menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("menu removed"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMenu(@PathVariable Integer id, @RequestBody @Valid Menu menu) {
        menuService.updateMenu(id, menu);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("menu updated"));
    }

    @GetMapping("/getcategory/{category}")
    public ResponseEntity findCategory(@PathVariable String category) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.findByCategory(category));
    }

    @GetMapping("/empty/{productCount}")
    public ResponseEntity countProducts(@PathVariable Integer productCount) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.findCountProducts(productCount));
    }

    @GetMapping("/getproduct/{productPrice}")
    public ResponseEntity findProductPrice(@PathVariable Double productPrice) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.findProductsPrice(productPrice));
    }

    @GetMapping("/discount/{productName}")
    public ResponseEntity findDiscount(@PathVariable String productName) {
        menuService.findProductsAfterdiscount(productName);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("discount done"));
    }

}
