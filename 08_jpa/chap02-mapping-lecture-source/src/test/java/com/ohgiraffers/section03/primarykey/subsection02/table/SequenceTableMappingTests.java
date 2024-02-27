package com.ohgiraffers.section03.primarykey.subsection02.table;

import com.ohgiraffers.section03.primarykey.subsection01.identity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

public class SequenceTableMappingTests {
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
        entityManager.close();      // 내부적으로 flush()가 날라가고 commit()이 동작


    }

    @Test
    void 식별자_매핑_테스트() {
        Member member1 = new Member();
        member1.setMemberId("user01");
        member1.setMemberPwd("pass01");
        member1.setNickname("홍길동");
        member1.setPhone("010-1234-5678");
        member1.setEmail("hong@gmail.com");
        member1.setAddress("서울시 서초구");
        member1.setEnrollDate(new java.util.Date());
        member1.setMemberRole("ROLE_MEMBER");
        member1.setStatus("Y");

       Member member2 = new Member();
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-1243-4378");
        member2.setEmail("korea@gmail.com");
        member2.setAddress("서울시 강북구");
        member2.setEnrollDate(new java.util.Date());
        member2.setMemberRole("ROLE_MEMBER");
        member2.setStatus("Y");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member1);
        entityManager.persist(member2);
        entityTransaction.commit();     // 내부적으로는 flush() 동작

        Member selectedMember = entityManager.find(Member.class, 1);

        String jpql = "SELECT A.memberNo FROM member_section03_subsection02 A";     // 반드시 별칭을 달아야함. 셋오퍼레이터 사용불가.
        List<Integer> memberNoList = entityManager.createQuery(jpql, Integer.class).getResultList();

        memberNoList.forEach(System.out::println);
    }
}
