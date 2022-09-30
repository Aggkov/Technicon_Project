package com.europeandynamics.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.europeandynamics.model.BaseEntity;
import com.europeandynamics.utils.EntityManagerFactoryUtils;

public abstract class AbstractRepository<T extends BaseEntity> implements BaseRepository<T> {

	protected EntityManagerFactory emf = EntityManagerFactoryUtils.getEntityManagerFactory();
	
//	private Class<T> persistentClass;

	@Override
	public Optional<T> findById(String id, Class<T> classType) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Optional<T> entity = Optional.ofNullable(em.find(classType, id));
		em.getTransaction().commit();

		return entity;
	}

	@Override
	public List<?> findAll(Class<T> classType) {
		EntityManager em = emf.createEntityManager();

		String className = classType.getSimpleName();
		em.getTransaction().begin();

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

		return entity;
	}

	@Override
	public void update(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();

	}

	@Override
	public void delete(T entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(em.contains(entity)) {
			em.remove(entity); 
		}
		T managedEntity = em.merge(entity);
		em.remove(managedEntity); 
		
		em.getTransaction().commit();

		
	}

//	@Override
//	public boolean deleteById(String id, Class<T> classType) {		
//		
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		T foundEntity = em.find(classType, id);
//
//		em.contains(foundEntity) ? em.remove(foundEntity) : em.merge(foundEntity));
//			
//			
//		em.getTransaction().commit();
//
//		em.close();
//	}


	@Override
	public boolean exists(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
