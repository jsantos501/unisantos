package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mock.MockDados;
import model.Contato;
import model.ListaCompras;
import model.Produto;
import model.Usuario;

public class ListaDAO {

	
	    private static final String SQL_INSERIR_LISTA_COMPRAS = "INSERT INTO unisantos.compras (nome, data, idUser) VALUES (?, ?, ?)";

	    private static final String SQL_INSERIR_PRODUTO_LISTA_COMPRAS = "INSERT INTO unisantos.compras_produtos (idCompra, idProduto) VALUES (?, ?)";
	    
	    


	    
	    private static final String SQL_LISTAR_COMPRAS = "SELECT compras_produtos.id, compras_produtos.idCompra, compras_produtos.idProduto, compras.nome as nomeCompras, compras.data as dataCompras	FROM unisantos.compras_produtos	join unisantos.compras on compras_produtos.idCompra = compras.id where compras.idUser = ?";
	    private static final String SQL_CONSULTAR_CONTATO = "select * from contatos where nome like ? order by nome";
	    private static final String SQL_EXCLUIR_CONTATO = "delete from contatos where id = ?";
	    private static final String SQL_ALTERAR_COMPRAS = "update contatos set nome=?, email=?, endereco=? where id=?";
	    private static final String SQL_ALTERAR_PRODUTO = "update contatos set nome=?, email=?, endereco=? where id=?";

	    private Connection connection;

	    public void cadastrar(ListaCompras compras) throws SQLException {
	        try {
	            connection = ConnectionFactory.getConnection();
	            try {
	                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_LISTA_COMPRAS);
//	    			return MockDados.criaNovaLista(compras);
	                stmt.setString(1, compras.getNomeListaCompras());
	                stmt.setString(2, compras.getDataListaCompras());
	                stmt.setString(3, compras.getIdUser());
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
//	    			return MockDados.addProdutoNaLista(compras, produto);
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

	    public ListaCompras alterarProduto(ListaCompras compras, Produto produto) throws SQLException {
//	        try {
//	            connection = ConnectionFactory.getConnection();
//	            try {
//	                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_PRODUTO);
	    			return MockDados.attProdutoNaLista(compras, produto);
//	                stmt.setString(1, compras.getNomeListaCompras());
//	                stmt.execute();
//	                stmt.close();
//	            } finally {
//	                connection.close();
//	            }
//	        } catch (SQLException ex) {
//	            throw ex;
//	        }
	    }

	    public ListaCompras alterarLista(ListaCompras compras) throws SQLException {
//	        try {
//	            connection = ConnectionFactory.getConnection();
//	            try {
//	                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_COMPRAS);
	    			return MockDados.attCompra(compras);
//	                stmt.setString(1, compras.getNomeListaCompras());
//	                stmt.execute();
//	                stmt.close();
//	            } finally {
//	                connection.close();
//	            }
//	        } catch (SQLException ex) {
//	            throw ex;
//	        }
	    }

	    public ListaCompras excluirProduto(ListaCompras compras, Produto produto) throws SQLException {
//	        try {
//	            connection = ConnectionFactory.getConnection();
//	            try {
//	                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_PRODUTO);
	    			return MockDados.removeProdutoNaLista(compras, produto);
//	                stmt.setString(1, compras.getNomeListaCompras());
//	                stmt.execute();
//	                stmt.close();
//	            } finally {
//	                connection.close();
//	            }
//	        } catch (SQLException ex) {
//	            throw ex;
//	        }
	    }
	    
	    public ListaCompras excluirLista(ListaCompras compras) throws SQLException {
//	        try {
//	            connection = ConnectionFactory.getConnection();
//	            try {
//	                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_COMPRAS);
	    			return MockDados.removeCompra(compras);
//	                stmt.setString(1, compras.getNomeListaCompras());
//	                stmt.execute();
//	                stmt.close();
//	            } finally {
//	                connection.close();
//	            }
//	        } catch (SQLException ex) {
//	            throw ex;
//	        }
	    }	    
	    
	    public List<ListaCompras> listar(Usuario usuario) throws SQLException {
	    	
	        List<ListaCompras> listasCompras = new ArrayList<ListaCompras>();
	        try {
	            connection = ConnectionFactory.getConnection();
	            try {
	                PreparedStatement stmt = connection.
	                        prepareStatement(SQL_LISTAR_COMPRAS);
//	                listasCompras = MockDados.getListarListasCompras()
	                
	                ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                	ListaCompras c = new ListaCompras();
	                	
	                    c.setIdListaCompras(String.valueOf(rs.getInt("id")));
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
	    
	    public List<Contato> consultar(String nome) throws SQLException {
	        List<Contato> contatos = new ArrayList<>();
	        try {
	            connection = ConnectionFactory.getConnection();
	            try {
	                PreparedStatement stmt = connection.
	                        prepareStatement(SQL_CONSULTAR_CONTATO);
	                stmt.setString(1, '%' + nome + '%');
	                ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                    Contato c = new Contato();
	                    c.setId(rs.getLong("id"));
	                    c.setNome(rs.getString("nome"));
	                    c.setEmail(rs.getString("email"));
	                    c.setEndereco(rs.getString("endereco"));
	                    contatos.add(c);
	                }
	                stmt.close();
	                rs.close();
	            } finally {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            throw e;
	        }
	        return contatos;
	    }
	    
	    public void excluir(Long id) throws SQLException {
	        try {
	            connection = ConnectionFactory.getConnection();
	            try {
	                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_CONTATO);
	                stmt.setLong(1, id);
	                stmt.execute();
	                stmt.close();
	            } finally {
	                connection.close();
	            }
	        } catch (SQLException ex) {
	            throw ex;
	        }
	    }

//	    public void alterar(Contato contato) throws SQLException {
//	        try {
//	            connection = ConnectionFactory.getConnection();
//	            try {
//	                System.out.println(contato);
//	                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_CONTATO);
//	                stmt.setString(1, contato.getNome());
//	                stmt.setString(2, contato.getEmail());
//	                stmt.setString(3, contato.getEndereco());
//	                stmt.setLong(4, contato.getId());
//	                stmt.execute();
//	                stmt.close();
//	            } finally {
//	                connection.close();
//	            }
//	        } catch (SQLException ex) {
//	            throw ex;
//	        }
//	    }
	    

}
