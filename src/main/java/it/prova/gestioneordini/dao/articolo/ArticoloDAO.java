package it.prova.gestioneordini.dao.articolo;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Ordine;

public interface ArticoloDAO extends IBaseDAO<Articolo>{
	
	public int totalSumOfArticlesByACertianOrder(Ordine ordineInput) throws Exception;
	
	public boolean controlloPresenzaOrdini(Long idOrdine) throws Exception; 
	
	public boolean findIfArticlesHasCategories(Long idArticolo) throws Exception;
	
}
