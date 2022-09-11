package negocio;

import java.util.ArrayList;

public class Transacao {

	protected  ArrayList<Locacao> alugueis;
	
	public Transacao() {
		
		alugueis= new ArrayList<Locacao>();
	}
	
	public double valorLocacaoTotal() {
	    double valor=0;
		for (Locacao locacao : alugueis) {
			valor +=locacao.filme.valorCompra;
		}
		return valor;
	}
	
	public Cliente buscaCliente(int id) {
		for (Locacao locacao : alugueis) {
			if(locacao.cliente.id==id) {
				return locacao.cliente;
			}
			
		}
		return null;
	}
	
	
	
	public double calculoLucro(int filmeId) {
		double valor=0;
		Filme aux = null;
		for (Locacao locacao : alugueis) {
			if(locacao.filme.id==filmeId) {
				valor += locacao.valorAluguel;
				aux = locacao.filme;
			}
			
		}
		return (valor*100)/aux.valorCompra;
	}
	
	
	public String retornaOGeneroMaisAlugado(ArrayList<Locacao> alugueis) {
		int qtdRomance = 0;
		int qtdDrama = 0;
		int qtdComedia = 0;
		for (Locacao locacao : alugueis) {
			if(locacao.filme.genero == Genero.ROMANCE) {
				qtdRomance = qtdRomance + 1;
			} else if (locacao.filme.genero == Genero.DRAMA) {
				qtdDrama = qtdDrama + 1;
			} else {
				qtdComedia = qtdComedia + 1;
			}
	     }
		int quantidade[] = {qtdRomance, qtdDrama, qtdComedia};
		int max = quantidade[0];
		int genero=0;
	    for(int i = 0; i < quantidade.length; i++){
	      if(quantidade[i] > max){
	        genero = i;
	      }
	    }
	     
	    if(genero == 0) {
	    	return "O gênero mais alugado é Romance!";
	    } else  if(genero == 1) {
	    	return "O gênero mais alugado é Drama!";
	    } else {
	    	return "O gênero mais alugado é Comédia!";
	    }
	}
	
	
	
}