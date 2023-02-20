package com.example.callbus.repository;

import com.example.callbus.entity.Menu;
import com.example.callbus.repository.custom.MenuCustomRepositroy;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuCustomRepositroy {
    List<Menu> findAllByParentIsNull();
    List<Menu> findAllByParentIsNull(Sort listOrder);
}
