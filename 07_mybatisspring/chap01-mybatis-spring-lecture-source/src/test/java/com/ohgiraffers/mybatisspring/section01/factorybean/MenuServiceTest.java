package com.ohgiraffers.mybatisspring.section01.factorybean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @DisplayName("주문가능 상태별 메뉴 조회 확인 테스트")
    @ParameterizedTest      // 매개변수를 여러번 테스트 하는것?..
    @ValueSource(strings = {"Y", "N"})
    void testFindAllMenus(String orderableStatus) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MenuDTO> menus = menuService.findAllMenuByOrderableStatus(orderableStatus);
                    menus.forEach(System.out::println);
                }
        );
    }
}