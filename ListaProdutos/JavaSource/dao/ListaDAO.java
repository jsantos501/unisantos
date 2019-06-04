package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ListaCompras;
import model.Produto;
import model.Usuario;

public class ListaDAO {

	private static final String SQL_INSERIR_LISTA_COMPRAS = "INSERT INTO unisantos.compras (nome, data, idUser) VALUES (?, ?, ?)";

	private static final String SQL_INSERIR_PRODUTO_LISTA_COMPRAS = "INSERT INTO unisantos.compras_produtos (idCompra, idProduto) VALUES (?, ?)";
	private static final String SQL_LISTAR_COMPRAS = "SELECT compras_produtos.id, compras_produtos.idCompra, compras_produtos.idProduto, compras.nome as nomeCompras, compras.data as dataCompras FROM unisantos.compras_produtos join unisantos.compras on compras_produtos.idCompra = compras.id where compras.idUser = ?";
	private static final String SQL_PRODUTOS_LISTA_SELECIONADA = "SELECT p.id, p.pego, p.nome, p.marca, p.quantidade, p.descricao, p.valorProduto, p.dataProduto, p.mercado FROM unisantos.compras_produtos as cp join unisantos.produto as p on cp.idProduto = p.id WHERE cp.idCompra = ?";

	private static final String SQL_CONSULTAR_LISTA_COMPRAS = "SELECT compras.id, compras.nome, compras.data, compras.idUser FROM unisantos.compras WHERE compras.nome = ? AND compras.data = ? AND compras.idUser = ?";

	private static final String SQL_EXCLUIR_PRODUTO_NA_LISTA = "DELETE FROM unisantos.compras_produtos WHERE idCompra = ? AND idProduto = ?";
	private static final String SQL_EXCLUIR_TODOS_PRODUTOS_LISTA = "DELETE FROM unisantos.compras_produtos WHERE idCompra = ?";
	private static final String SQL_EXCLUIR_LISTA_COMPLETA = "DELETE FROM unisantos.compras WHERE id = ?";

	private static final String SQL_ALTERAR_COMPRAS = "UPDATE unisantos.compras SET nome = ?, data = ? WHERE id = ?";

	private Connection connection;

	public void cadastrar(ListaCompras compras) throws SQLException {
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_LISTA_COMPRAS);
				stmt.setString(1, compras.getNomeListaCompras());
				stmt.setString(2, compras.getDataListaCompras());// new Date(Calendar.getInstance().getTimeInMillis()));
				stmt.setInt(3, Integer.parseInt(compras.getIdUser()));
				stmt.execute();
				stmt.close();
			} finally {
				connection.close();
			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void addProdutoNaLista(ListaCompras compras, Produto produto) throws SQLException {
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_PRODUTO_LISTA_COMPRAS);
				stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
				stmt.setInt(2, Integer.parseInt(produto.getId()));
				stmt.execute();
				stmt.close();
			} finally {
				connection.close();
			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public ListaCompras listaProdutos(ListaCompras compras) throws SQLException {

		compras.setListaProdutos(new ArrayList<Produto>());
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_PRODUTOS_LISTA_SELECIONADA);
				stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Produto p = new Produto();
					p.setId(String.valueOf(rs.getInt("id")));
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

		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_COMPRAS);
				stmt.setString(1, compras.getNomeListaCompras());
				stmt.setString(2, compras.getDataListaCompras());
				stmt.setInt(3, Integer.parseInt(compras.getIdListaCompras()));
				stmt.execute();
				stmt.close();
			} finally {
				connection.close();
			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void excluirProduto(ListaCompras compras, Produto produto) throws SQLException {
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_PRODUTO_NA_LISTA);
				stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
				stmt.setInt(2, Integer.parseInt(produto.getId()));
				stmt.execute();
				stmt.close();
			} finally {
				connection.close();
			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void excluirLista(ListaCompras compras) throws SQLException {
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_TODOS_PRODUTOS_LISTA);

				stmt.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
				stmt.execute();
				stmt.close();

				PreparedStatement stmt2 = connection.prepareStatement(SQL_EXCLUIR_LISTA_COMPLETA);
				stmt2.setInt(1, Integer.parseInt(compras.getIdListaCompras()));
				stmt2.execute();
				stmt2.close();

			} finally {
				connection.close();
			}
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public List<ListaCompras> listar(Usuario usuario) throws SQLException {

		List<ListaCompras> listasCompras = new ArrayList<ListaCompras>();
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_LISTAR_COMPRAS);
				stmt.setInt(1, Integer.parseInt(usuario.getId()));

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ListaCompras c = new ListaCompras();

					c.setIdListaCompras(String.valueOf(rs.getInt("idCompra")));
					c.setDataListaCompras(rs.getString("dataCompras"));
					c.setNomeListaCompras(rs.getString("nomeCompras"));
					listasCompras.add(c);
				}
				stmt.close();
				rs.close();
			} finally {
				connection.close();
			}
		} catch (SQLException e) {
			throw e;
		}
		return listasCompras;
	}

	public ListaCompras consultar(ListaCompras compras) throws SQLException {
		try {
			connection = ConnectionFactory.getConnection();
			try {
				PreparedStatement stmt = connection.prepareStatement(SQL_CONSULTAR_LISTA_COMPRAS);
				stmt.setString(1, compras.getNomeListaCompras());
				stmt.setString(2, compras.getDataListaCompras());
				stmt.setInt(3, Integer.parseInt(compras.getIdUser()));

				ResultSet rs = stmt.executeQuery();
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						compras.setIdListaCompras(String.valueOf(rs.getInt("id")));
						compras.setNomeListaCompras(String.valueOf(rs.getString("nome")));
						compras.setDataListaCompras(String.valueOf(rs.getString("data")));

					}
				} else {
					compras = null;
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

}
