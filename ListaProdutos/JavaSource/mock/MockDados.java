package mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ListaCompras;
import model.Produto;
import model.Usuario;

public class MockDados {
	private static List<ListaCompras> listasCompras = null;
	
	
	public static List<ListaCompras> getListarListasCompras(){
		if(listasCompras == null) {
			listasCompras = new ArrayList<ListaCompras>();
			listasCompras.add(getListaCompras1());
			listasCompras.add(getListaCompras2());
		}
		
		return listasCompras;
		
	}

	public static ListaCompras getListaCompras1(){
		ListaCompras compras = new ListaCompras();
		compras.setIdListaCompras("1");
		compras.setNomeListaCompras("Compras do Mes Maio");
		compras.setDataListaCompras("10/05/2019");
//		compras.setIdUser("1");
		compras.setListaProdutos(getListaProdutos1());
		compras.setQtdProduto(""+compras.getListaProdutos().size());
		return compras;		
	}

	public static ListaCompras getListaCompras2(){
		ListaCompras compras = new ListaCompras();
		compras.setIdListaCompras("2");
		compras.setNomeListaCompras("Compras do Mes Abril");
		compras.setDataListaCompras("10/04/2019");
//		compras.setIdUser("1");
		compras.setListaProdutos(getListaProdutos1());
		compras.setQtdProduto(""+compras.getListaProdutos().size());
		return compras;		
	}
	
	public static List<Produto> getListaProdutos1(){
		List<Produto> listas = new ArrayList<Produto>();
		listas.add(getProduto1());
		listas.add(getProduto2());
		listas.add(getProduto3());
		return listas;
		
	}

	
	public static Produto getProduto1(){
		Produto produto = new Produto();
//		produto.setId("1");
		produto.setPego("true");
		produto.setNome("carne moida");
		produto.setMarca("friboi");
		produto.setQuantidade("500");
		produto.setDescricao("carne moida de primeira qualidade friboi");
		produto.setValorProduto("R$ 5,00");
		produto.setDataProduto("10/05/2019");
		produto.setMercado(getMercado1());
		return produto;		
	}
	
	public static Produto getProduto2(){
		Produto produto = new Produto();
//		produto.setId("2");
		produto.setPego("false");
		produto.setNome("tempero de carne");
		produto.setMarca("maggi");
		produto.setQuantidade("100");
		produto.setDescricao("tempero maggi para dar um toque profissional ao seu prato");
		produto.setValorProduto("R$ 10,00");
		produto.setDataProduto("10/05/2019");
		produto.setMercado(getMercado1());
		return produto;		
	}
	
	public static Produto getProduto3(){
		Produto produto = new Produto();
//		produto.setId("3");
		produto.setPego("true");
		produto.setNome("cerveja pilsen");
		produto.setMarca("eisenbahn");
		produto.setQuantidade("350");
		produto.setDescricao("Cerveja Eisenbahn Pilsen Puro Malte 350Ml");
		produto.setValorProduto("R$ 2,99");
		produto.setDataProduto("10/05/2019");
		produto.setMercado(getMercado1());
		return produto;		
	}
	public static String getMercado1(){
		return "hipermercado Extra";
//		Mercado mercado = new Mercado();
//		mercado.setId("1");
//		mercado.setNome("hipermercado Extra");
//		mercado.setEndereco("ana costa, 500");
//		mercado.setDescricao("o melhor hipermercado 24h da região");
//		mercado.setCidade("santos");
//		mercado.setCep("11088-420");
//		return mercado;		
	}
	

	public static Usuario getUsuario(){
		Usuario user = new Usuario();
//		user.setId("1");
		user.setPerfil("admin");
		user.setSenha("******");
		user.setLogin("ale");
		user.setNome("alexandre jose");
		user.setCpf("32965055800");
		user.setEmail("ale@gmail.com");
		user.setCep("11088420");
		user.setCelular("11966931360");
		return user;		
	}

	public static ListaCompras criaNovaLista(ListaCompras lc){
		Random gerador = new Random();
		lc.setIdListaCompras(""+ gerador.nextInt(99));
		listasCompras.add(lc);
		return lc;
	}
	
	public static ListaCompras addProdutoNaLista(ListaCompras compras, Produto produto){

		Random gerador = new Random();
//		produto.setId(""+ gerador.nextInt(99));
		for(ListaCompras l:listasCompras) {
			if(compras.getIdListaCompras().equals(l.getIdListaCompras())) {
				l.getListaProdutos().add(produto);
				return l;
			}
		}
		return null;
	}
	
	public static ListaCompras attCompra(ListaCompras compras){

		for(ListaCompras l:listasCompras) {
			if(compras.getIdListaCompras().equals(l.getIdListaCompras())) {
				l.setDataListaCompras(compras.getDataListaCompras());
				l.setNomeListaCompras(compras.getNomeListaCompras());
				return l;
			}
		}
		return null;
	}	
	
	public static ListaCompras attProdutoNaLista(ListaCompras compras, Produto produto){
		for(ListaCompras l:listasCompras) {
			if(compras.getIdListaCompras().equals(l.getIdListaCompras())) {
				for(Produto p:l.getListaProdutos()) {
					if(p.getId().equals(produto.getId())) {
						p.setDataProduto(produto.getDataProduto());
						p.setDescricao(produto.getDescricao());
						p.setMarca(produto.getMarca());
						p.setMercado(produto.getMercado());
						p.setNome(produto.getNome());
						p.setQuantidade(produto.getQuantidade());
						p.setValorProduto(produto.getValorProduto());
						return l;
					}
				}
			}
		}	
		return null;
	}
	
	public static ListaCompras removeCompra(ListaCompras compras){

		for(ListaCompras l:listasCompras) {
			if(compras.getIdListaCompras().equals(l.getIdListaCompras())) {
				listasCompras.remove(l);
				return null;
			}
		}
		return compras;
	}		
	
	public static ListaCompras removeProdutoNaLista(ListaCompras compras, Produto produto){
		for(ListaCompras l:listasCompras) {
			if(compras.getIdListaCompras().equals(l.getIdListaCompras())) {
				for(Produto p:l.getListaProdutos()) {
					if(p.getId().equals(produto.getId())) {
						l.getListaProdutos().remove(p);
						return l;
					}
				}
			}
		}	
		return null;
	}
	
	
	
	public static List<Usuario> getListaUsuarios(){
		List<Usuario> listaUsers = new ArrayList<Usuario>();
		listaUsers.add(getUsuario());
		
		return listaUsers;
	}

	
	public static List<Usuario> addUsuarioNaLista(List<Usuario> lista, Usuario user){

		Random gerador = new Random();
//		user.setId(""+ gerador.nextInt(99));
		lista.add(user);
		
		return lista;
	}
	
	public static List<Usuario> removeProdutoNaLista(List<Usuario> lista, Usuario exUser){
		for(Usuario u:lista) {
			if(u.getId().equals(exUser.getId())) {
				lista.remove(u);
				return lista;
			}
		}
		return null;
	}
	
	public static List<Usuario> attUsuarioNaLista(List<Usuario> lista, Usuario user){
		for(Usuario u:lista) {
			if(u.getId().equals(user.getId())) {
				u.setCelular(user.getCelular());
				u.setCep(user.getCep());
				u.setCpf(user.getCpf());
				u.setEmail(user.getEmail());
				u.setLogin(user.getLogin());
				u.setNome(user.getNome());
				u.setPerfil(user.getPerfil());
				u.setSenha(user.getSenha());
				
				return lista;
			}
		}	
		return null;
	}
	
}

