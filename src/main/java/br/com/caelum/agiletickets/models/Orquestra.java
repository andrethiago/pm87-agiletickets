package br.com.caelum.agiletickets.models;

import java.math.BigDecimal;

public class Orquestra extends Espetaculo {
	
	public BigDecimal calculaPreco(Sessao sessao) {
		BigDecimal preco = sessao.getPreco();
		if(sessao.quantidadeIngressosDisponiveisMenorOuIgualA(CINQUENTA_POR_CENTO)) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(VINTE_POR_CENTO)));
		}
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(DEZ_POR_CENTO)));
		}
		
		return preco;
	}

}
