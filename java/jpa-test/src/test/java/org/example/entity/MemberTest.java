package org.example.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class MemberTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	@BeforeEach
	void setUp() {
		emf = Persistence.createEntityManagerFactory("example-unit");
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() {
		em.close();
		emf.close();
	}

	@Test
	void persistence_context_test() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 비영속
		Member member = new Member(1L, "memberA", 10);
		Member member2 = new Member(2L, "memberB", 20);

		try {
			System.out.println("===== persist member =====");
			// 영속 (쿼리가 바로 나가지 않음)
			em.persist(member);
			em.persist(member2);

			System.out.println("===== before commit =====");
			// 영속성 컨텍스트에 있는 데이터를 DB에 반영, 쿼리 실행
			tx.commit();
			System.out.println("===== after commit =====");

			// 영속성 컨텍스트에 있는 데이터는 sql쿼리를 날리지 않고, 영속성 컨텍스트에서 바로 가져온다.
			em.find(Member.class, member.getId());
			em.find(Member.class, member2.getId());
			em.find(Member.class, 100L);
		} catch (Exception e) {
			tx.rollback();
		}
	}

	@Test
	void 영속성_컨텍스트를_clear하면_sql을_실행한다() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 비영속
		Member member = new Member(1L, "memberA", 10);
		Member member2 = new Member(2L, "memberB", 20);

		try {
			System.out.println("===== persist member =====");
			// 영속 (쿼리가 바로 나가지 않음)
			em.persist(member);
			em.persist(member2);

			System.out.println("===== before commit =====");
			// 영속성 컨텍스트에 있는 데이터를 DB에 반영, 쿼리 실행
			tx.commit();
			System.out.println("===== after commit =====");

		} catch (Exception e) {
			tx.rollback();
		}

		em.clear();

		// clear했기 때문에 모두 sql에서 가져온다.
		em.find(Member.class, member.getId());
		em.find(Member.class, member2.getId());
		em.find(Member.class, 100L);
	}
}
