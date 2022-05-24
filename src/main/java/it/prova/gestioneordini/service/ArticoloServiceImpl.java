package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.articolo.ArticoloDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class ArticoloServiceImpl implements ArticoloService{

	@Override
	public void setArticoloDAO(ArticoloDAO articoloDaoInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Articolo> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
	public void aggiorna(Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idCd) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
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
