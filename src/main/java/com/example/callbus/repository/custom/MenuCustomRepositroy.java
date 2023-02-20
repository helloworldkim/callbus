package com.example.callbus.repository.custom;

import com.example.callbus.entity.Menu;
import com.example.callbus.web.response.board.BoardResDto;
import com.example.callbus.web.response.menu.MenuResDTO;

import java.util.List;


public interface MenuCustomRepositroy {
    List<Menu> findMenuListWithQuerydsl();

}
