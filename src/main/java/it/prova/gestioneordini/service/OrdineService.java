package it.prova.gestioneordini.service;

import java.util.List;

import it.prova.gestioneordini.dao.ordine.OrdineDAO;
import it.prova.gestioneordini.model.Categoria;
import it.prova.gestioneordini.model.Ordine;

public interface OrdineService {

	public void setOrdineDAO(OrdineDAO ordineDAO) throws Exception;

	public List<Ordine> listAll() throws Exception;

	public void inserisciNuovo(Ordine ordineInput) throws Exception;

	public void aggiorna(Ordine ordineInput) throws Exception;

	public void rimuovi(Ordine ordine) throws Exception;

	public List<Ordine> trovaTuttiGliOrdiniDiUnaCertaCategoria(Categoria catecoriaInput) throws Exception;

	public List<Ordine> trovaTuttiGliOrdiniRecentiDiUnaCertaCategoria(Categoria categoriaInput) throws Exception;

}
