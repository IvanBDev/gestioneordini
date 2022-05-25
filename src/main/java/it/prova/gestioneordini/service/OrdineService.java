package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineService {

	public void setOrdineDAO(OrdineDAO ordineDAO) throws Exception;

	public List<Ordine> listAll() throws Exception;

	public void inserisciNuovo(Ordine ordineInput) throws Exception;

	public void aggiorna(Ordine ordineInput) throws Exception;

	public void rimuovi(Long ordineId) throws Exception;
	
	public void rimuoviArticolo(Ordine ordineInput, Articolo articoloInput) throws Exception;

	public List<Ordine> trovaTuttiGliOrdiniDiUnaCertaCategoria(Categoria catecoriaInput) throws Exception;

	public Ordine trovaTuttiGliOrdiniRecentiDiUnaCertaCategoria(Categoria categoriaInput) throws Exception;
	
	public void aggiungiArticolo(Ordine ordineInput, Articolo articoloInput) throws Exception;
	
	public List<String> trovaTuttiGliIndirizziDistintiDatoUnEstrattoDINumeroSeriale(String codiceStringa) throws Exception;

}
