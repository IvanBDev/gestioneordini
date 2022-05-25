package it.prova.gestioneordini.dao.ordine;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null)
			throw new Exception("Valore in input non valido");
		
		entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null)
			throw new Exception("Problema valore in input");

		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		// TODO Auto-generated method stub
		if(input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public List<Ordine> findAllOrdersWithACertianCategory(Categoria catecoriaInput) throws Exception {
		// TODO Auto-generated method stub
		TypedQuery<Ordine> query = entityManager.createQuery("select o from Ordine o join o.articoli a join a.categorie c where c.id = :idCategoria", Ordine.class);
		query.setParameter("idCategoria", catecoriaInput.getId());
		return query.getResultList();
	}

	@Override
	public List<Ordine> findAllRecentOrdersOfACertianCategory(Categoria categoriaInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findIfOrderHasArticles(Long idOrdine) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery("FROM Ordine o INNER JOIN o.articoli a WHERE o.id = :idOrdine", Ordine.class); 
		query.setParameter("idOrdine", idOrdine);
		
		return query.getResultList().isEmpty();
	}

}
