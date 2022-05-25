package it.prova.gestioneordini.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.dao.articolo.ArticoloDAO;
import it.prova.gestioneordini.exception.CustomException;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDAO articoloDAO;

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDAO) throws Exception {
		// TODO Auto-generated method stub
		this.articoloDAO = articoloDAO;
	}

	@Override
	public List<Articolo> listAll() throws Exception {
		// TODO Auto-generated method stub
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Articolo caricaSingoloElementoEagerGeneri(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Articolo articoloInput) throws Exception {
		// TODO Auto-generated method stub
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDAO.update(articoloInput);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Articolo articoloInput) throws Exception {
		// TODO Auto-generated method stub
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDAO.insert(articoloInput);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idInput) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			if(articoloDAO.findIfArticlesHasCategories(idInput) == false)
				articoloDAO.delete(articoloDAO.get(idInput));
			else
				throw new CustomException("Articolo ha al suo interno delle categorie");

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	
	@Override
	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			articoloInstance = entityManager.merge(articoloInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			categoriaInstance = entityManager.merge(categoriaInstance);

			articoloInstance.getCategorie().add(categoriaInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public int sommaTotaleDegliArticoliDiUnCertoOrdine(Ordine ordineInput) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void creaECollegaArticoloECategoria(Articolo articoloTransientInstance, Categoria categoriaTransientInstance)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
