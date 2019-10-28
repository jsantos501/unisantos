package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import connection.JPAEntityManager;
import constantes.Constantes;
import model.ListaCompras;
import model.Produto;
import model.Usuario;

public class ListaDAO {

	private static final String SQL_INSERIR_LISTA_COMPRAS = "INSERT INTO unisantos.compras (nome, data, idUser) VALUES (?, ?, ?)";

	private static final String SQL_INSERIR_PRODUTO_LISTA_COMPRAS = "INSERT INTO unisantos.compras_produtos (idCompra, idProduto) VALUES (?, ?)";
	private static final String SQL_LISTAR_COMPRAS = "SELECT id as idCompra, compras.nome as nomeCompras, compras.data as dataCompras FROM unisantos.compras where compras.idUser = ?";
	private static final String SQL_PRODUTOS_LISTA_SELECIONADA = "SELECT p.id, p.pego, p.nome, p.marca, p.quantidade, p.descricao, p.valorProduto, p.dataProduto, p.mercado FROM unisantos.compras_produtos as cp join unisantos.produto as p on cp.idProduto = p.id WHERE cp.idCompra = ?";

	private static final String SQL_CONSULTAR_LISTA_COMPRAS = "SELECT compras.id, compras.nome, compras.data, compras.idUser FROM unisantos.compras WHERE compras.nome = ? AND compras.data = ? AND compras.idUser = ?";

	private static final String SQL_EXCLUIR_PRODUTO_NA_LISTA = "DELETE FROM unisantos.compras_produtos WHERE idCompra = ? AND idProduto = ?";
	private static final String SQL_EXCLUIR_TODOS_PRODUTOS_LISTA = "DELETE FROM unisantos.compras_produtos WHERE idCompra = ?";
	private static final String SQL_EXCLUIR_LISTA_COMPLETA = "DELETE FROM unisantos.compras WHERE id = ?";

	private static final String SQL_ALTERAR_COMPRAS = "UPDATE unisantos.compras SET nome = ?, data = ? WHERE id = ?";

	private Connection connection;

	public void cadastrar(ListaCompras compras) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(compras);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
		
	}

	public void addProdutoNaLista(ListaCompras compras) throws SQLException {
 
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(compras);

		manager.flush();
		manager.getTransaction().commit();
		manager.close();
	}

	public ListaCompras listaProdutos(ListaCompras compras) throws SQLException {

		compras.setListaProdutos(new ArrayList<Produto>());
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_PRODUTOS_LISTA_SELECIONADA);
				stmt.setInt(1, compras.getId().intValue());

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Produto p = new Produto();
//					p.setId(String.valueOf(rs.getInt("id")));
					p.setPego(String.valueOf(rs.getInt("pego")));
					p.setNome(String.valueOf(rs.getString("nome")));
					p.setMarca(String.valueOf(rs.getString("marca")));
					p.setQuantidade(String.valueOf(rs.getInt("quantidade")));
					p.setDescricao(String.valueOf(rs.getString("descricao")));
					p.setValorProduto(String.valueOf(rs.getDouble("valorProduto")));
					p.setDataProduto(String.valueOf(rs.getDate("dataProduto")));
					p.setMercado(String.valueOf(rs.getString("mercado")));

					compras.getListaProdutos().add(p);
				}
				stmt.close();
				rs.close();
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			throw e;
		}
		return compras;
	}

	public void alterarLista(ListaCompras compras) throws SQLException {
		
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(compras);
		manager.getTransaction().commit();
		manager.close();
//		try {
//			connection = ConnectionFactory.getConnection();
//			try {
//				PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_COMPRAS);
//				stmt.setString(1, compras.getNomeListaCompras());
//				stmt.setString(2, compras.getDataListaCompras());
//				stmt.setInt(3, Integer.parseInt(compras.getIdListaCompras()));
//				stmt.execute();
//				stmt.close();
//			} finally {
//				connection.close();
//			}
//		} catch (SQLException ex) {
//			throw ex;
//		}
	}

	public void excluirProduto(ListaCompras compras) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(compras);
		manager.getTransaction().commit();
		manager.close();
		
//		
//		try {
//			connection = ConnectionFactory.getConnection();
//			try {
//				PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_PRODUTO_NA_LISTA);
//				stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
////				stmt.setInt(2, Integer.parseInt(produto.getId()));
//				stmt.execute();
//				stmt.close();
//			} finally {
//				connection.close();
//			}
//		} catch (SQLException ex) {
//			throw ex;
//		}
	}

	public void excluirLista(ListaCompras compras) throws SQLException {
		EntityManager manager = JPAEntityManager.getEntityManager();
		manager.getTransaction().begin();
		manager.remove(compras);
		manager.getTransaction().commit();
		manager.close();
		
//	try {
//			connection = ConnectionFactory.getConnection();
//			try {
//				PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_TODOS_PRODUTOS_LISTA);
//
//				stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
//				stmt.execute();
//				stmt.close();
//
//				PreparedStatement stmt2 = connection.prepareStatement(SQL_EXCLUIR_LISTA_COMPLETA);
//				stmt2.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
//				stmt2.execute();
//				stmt2.close();
//
//			} finally {
//				connection.close();
//			}
//		} catch (SQLException ex) {
//			throw ex;
//		}
	}

	public List<ListaCompras> listar(Usuario usuario) throws SQLException {

		List<ListaCompras> lu = null;

		EntityManager manager = JPAEntityManager.getEntityManager();
		Query query = manager.createNamedQuery(Constantes.LISTA_COMPRAS_LISTA_COMPRAS_POR_USUARIO, ListaCompras.class);
		
		query.setParameter("pLogin", usuario.getLogin());	
		
		try {
			lu = query.getResultList();
		}catch (NoResultException e) {
			lu = null;
		}finally {
			manager.close();
		}
	
		return lu;
		
		
		
	}

	public ListaCompras consultar(Long idCompras) {
		
		EntityManager manager = JPAEntityManager.getEntityManager();
		ListaCompras registro = manager.find(ListaCompras.class, idCompras);
		manager.close();
		
		return registro;
	}

}
