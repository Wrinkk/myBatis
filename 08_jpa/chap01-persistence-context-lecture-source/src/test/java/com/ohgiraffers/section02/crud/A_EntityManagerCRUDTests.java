package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A_EntityManagerCRUDTests {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }


    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트() {
        // given
        int menuCode = 2;

        // when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // then
        Assertions.assertNotNull(foundMenu);
        assertEquals(menuCode, foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);
    }

    @Test
    public void 새로운_메뉴_추가_테스트() {
        //given
        Menu menu = new Menu();
        menu.setMenuName("꿀아메리카노");
        menu.setMenuPrice(1900);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {

            entityManager.persist(menu);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }

        // then
        Assertions.assertTrue(entityManager.contains(menu));        // menu 객체가 현재 영속상태로 잘관리 되는지 확인
    }

    @Test
    public void 메뉴_이름_수정_테스트() {

        // given
        Menu menu = entityManager.find(Menu.class, 2);
        System.out.println("menu = " + menu);

        String menuNameToChange = "딸기스무디";

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {

        menu.setMenuName(menuNameToChange);
        entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
        }


        // then
        assertEquals(menuNameToChange, entityManager.find(Menu.class, 2).getMenuName());

    }

    @Test
    void 메뉴_삭제하기_테스트() {

        // given
        Menu menuToRemove = entityManager.find(Menu.class, 1);

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            entityManager.remove(menuToRemove);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }

        // then
        Menu removeMenu = entityManager.find(Menu.class, 1);
        assertEquals(null,removeMenu);
    }
}
