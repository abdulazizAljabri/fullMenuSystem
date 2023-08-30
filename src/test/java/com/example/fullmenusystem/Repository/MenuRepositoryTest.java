package com.example.fullmenusystem.Repository;

import com.example.fullmenusystem.Model.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    Menu menu1;
    Menu menu2;


    @BeforeEach
    void setup(){
        menu1 = new Menu(null, "seven", "drinks", 2, 4.0);
        menu2 = new Menu(null,"pepsii","drinks",5,3.6);
    }

    // 3 RepositoryTest
    @Test
    void findByMenuIdTest(){
        menuRepository.save(menu1);
        Menu menu = menuRepository.findByMenuId(menu1.getMenuId());
        Assertions.assertThat(menu.getMenuId()).isEqualTo(menu1.getMenuId());
    }

    // 4 RepositoryTest
    @Test
    void findByCategoryTest(){
        menuRepository.save(menu1);
        List<Menu> menus = menuRepository.findByCategory(menu1.getCategory());
        Assertions.assertThat(menus.get(0).getCategory()).isEqualTo(menu1.getCategory());
    }



}
