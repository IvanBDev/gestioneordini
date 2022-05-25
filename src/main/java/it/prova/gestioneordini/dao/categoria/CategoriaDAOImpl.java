package it.prova.gestioneordini.dao.categoria;

import java.util.List;
import javax.persistence.EntityManager;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class CategoriaDAOImpl implements CategoriaDAO{
	
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public List<Categoria> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Categoria.class, id);
	}

	@Override
	public void update(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.merge(input);
	}

	@Override
	public void insert(Categoria input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");

		entityManager.persist(input);
	}

	@Override
	public void delete(Categoria o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> findAllCategoriesDistinctByArticlesOfACertianOrder(Ordine ordineInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
