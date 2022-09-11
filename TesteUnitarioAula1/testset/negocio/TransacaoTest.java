package negocio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.Cliente;
import negocio.Filme;
import negocio.Genero;
import negocio.Locacao;
import negocio.Transacao;

public class TransacaoTest {

	Transacao transacao;
	Locacao locacao1;
	Locacao locacao2;
	Filme filme1;
	Filme filme2;

	@Before
	public void setUp() throws Exception {
		
		locacao1 = new Locacao();
		locacao2 = new Locacao();
		filme1 = new Filme("Java", Genero.ROMANCE);
		filme1.valorCompra = 100;

		filme2 = new Filme("JavaScript", Genero.ROMANCE);
		filme2.valorCompra = 50;
		filme2.id=20;

		locacao1.alugar(new Cliente("Izaias", 2, true), filme1, "21/06/2022", "19:00");
		locacao2.alugar(new Cliente("Thiago", 3, true), filme2, "20/06/2022", "22:00");
		

		transacao = new Transacao();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void calculoLucroTestFunciona() {
		Filme filme = new Filme("odio",Genero.DRAMA);
		filme.id = 633;
		Cliente cliente1 = new Cliente("Jannielly", 5, true);
		Cliente cliente2 = new Cliente("Artur", 6, true);
		filme.valorCompra = 300;
		filme.valorAluguel = 200;
		Locacao locacao1 = new Locacao();
		locacao1.setValorAluguel(200);
		Locacao locacao2 = new Locacao();
		locacao2.setValorAluguel(200);
		locacao1.alugar(cliente1, filme, "20/06/2022", "22:00");
		locacao2.alugar(cliente2, filme, "23/06/2022", "12:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		
		assertEquals(133.33, transacao.calculoLucro(633), 0.1);
	}
	
	@Test
	public void setValorAluguel() {
		Locacao locacao = new Locacao();
		locacao.setValorAluguel(10);
		
		assertEquals(10, locacao.valorAluguel, 0.1);
	}

	@Test
	public void valorLocacaoTotalTest() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		assertEquals(transacao.alugueis.get(0).cliente.nome, "Izaias");
		assertTrue("Filme deveria ser selecionado corretamente",transacao.alugueis.get(1).filme.id==20);
		assertEquals(150, transacao.valorLocacaoTotal(), 0.1);
	}

	@Test
	public void valorLocacaoTotalTest2() {

		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao2);
		assertEquals(200, transacao.valorLocacaoTotal(), 0.1);
	}

	@Test 
	public void buscaClienteIdTest() {
		
		transacao.alugueis.add(locacao2);
		assertEquals("Thiago",transacao.buscaCliente(3).nome);
	}
	
	@Test 
	public void buscaClienteIdNullTest() {
		transacao.alugueis.add(locacao2);
		assertNull(transacao.buscaCliente(5));
	}
	
	//Test questão 1:
	@Test
	public void dataTest() {
		
		locacao2.data = "19/06/2022";
		
		
		assertEquals("19/06/2022", locacao2.data);
		
	}
	
	@Test
	public void horatest() {
		
		locacao2.hora = "07:00";
		
		assertEquals("07:00", locacao2.hora);
		
	}
	
	//Test questão 2:
	@Test
	public void statusClienteAtivoTest () {
		
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		String aux = locacao.alugar(cliente3, filme2, "20/06/2022", "22:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao);
		
		
		assertEquals("João", transacao.buscaCliente(3).nome);
		assertEquals("Operação Realizada!",aux);
	}
	
	@Test
	public void statusClienteInativoTest () {
		
		Cliente cliente3 = new Cliente("João", 3, false);
		Locacao locacao = new Locacao();
		
		assertEquals("Cliente Inativo. Operação não pode ser realizada!", locacao.alugar(cliente3, filme2, "20/06/2022", "22:00"));
	}
	
	//Test Questão 3:
	@Test
	public void GeneroMaisAlugadoRomanceTest() {
		
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Locacao locacao1 = new Locacao();
		Locacao locacao2 = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
		Filme filme2 = new Filme("CSharp", Genero.ROMANCE);
		Filme filme3 = new Filme("Java", Genero.DRAMA);
		locacao.alugar(cliente3, filme1, "20/06/2022", "22:00");
		locacao1.alugar(cliente3, filme2, "20/06/2022", "22:00");
		locacao2.alugar(cliente3, filme3, "20/06/2022", "22:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao);
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		
		
		assertEquals("O gênero mais alugado é Romance!", transacao.retornaOGeneroMaisAlugado(transacao.alugueis));
	}
	
	
	@Test
	public void GeneroMaisAlugadoDramaTest() {
		
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Locacao locacao1 = new Locacao();
		Locacao locacao2 = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
		Filme filme2 = new Filme("CSharp", Genero.DRAMA);
		Filme filme3 = new Filme("Java", Genero.DRAMA);
		locacao.alugar(cliente3, filme1, "20/06/2022", "22:00");
		locacao1.alugar(cliente3, filme2, "20/06/2022", "22:00");
		locacao2.alugar(cliente3, filme3, "20/06/2022", "22:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao);
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		
		
		assertEquals("O gênero mais alugado é Drama!", transacao.retornaOGeneroMaisAlugado(transacao.alugueis));
	}
	
	@Test
	public void GeneroMaisAlugadoComediaTest() {
		
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Locacao locacao1 = new Locacao();
		Locacao locacao2 = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
		Filme filme2 = new Filme("CSharp", Genero.COMEDIA);
		Filme filme3 = new Filme("Java", Genero.COMEDIA);
		locacao.alugar(cliente3, filme1, "20/06/2022", "22:00");
		locacao1.alugar(cliente3, filme2, "20/06/2022", "22:00");
		locacao2.alugar(cliente3, filme3, "20/06/2022", "22:00");
		Transacao transacao = new Transacao();
		transacao.alugueis.add(locacao);
		transacao.alugueis.add(locacao1);
		transacao.alugueis.add(locacao2);
		
		
		assertEquals("O gênero mais alugado é Comédia!", transacao.retornaOGeneroMaisAlugado(transacao.alugueis));
	}

	
	//Teste Questão 4:
	@Test
	public void aluguelComDescontoCaseRomanceTest() {
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
		filme1.valorAluguel = 80;
		locacao.locacaoComDesconto(cliente3, filme1, "20/06/2022", "22:00");
		

		assertEquals(64 ,locacao.valorAluguel, 0.1);
	}
	
	@Test
	public void aluguelComDescontoCaseDramaTest() {
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.DRAMA);
		filme1.valorAluguel = 80;
		locacao.locacaoComDesconto(cliente3, filme1, "20/06/2022", "22:00");
		

		assertEquals(72 ,locacao.valorAluguel, 0.1);
	}
	
	
	@Test
	public void aluguelComDescontoCaseComediaTest() {
		Cliente cliente3 = new Cliente("João", 3, true);
		Locacao locacao = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.COMEDIA);
		filme1.valorAluguel = 80;
		String aux = locacao.locacaoComDesconto(cliente3, filme1, "20/06/2022", "22:00");
		

		assertEquals(68 ,locacao.valorAluguel, 0.1);
		assertEquals("Operação Realizada!" , aux);
	}
	
	
	@Test
	public void aluguelComDescontNaoRealizadoTest() {
		Cliente cliente3 = new Cliente("João", 3, false);
		Locacao locacao = new Locacao();
		Filme filme1 = new Filme("JavaScript", Genero.COMEDIA);
		filme1.valorAluguel = 80;
		
		assertEquals("Cliente Inativo. Operação não pode ser realizada!",locacao.locacaoComDesconto(cliente3, filme1, "20/06/2022", "22:00"));
	}
	
	
	
	//Teste Questão 5:
		@Test
		public void alugarFavoritosTest() {
			Cliente cliente3 = new Cliente("João", 3, true);
			Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
			Filme filme2 = new Filme("Csharp", Genero.ROMANCE);
			cliente3.filmesFavoritos.add(filme1);
			cliente3.filmesFavoritos.add(filme2);
			Locacao locacao = new Locacao();
			
			filme1.valorAluguel = 80;
			locacao.locacaoComDesconto(cliente3, cliente3.buscaFilmeFavorito(cliente3,"Csharp"), "20/06/2022", "22:00");
			Transacao transacao = new Transacao();
			transacao.alugueis.add(locacao);

			assertEquals("Csharp" , transacao.buscaCliente(3).buscaFilmeFavorito(cliente3,"Csharp").nome );
		}
		
		@Test
		public void alugarFavoritosNullTest() {
			Cliente cliente3 = new Cliente("João", 3, true);
			Filme filme1 = new Filme("JavaScript", Genero.ROMANCE);
			Filme filme2 = new Filme("Csharp", Genero.ROMANCE);
			cliente3.filmesFavoritos.add(filme2);
			Locacao locacao = new Locacao();
			
			filme1.valorAluguel = 80;
			locacao.alugar(cliente3, cliente3.buscaFilmeFavorito(cliente3, filme1.nome), "20/06/2022", "22:00");
			Transacao transacao = new Transacao();
			transacao.alugueis.add(locacao);

			assertEquals(null, transacao.buscaCliente(3).buscaFilmeFavorito(cliente3,filme1.nome) );
		}

}
