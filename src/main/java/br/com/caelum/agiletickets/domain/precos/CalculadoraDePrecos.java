package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	private static final double VINTE_POR_CENTO = 0.20;
	private static final double CINQUENTA_POR_CENTO = 0.50;
	private static final double DEZ_POR_CENTO = 0.10;
	private static final double CINCO_POR_CENTO = 0.05;

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPrecoEspetaculo();
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}