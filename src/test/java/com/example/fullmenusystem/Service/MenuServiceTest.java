package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Model.Menu;
import com.example.fullmenusystem.Repository.MenuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static javax.management.Query.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @InjectMocks
    MenuService menuService;

    @Mock

    MenuRepository menuRepository;

    Menu menu1;
    Menu menu2;

    List<Menu> menuList;

    @BeforeEach
    void setUp() {
        menu1 = new Menu(null, "pepsi", "drinks", 2, 4.0);
        menu2 = new Menu(null, "pepsi", "drinks", 2, 4.0);
        menuList = new ArrayList<>();
        menuList.add(menu1);
        menuList.add(menu2);

    }

    // 4 ServiceTest
    @Test
    void getMenusTest() {
        when(menuRepository.findAll()).thenReturn(menuList);
        List<Menu> menus = menuService.getMenus();
        Assertions.assertEquals(menus, menuList);
        verify(menuRepository, Mockito.times(1)).findAll();
    }

    // 5 ServiceTest
    @Test
    void deleteMenuTest() {
        when(menuRepository.findByMenuId(menu1.getMenuId())).thenReturn(menu1);

        menuService.deleteMenu(menu1.getMenuId());

        verify(menuRepository, Mockito.times(1)).delete(menu1);
        verify(menuRepository, Mockito.times(1)).findByMenuId(menu1.getMenuId());
    }

}
