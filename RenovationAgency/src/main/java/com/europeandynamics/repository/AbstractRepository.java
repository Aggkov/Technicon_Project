package com.europeandynamics.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.europeandynamics.model.BaseEntity;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
	protected EntityManager em = emf.createEntityManager();

//	protected Class<T> classType;

	@Override
	public Optional<T> findById(String id, Class<T> classType) {

		return Optional.of(em.find(classType, id));
	}

	@Override
	public List<?> findAll(Class<T> classType) {
		String className = classType.getSimpleName();

		List<?> entities = em
				.createQuery("SELECT " + className.toLowerCase() + " from " + className + " " + className.toLowerCase())
				.getResultList();

		return entities;

	}

	@Override
	public T create(T entity) {
		em.getTransaction().begin();

		em.persist(entity);
		em.getTransaction().commit();

		em.close();
		return entity;
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();

		em.merge(entity);
		em.getTransaction().commit();

		em.close();
	}

	@Override
	public void deleteById(String id, Class<T> classType) {

		em.getTransaction().begin();

		T entity = em.find(classType, id);

		em.remove(entity);
		em.getTransaction().commit();

		em.close();

	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();

		em.remove(entity);
		em.getTransaction().commit();

		em.close();
	}

	@Override
	public boolean exists(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
