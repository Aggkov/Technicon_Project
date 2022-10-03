package com.europeandynamics.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import com.europeandynamics.model.BaseEntity;
import com.europeandynamics.utils.EntityManagerFactoryUtils;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

	protected EntityManagerFactory emf = EntityManagerFactoryUtils.getEntityManagerFactory();
	protected EntityManager em = emf.createEntityManager();


	@Override
	public Optional<T> findById(String id, Class<T> classType) {

		Optional<T> entity = Optional.ofNullable(em.find(classType, id));

		return entity;
	}

	@Override
	public List<?> findAll(Class<T> classType) {

		String className = classType.getSimpleName();

		List<?> entities = em
				.createQuery("SELECT " + className.toLowerCase() + " from " + className + " " + className.toLowerCase())
				.getResultList();

		em.getTransaction().commit();

		return entities;

	}

	@Override
	public T create(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		return entity;
	}

	@Override
	public void update(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public boolean delete(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.contains(entity)) {
			em.remove(entity); 
			em.getTransaction().commit();
			em.close();
			return true;
		}
		else {
			T managedEntity = em.merge(entity);
			em.remove(managedEntity); 
			
			em.getTransaction().commit();
			em.close();
			return true;

		}
	}

}
