package it.prova.gestioneordini.dao.articolo;

import it.prova.gestioneordini.dao.IBaseDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;

public interface ArticoloDAO extends IBaseDAO<Articolo>{
	
	public Long totalSumOfArticlesByACertianOrder(Categoria categoria) throws Exception;
	
	public boolean findIfArticlesHasCategories(Long idArticolo) throws Exception;
	
}
