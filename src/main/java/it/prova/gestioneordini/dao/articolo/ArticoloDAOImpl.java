package it.prova.gestioneordini.dao.articolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Ordine;

public class ArticoloDAOImpl implements ArticoloDAO{
	
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		// TODO Auto-generated method stub
		this.entityManager = entityManager;
	}

	@Override
	public List<Articolo> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Articolo", Articolo.class).getResultList();
	}

	@Override
	public Articolo get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Articolo.class, id);
	}

	@Override
	public void update(Articolo input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.merge(input);
	}

	@Override
	public void insert(Articolo input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");

		entityManager.persist(input);
	}

	@Override
	public void delete(Articolo input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public int totalSumOfArticlesByACertianOrder(Ordine ordineInput) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean controlloPresenzaOrdini(Long idOrdine) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Articolo> query = entityManager.createQuery("SELECT o.* FROM Articolo a INNER JOIN a.ordine o WHERE o.id = :idOrdine", Articolo.class);
		query.setParameter("idOrdine", idOrdine);
		return query.getResultList().isEmpty();
	}

	@Override
	public boolean findIfArticlesHasCategories(Long idArticolo) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Articolo> query = entityManager.createQuery("FROM Articolo a INNER JOIN a.categorie c WHERE a.id = :idArticolo", Articolo.class); 
		query.setParameter("idArticolo", idArticolo);
		
		return query.getResultList().isEmpty();
	}

}
