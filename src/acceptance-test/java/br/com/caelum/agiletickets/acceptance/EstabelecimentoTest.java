package br.com.caelum.agiletickets.acceptance;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import br.com.caelum.agiletickets.acceptance.page.EstabelecimentosPage;

public class EstabelecimentoTest {

	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private EstabelecimentosPage estabelecimentos;

	@BeforeClass
	public static void abreBrowser() {
		browser = new HtmlUnitDriver();
	}

	@Before
	public void setUp() throws Exception {
		estabelecimentos = new EstabelecimentosPage(browser);
	}

	@AfterClass
	public static void teardown() {
		browser.close();
	}

	@Test
	public void aoAdicionarUmEstabelecimentoDeveMostraLoNaTabela() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("Caelum", "R. Vergueiro, 3185");

		estabelecimentos.ultimaLinhaDeveConter("Caelum", "R. Vergueiro, 3185");
	}

	@Test
	public void aoAdicionarUmEstabelecimentoSemNomeDeveMostrarErro() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("", "R. Vergueiro, 3185");

		estabelecimentos.deveMostrarErro("O nome não pode ser vazio");
	}

	@Test
	public void aoAdicionarUmEstabelecimentoSemEnderecoDeveMostrarErro() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimento("Caelum", "");

		estabelecimentos.deveMostrarErro("O endereco não pode ser vazio");
	}

	@Test
	public void mostraQueHaEstacionamentoQuandoCadastramosQueSim() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(true);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(true);
	}

	@Test
	public void mostraQueNaoHaEstacionamentoQuandoCadastramosQueNao() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(false);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(false);
	}
	
}
