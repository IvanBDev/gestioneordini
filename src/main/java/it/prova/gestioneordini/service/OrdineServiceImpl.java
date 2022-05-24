package it.prova.gestioneordini.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;
public class OrdineServiceImpl implements OrdineService{
	
	private OrdineDAO ordineDaoInstance;

	@Override
	public void setOrdineDAO(OrdineDAO ordineDaoInstance) throws Exception {
		// TODO Auto-generated method stub
		this.ordineDaoInstance = ordineDaoInstance;
	}

	@Override
	public List<Ordine> listAll() throws Exception {
		// TODO Auto-generated method stub
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDaoInstance.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDaoInstance.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine inserisciNuovo(Ordine ordineInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Ordine ordineInput) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Ordine ordine) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ordine> trovaTuttiGliOrdiniDiUnaCertaCategoria(Categoria catecoriaInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordine> trovaTuttiGliOrdiniRecentiDiUnaCertaCategoria(Categoria categoriaInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
