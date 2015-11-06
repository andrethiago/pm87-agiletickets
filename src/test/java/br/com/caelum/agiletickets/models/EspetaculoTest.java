package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void deveCriar4SessoesComPeriodicidadeDiaria() {
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusDays(3);
		LocalTime horaSessao = LocalTime.now();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horaSessao, Periodicidade.DIARIA);
		
		assertTrue(sessoes.size() == 4);
	}
	
	@Test
	public void deveCriar5SessoesComPeriodicidadeSemanal() {
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusWeeks(4);
		LocalTime horaSessao = LocalTime.now();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horaSessao, Periodicidade.SEMANAL);
		
		assertTrue(sessoes.size() == 5);
	}
	
	@Test
	public void deveCriar1SessaoQuandoDatasIguais() {
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio;
		LocalTime horaSessao = LocalTime.now();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horaSessao, Periodicidade.DIARIA);
		
		assertTrue(sessoes.size() == 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarSessoesComDataInicialMaiorQueFinal() {
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.minusWeeks(1);
		LocalTime horaSessao = LocalTime.now();
		
		Espetaculo espetaculo = new Espetaculo();
		
		espetaculo.criaSessoes(inicio, fim, horaSessao, Periodicidade.SEMANAL);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveriaCriarSessoesDiariasQuandoDataInicioMaiorQueDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate amanha = hoje.plusDays(1);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade diaria = Periodicidade.DIARIA;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(amanha, hoje, agora, diaria);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
	  	//Nao tem asserts pq deveria jogar exception...
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDeveriaCriarSessoesSemanaisQuandoDataInicioMaiorQueDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate amanha = hoje.plusDays(1);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade semanal = Periodicidade.SEMANAL;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(amanha, hoje, agora, semanal);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
	  	//Nao tem asserts pq deveria jogar exception...
	}

	@Test
	public void deveriaCriarApenasUmaSessaoParaPeriodicidadeDiariaComDataInicioIgualDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalTime agora = new LocalTime();
	  	Periodicidade diaria = Periodicidade.DIARIA;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, hoje, agora, diaria);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		assertEquals(1, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
	  	Sessao unica = sessoes.get(0);
	  	assertEquals(show, unica.getEspetaculo());
	    assertEquals(hoje.toDateTime(agora), unica.getInicio());
	}

	@Test
	public void deveriaCriarApenasUmaSessaoParaPeriodicidadeSemanalComDataInicioIgualDataFim() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalTime agora = new LocalTime();
	  	Periodicidade semanal = Periodicidade.SEMANAL;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, hoje, agora, semanal);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		assertEquals(1, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
	  	Sessao unica = sessoes.get(0);
	  	assertEquals(show, unica.getEspetaculo());
	    assertEquals(hoje.toDateTime(agora), unica.getInicio());
	}

	@Test
	public void deveriaCriarCincoSessoesParaPeriodicidadeDiariaComIntervaloDeCincoDias() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate daquiQuatroDias = hoje.plusDays(4);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade diaria = Periodicidade.DIARIA;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, daquiQuatroDias, agora, diaria);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		assertEquals(5, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
		for(int i = 0; i < sessoes.size(); i++) {
			Sessao criada = sessoes.get(i);
			assertEquals(show, criada.getEspetaculo());
			assertEquals(hoje.plusDays(i).toDateTime(agora), criada.getInicio());
	    }
	}

	@Test
	public void deveriaCriarCincoSessoesParaPeriodicidadeSemanalComIntervaloDeCincoSemanas() {
		//DADAS ESTAS ENTRADAS:
		LocalDate hoje = new LocalDate();
	  	LocalDate daquiQuatroSemanas = hoje.plusWeeks(4);
	  	LocalTime agora = new LocalTime();
	  	Periodicidade semanal = Periodicidade.SEMANAL;
	  
	  	//QUANDO EU DISPARAR O PROCESSAMENTO:
	  	Espetaculo show = new Espetaculo();
	  	List<Sessao> sessoes = show.criaSessoes(hoje, daquiQuatroSemanas, agora, semanal);
	  
	  	//ESTAS SAO AS SAIDAS ESPERADAS:
		assertEquals(5, sessoes.size());
	  
	  	//Nao basta apenas verificar o size da lista, precisa garantir que criou as sessoes corretamente:
		for(int i = 0; i < sessoes.size(); i++) {
			Sessao criada = sessoes.get(i);
			assertEquals(show, criada.getEspetaculo());
			assertEquals(hoje.plusWeeks(i).toDateTime(agora), criada.getInicio());
	    }
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
