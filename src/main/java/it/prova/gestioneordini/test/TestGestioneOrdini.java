package it.prova.gestioneordini.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Ordine;
import it.prova.gestioneordini.service.ArticoloService;
import it.prova.gestioneordini.service.CategoriaService;
import it.prova.gestioneordini.service.MyServiceFactory;
import it.prova.gestioneordini.service.OrdineService;

public class TestGestioneOrdini {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		
		try {
			System.out.println("Nella tabella Ordini sono presenti: "+ ordineServiceInstance.listAll().size()+ " elementi");
			//testInserisciNuovoOrdine(ordineServiceInstance);
			testInserisciArticoloAOrdine(ordineServiceInstance, articoloServiceInstance);
			
			//testAggiornaRecordOrdine(ordineServiceInstance);
			
			System.out.println("Nella tabella Ordini sono presenti: "+ ordineServiceInstance.listAll().size()+ " elementi");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}
	
	public static void testInserisciNuovoOrdine(OrdineService ordineServiceInstance) throws Exception{
		System.out.println(".....................testInserisciNuovoOrdine inizio: ..................................");
		
		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2022");
		Ordine nuovoOrdine = new Ordine("Ivan", "Via Firenze 112", dataSpedizione);
		
		if(nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
		
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		
		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");
			
		
		System.out.println(".....................testInserisciNuovoOrdine fine: PASSED..................................");
	}
	
	public static void testAggiornaRecordOrdine(OrdineService ordineServiceInstance) throws Exception{
		System.out.println(".....................testAggiornaRecordOrdine inizio: ..................................");
		
		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if(listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");
		
		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("25/06/2021");
		Ordine nuovoOrdine = new Ordine("Paolo", "Via Firenze 111", dataSpedizione);
		
		if(nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
		
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		
		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");
		
		Ordine ordineUpdate = nuovoOrdine;
		//ordineUpdate.setNomeDestinatario("");
		ordineUpdate.setIndirizzoSpedizione("Via Firenze 112");
		//ordineUpdate.setDataSpedizione(new SimpleDateFormat("dd/MM/yyyy").parse(""));
		
		ordineServiceInstance.aggiorna(ordineUpdate);
		
		System.out.println(".....................testAggiornaRecordOrdine fine: PASSED..................................");
	}
	
	public static void testInserisciArticoloAOrdine(OrdineService ordineServiceInstance, ArticoloService articoloServiceInstance) throws Exception{
		System.out.println(".....................testInserisciArticoloAOrdine inizio: ..................................");
		
		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if(listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");
		
		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2021");
		Ordine nuovoOrdine = new Ordine("Antonio", "Via Delle Barche 71", dataSpedizione);
		
		if(nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");
		
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		
		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");
		
		Date dataInserimentoArticolo = new SimpleDateFormat("dd/MM/yyyy").parse("10/11/2021");
		Articolo nuovoArticolo = new Articolo("Scarpe Nike", "SCPNK120", 180, dataInserimentoArticolo, nuovoOrdine);
		
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		
		System.out.println(".....................testInserisciArticoloAOrdine fine: PASSED..................................");
	}

}
