package fr.yuka;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("yuka");
		EntityManager em = emf.createEntityManager();

		em.close();
		emf.close();

	}
}