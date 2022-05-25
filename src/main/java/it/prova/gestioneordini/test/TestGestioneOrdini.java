package it.prova.gestioneordini.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import it.prova.gestioneordini.dao.EntityManagerUtil;
import it.prova.gestioneordini.model.Articolo;
import it.prova.gestioneordini.model.Categoria;
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
			System.out.println(
					"Nella tabella Ordini sono presenti: " + ordineServiceInstance.listAll().size() + " elementi");
			// testInserisciNuovoOrdine(ordineServiceInstance);
			// testInserisciArticoloAOrdine(ordineServiceInstance, articoloServiceInstance);

			// testAggiornaRecordOrdine(ordineServiceInstance);

			// testAggiungiCategoriaAdArticoli(articoloServiceInstance,
			// categoriaServiceInstance);
			// testAggiungiArticoloACategoria(ordineServiceInstance,
			// articoloServiceInstance, categoriaServiceInstance);

			// testRimuoviArticoloDaOrdine(ordineServiceInstance, articoloServiceInstance);

			// testTrovaTuttiGliOrdiniDiUnaCertaCategoria(ordineServiceInstance,
			// categoriaServiceInstance);

			// testSommaDegliArticoliDataUnaDeterminataCategoria(articoloServiceInstance,
			// categoriaServiceInstance);

			// testTrovaTutteLeCategorieDistinteDaArticoliDiUnCertoOrdine(categoriaServiceInstance,
			// ordineServiceInstance);

			//testTrovaTuttiGliOrdiniRecentiDiUnaCertaCategoria(categoriaServiceInstance, ordineServiceInstance)

			//testTrovaTuttiICodiciDistintiDaOrdiniRisalentiAlMeseDiFebbraio2022(ordineServiceInstance, articoloServiceInstance, categoriaServiceInstance);
			
			testSommaTotaleDegliArticoliIntestatiAMarioRossi(ordineServiceInstance, articoloServiceInstance);
			

			System.out.println(
					"Nella tabella Ordini sono presenti: " + ordineServiceInstance.listAll().size() + " elementi");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}

	public static void testInserisciNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".....................testInserisciNuovoOrdine inizio: ..................................");

		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2022");
		Ordine nuovoOrdine = new Ordine("Ivan", "Via Firenze 112", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");

		System.out.println(
				".....................testInserisciNuovoOrdine fine: PASSED..................................");
	}

	public static void testAggiornaRecordOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".....................testAggiornaRecordOrdine inizio: ..................................");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if (listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");

		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("25/06/2021");
		Ordine nuovoOrdine = new Ordine("Paolo", "Via Firenze 111", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");

		Ordine ordineUpdate = nuovoOrdine;
		// ordineUpdate.setNomeDestinatario("");
		ordineUpdate.setIndirizzoSpedizione("Via Firenze 112");
		// ordineUpdate.setDataSpedizione(new SimpleDateFormat("dd/MM/yyyy").parse(""));

		ordineServiceInstance.aggiorna(ordineUpdate);

		System.out.println(
				".....................testAggiornaRecordOrdine fine: PASSED..................................");
	}

	public static void testInserisciArticoloAOrdine(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance) throws Exception {
		System.out.println(
				".....................testInserisciArticoloAOrdine inizio: ..................................");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if (listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");

		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("12/11/2021");
		Ordine nuovoOrdine = new Ordine("Antonio", "Via Delle Barche 71", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");

		Date dataInserimentoArticolo = new SimpleDateFormat("dd/MM/yyyy").parse("10/11/2021");
		Articolo nuovoArticolo = new Articolo("Nintendo Switch", "NTDST350", 350, dataInserimentoArticolo, nuovoOrdine);

		articoloServiceInstance.inserisciNuovo(nuovoArticolo);

		System.out.println(
				".....................testInserisciArticoloAOrdine fine: PASSED..................................");
	}

	public static void testAggiungiCategoriaAdArticoli(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(
				".....................testAggiungiCategoriaAdArticoli inizio: ..................................");

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		if (listaArticoli.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");

		Articolo articoloPrivoDiCategoria = listaArticoli.get(0);

		Categoria nuovaCategoria = new Categoria("00", "Videogioco");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if (nuovaCategoria.getId() == null)
			throw new RuntimeException("Non ci sono categorie nel DB");

		articoloServiceInstance.aggiungiCategoria(articoloPrivoDiCategoria, nuovaCategoria);

		System.out.println(
				".....................testAggiungiCategoriaAdArticoli fine: PASSED..................................");
	}

	public static void testAggiungiArticoloACategoria(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(
				".....................testAggiungiArticoloACategoria inizio: ..................................");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if (listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");

		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("29/03/2021");
		Ordine nuovoOrdine = new Ordine("Anna", "Via Castoro 5", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		List<Articolo> listaArticoli = articoloServiceInstance.listAll();
		if (listaArticoli.isEmpty())
			throw new RuntimeException("Non ci sono proprietari nel DB");

		Date dataInserimentoArticolo = new SimpleDateFormat("dd/MM/yyyy").parse("15/07/2021");
		Articolo nuovoArticolo = new Articolo("Animal Crossing", "AMCS60", 60, dataInserimentoArticolo, nuovoOrdine);

		Categoria nuovaCategoria = new Categoria("Videogioco", "00");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if (nuovaCategoria.getId() == null)
			throw new RuntimeException("Non ci sono categorie nel DB");

		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo);

		System.out.println(
				".....................testAggiungiArticoloACategoria fine: PASSED..................................");
	}

	public static void testRimuoviArticoloDaOrdine(OrdineService ordineServiceInstance,
			ArticoloService articoloServiceInstance) throws Exception {
		System.out
				.println(".....................testRimuoviArticoloDaOrdine inizio: ..................................");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if (listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono ordini nel DB");

		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("19/07/2021");
		Ordine nuovoOrdine = new Ordine("Sofiaaa", "Via Monteroni 10", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciOrdine fallito: record già presente ");

		Date dataInserimentoArticolo = new SimpleDateFormat("dd/MM/yyyy").parse("17/07/2021");
		Articolo nuovoArticolo = new Articolo("Maglietta Anime5", "MGTANM555", 55, dataInserimentoArticolo);

		nuovoArticolo.setOrdine(nuovoOrdine);
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciArticolo fallito ");

		articoloServiceInstance.inserisciNuovo(nuovoArticolo);

		articoloServiceInstance.rimuovi(nuovoArticolo.getId());

		System.out.println(
				".....................testRimuoviArticoloDaOrdine fine: PASSED..................................");
	}

	public static void testTrovaTuttiGliOrdiniDiUnaCertaCategoria(OrdineService ordineServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(
				".....................testTrovaTuttiGliOrdiniDiUnaCertaCategoria inizio: ..................................");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		if (listaCategorie.isEmpty())
			throw new RuntimeException("Non ci sono categorie nel DB");

		Categoria categoriaPerRicerca = listaCategorie.get(0);

		List<Ordine> listaRisultato = ordineServiceInstance.trovaTuttiGliOrdiniDiUnaCertaCategoria(categoriaPerRicerca);
		for (Ordine ordineItem : listaRisultato) {
			System.out.println(ordineItem.getNomeDestinatario());
		}

		System.out.println(
				".....................testTrovaTuttiGliOrdiniDiUnaCertaCategoria fine: PASSED..................................");
	}

	public static void testSommaDegliArticoliDataUnaDeterminataCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(
				".....................testSommaDegliArticoliDataUnaDeterminataCategoria inizio: ..................................");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		if (listaCategorie.isEmpty())
			throw new RuntimeException("Non ci sono categorie nel DB");

		System.out.println(articoloServiceInstance.sommaTotaleDegliArticoliDiUnCertoOrdine(listaCategorie.get(0)));

		System.out.println(
				".....................testSommaDegliArticoliDataUnaDeterminataCategoria fine: PASSED..................................");
	}

	public static void testTrovaTutteLeCategorieDistinteDaArticoliDiUnCertoOrdine(
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(
				".....................testTrovaTutteLeCategorieDistinteDaArticoliDiUnCertoOrdine inizio: ..................................");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		if (listaCategorie.isEmpty())
			throw new RuntimeException("Non ci sono categorie nel DB");

		Ordine ordinePerRicerca = ordineServiceInstance.listAll().get(2);

		List<Categoria> risultatoRicerca = categoriaServiceInstance
				.trovaTutteLeCategorieDistinteDaArticoliDiUnCertoOrdine(ordinePerRicerca);
		System.out.println(risultatoRicerca.size());

		System.out.println(
				".....................testTrovaTutteLeCategorieDistinteDaArticoliDiUnCertoOrdine fine: PASSED..................................");
	}

	public static void testTrovaTuttiGliOrdiniRecentiDiUnaCertaCategoria(CategoriaService categoriaServiceInstance,
			OrdineService ordineServiceInstance) throws Exception {
		System.out.println(
				".....................testTrovaTuttiGliOrdiniRecentiDiUnaCertaCategoria inizio: ..................................");

		List<Categoria> listaCategorie = categoriaServiceInstance.listAll();
		if (listaCategorie.isEmpty())
			throw new RuntimeException("Non ci sono categorie nel DB");

		List<Ordine> listaOrdini = ordineServiceInstance.listAll();
		if (listaOrdini.isEmpty())
			throw new RuntimeException("Non ci sono ordini nel DB");

		Categoria risultato = categoriaServiceInstance.listAll().get(0);
		Ordine ordineRicerca = ordineServiceInstance.trovaTuttiGliOrdiniRecentiDiUnaCertaCategoria(risultato);

		System.out.println(ordineRicerca.getNomeDestinatario() + ", " + ordineRicerca.getDataSpedizione());

		System.out.println(
				".....................testTrovaTuttiGliOrdiniRecentiDiUnaCertaCategoria fine: PASSED..................................");
	}

	public static void testTrovaTuttiICodiciDistintiDaOrdiniRisalentiAlMeseDiFebbraio2022(
			OrdineService ordineServiceInstance, ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		
		System.out.println(
				".....................testTrovaTuttiICodiciDistintiDaOrdiniRisalentiAlMeseDiFebbraio2022 inizio: ..................................");
		
		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("21/02/2022");
		Ordine nuovoOrdine = new Ordine("Vittorio", "Via Lucio 20", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");
		
		Date dataInserimentoArticolo = new SimpleDateFormat("dd/MM/yyyy").parse("19/02/2022");
		Articolo nuovoArticolo = new Articolo("Super Mario", "SPM60", 60, dataInserimentoArticolo);
		nuovoArticolo.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		
		Categoria nuovaCategoria = new Categoria("Videogiochi", "00");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		 
		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo);
		
		List<String> risultatoRicerca = categoriaServiceInstance.trovaTuttiICodiciDistintiDaOrdiniRisalentiAlMeseDiFebbraio2022();
		System.out.println(risultatoRicerca.size());
		
		System.out.println(
				".....................testTrovaTuttiICodiciDistintiDaOrdiniRisalentiAlMeseDiFebbraio2022 fine: PASSED..................................");
	}
	
	public static void testSommaTotaleDegliArticoliIntestatiAMarioRossi(OrdineService ordineServiceInstance, ArticoloService articoloServiceInstance) throws Exception{
		System.out.println(
				".....................testSommaTotaleDegliArticoliIntestatiAMarioRossi inizio: ..................................");
		
		Date dataSpedizione = new SimpleDateFormat("dd/MM/yyyy").parse("29/07/2022");
		Ordine nuovoOrdine = new Ordine("Mario Rossi", "Viale Abruzzo 12", dataSpedizione);

		if (nuovoOrdine.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		ordineServiceInstance.inserisciNuovo(nuovoOrdine);

		if (nuovoOrdine.getId() == null)
			throw new Exception("testInserisciProprietario fallito ");
		
		Date dataInserimentoArticolo = new SimpleDateFormat("dd/MM/yyyy").parse("26/07/2022");
		Articolo nuovoArticolo = new Articolo("Giacca Gucci", "GCGC865", 865, dataInserimentoArticolo);
		nuovoArticolo.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		
		System.out.println(articoloServiceInstance.sommaTotaleDegliArticoliIntestatiAMarioRossi());
			
		System.out.println(
				".....................testSommaTotaleDegliArticoliIntestatiAMarioRossi fine: PASSED..................................");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
