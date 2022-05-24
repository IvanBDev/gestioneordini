package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.categoria.CategoriaDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService{

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDaoInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Long idGenere) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiungiCd(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> trovaTutteLeCategorieDistinteDaArticoliDiUnCertoOrdine(Ordine ordineInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
