package com.ohgiraffers.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

public class SimpleJPQLTests {
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

    /* 설명.
     *  jpql의 기본 문법
     *  1. SELECT, UPDATE, DELETE 등의 키워드 사용은 SQL과 동일하다.
     *  2. INSERT는 persist() 메소드를 사용하면된다.
     *  3. 키워드는 대소문자를 구분하지 않지만, 엔티티와 속성은 대소문자를 구분하므로 유이한다.
     *  4. 엔티티 별칭을 필수로 사용해야 하며 별칭 없이 작성하면 에러가 발생한다.
     * */

    /* 설명.
     *  jpql의 사용 방법은 다음과 같다.
     *  1. 작성한 jpql(문자열)을 'entityManageer.createQuery()' 메소드를 통해 쿼리 객체로 만든다.
     *  2. 쿼리 객체는 'TypedQuery', 'Query' 두 가지가 있다.
     *   2-1. TypedQuery: 반환할 타입을 명확하게 지칭하는 방식일 때 사용하며 쿼리 객체의 메소드 실행 결과로
     *                      지정한 타입이 반환된다.
     *   2-2. Query: 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는
     *                  Object[]가 반환된다.
     *  3. 쿼리 객체에서 제공하는 메소드를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
     *   3-1. getSingleResult(): 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 예외가 발생한다.
     *   3-2. getResultList(): 결과가 2행 이상 일경우 사용하며 컬렉션을 반환한다. 없으면 빈 컬렉션을 반환한다.
    * */
    @Test
    void TypeQuery를_이용한_단일행_단일열_조회_테스트() {

        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        String result = query.getSingleResult();

        System.out.println("result = " + result);

        Assertions.assertEquals("민트미역국", result);
    }

    @Test
    void Query를_이용한_단일행_단일열_조회_테스트() {
        String jpql = "SELECT menuName FROM menu_section01 WHERE menuCode = 7";

        Query query = entityManager.createQuery(jpql);
        Object singleResult = query.getSingleResult();

        Assertions.assertTrue(singleResult instanceof String);
        Assertions.assertEquals("민트미역국", singleResult);

    }

    @Test
    void TypedQuery를_이용한_다중행_다중열_조회_테스트() {

        /* 설명. jpql에서는 entity의 별칭을 적으면 모든 속성(컬럼)을 조회하는 것이다. */
        String jpql = "SELECT m FROM menu_section01 as m";

        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> resultList = query.getResultList();

        Assertions.assertTrue(!resultList.isEmpty());
        resultList.forEach(System.out::println);
    }

    /* 설명. SQL과 다르지 않은 몇가지 종류의 연산지만 테스트 해 보자.*/
    @Test
    void distinct를_활용한_중복제거_여러_행_조회_테스트() {

        /* 설명. 메뉴로 존재하는 카테고리의 종류(중복제거)만 조회 */
        String jpql = "SELECT DISTINCT m.categoryCode FROM menu_section01 m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> resultList = query.getResultList();

        Assertions.assertTrue(!resultList.isEmpty());
        resultList.forEach(System.out::println);
    }

    @Test
    void in_연산자를_활용한_조회_테스트() {

        String jpql = "SELECT m FROM menu_section01 m WHERE m.categoryCode IN(6,10)";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultList = query.getResultList();

        Assertions.assertTrue(!resultList.isEmpty());
        resultList.forEach(System.out::println);
    }

    @Test
    void like_연산자를_활용한_조회_테스트() {
        String jpql = "SELECT m FROM menu_section01 m WHERE m.menuName LIKE '%밥%'";
//        List<Menu> resultList = entityManager.createQuery(jpql, Menu.class).getResultList();
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        List<Menu> resultList = query.getResultList();

        Assertions.assertTrue(!resultList.isEmpty());   // 빈 객체오기 체크하기 위해서 !.isEmpty
        resultList.forEach(System.out::println);
    }
}
