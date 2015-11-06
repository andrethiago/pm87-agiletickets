package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public class Cinema extends Espetaculo {
	
	public BigDecimal calculaPreco(Sessao sessao) {
		BigDecimal preco = sessao.getPreco();
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= CINCO_POR_CENTO) {
			preco = preco.add(preco.multiply(BigDecimal.valueOf(DEZ_POR_CENTO)));
		}
		return preco;
	}

}
