package com.example.callbus;

import com.example.callbus.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {

            Menu menu1 = Menu.builder()
                    .name("1번메뉴")
                    .listOrder(3)
                    .build();
            em.persist(menu1);
            Menu menu2 = Menu.builder()
                    .name("2번메뉴")
                    .listOrder(2)
                    .build();
            em.persist(menu2);
            Menu menu3 = Menu.builder()
                    .name("3번메뉴")
                    .listOrder(1)
                    .build();
            em.persist(menu3);

            Menu menu1_1 = Menu.builder()
                    .name("1-1. 하위메뉴")
                    .listOrder(1)
                    .parent(menu1)
                    .build();

            Menu menu1_2 = Menu.builder()
                    .name("1-2. 하위메뉴")
                    .listOrder(2)
                    .parent(menu1)
                    .build();

            em.persist(menu1_1);
            em.persist(menu1_2);

            Menu menu2_1 = Menu.builder()
                    .name("2-1. 하위메뉴")
                    .listOrder(1)
                    .parent(menu2)
                    .build();

            em.persist(menu2_1);


            Menu menu1_1_1 = Menu.builder()
                    .name("1-1-1. 하위메뉴")
                    .listOrder(1)
                    .parent(menu1_1)
                    .build();
            em.persist(menu1_1_1);



        }





    }
}

