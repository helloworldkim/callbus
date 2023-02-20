package com.example.callbus.service;

import com.example.callbus.entity.Menu;
import com.example.callbus.repository.MenuRepository;
import com.example.callbus.web.response.menu.MenuResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;


    public List<MenuResDTO> getV1Menus() {
        List<Menu> all = menuRepository.findAll();
        return all.stream().map(MenuResDTO::new).collect(Collectors.toList());
    }

    public List<MenuResDTO> getV2Menus() {
        final List<Menu> all = menuRepository.findAllByParentIsNull();
        return all.stream().map(MenuResDTO::new).collect(Collectors.toList());
    }

    public List<MenuResDTO> getV3Menus() {
        final List<Menu> all = menuRepository.findAll(Sort.by(Sort.Direction.ASC, "listOrder"));
        return all.stream().map(MenuResDTO::new).collect(Collectors.toList());
    }

    public List<MenuResDTO> getV4Menus() {
        final List<Menu> all = menuRepository.findAllByParentIsNull(Sort.by(Sort.Direction.ASC, "listOrder")); // parent가 없는 메뉴들을 조회함.
        return all.stream().map(MenuResDTO::new).collect(Collectors.toList());
    }

    public List<MenuResDTO> getV5Menus() {
        final List<Menu> all = menuRepository.findMenuListWithQuerydsl();
        return all.stream().map(MenuResDTO::new).collect(Collectors.toList());
    }



}
