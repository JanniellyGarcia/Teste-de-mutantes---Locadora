package negocio;

import java.util.ArrayList;

public class Cliente {

	protected String nome;
	protected int id;
	protected boolean status;
	protected  ArrayList<Filme> filmesFavoritos;
	
	/* Cliente Ativo status = true;
	 Cliente Inativo status = false;*/
	
	public Cliente(String nome, int id, boolean status) {
		this.nome= nome;
		this.id= id;
		this.status = status;
		filmesFavoritos = new ArrayList<Filme>();
		
	}
	
	public Filme buscaFilmeFavorito(Cliente cliente, String nomeFilme) {
		for (Filme filme : cliente.filmesFavoritos) {
			if(filme.nome==nomeFilme) {
				return filme;
			}
			
		}
		return null;
	}
}
