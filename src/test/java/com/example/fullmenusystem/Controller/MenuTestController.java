package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Model.Menu;
import com.example.fullmenusystem.Service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MenuController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class MenuTestController {

    @MockBean
    MenuService menuService;

    @Autowired
    MockMvc mockMvc;

    Menu menu1;

    List<Menu> menuList;

    @BeforeEach
    void setup() {
        menu1 = new Menu(1,"rice","meal",4,14.5);
        menuList = Arrays.asList(menu1);
    }
    @Test
    public void getMenuList()throws Exception {
        Mockito.when(menuService.getMenus()).thenReturn(menuList);
        mockMvc.perform(get("/api/v1/menus/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value("rice"));
    }

    @Test
    public void deleteMenu() throws Exception {
        mockMvc.perform(delete("/api/v1/menus/delete/{id}",menu1.getMenuId()))
                .andExpect(status().isOk());
    }
}

