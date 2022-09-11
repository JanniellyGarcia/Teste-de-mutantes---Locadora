package negocio;

public class Locacao {

	protected Cliente cliente;
	protected Filme filme;
	protected double valorAluguel;
	protected String data;
	protected String hora;
	
	
	public String alugar(Cliente c, Filme f, String data, String hora) {
		if(c.status == true) {
			this.cliente = c;
			this.filme = f;
			this.data = data;
			this.hora = hora;
			return "Operação Realizada!";
			
		}
		else {
			return("Cliente Inativo. Operação não pode ser realizada!");
			
		}
		
		
	}
	public void setValorAluguel(double valorAluguel) {
		this.valorAluguel=valorAluguel;
	}
	public String locacaoComDesconto(Cliente c, Filme f, String data, String hora) {
		
		if(c.status == true) {
			this.cliente = c;
			this.filme = f;
			this.data = data;
			this.hora = hora;
			switch (f.genero) {
			case ROMANCE: 
				this.valorAluguel = f.valorAluguel * 0.8;
				break;
			case DRAMA: 
				this.valorAluguel = f.valorAluguel * 0.9;
				break;
			case COMEDIA:
				this.valorAluguel = f.valorAluguel * 0.85;
				break;
			default:
				break;
			}
			return "Operação Realizada!";
			
		}
		else {
			
			return "Cliente Inativo. Operação não pode ser realizada!";
		}
		
	}
	
}
