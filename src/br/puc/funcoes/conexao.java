package br.puc.funcoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexao {
	public static String status = "";

	/**
	 * TODO
	 * @function	obterConexao - faz a conexao com o banco de dados
	 * @return		conexao com o banco
	 */
	public static Connection obterConexao() {
		
		String user = "root";
		String password = "";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();	//driver do banco
			String url = "jdbc:mysql://localhost/posgraduacaorecommander";			//localização da tabela/tabela
			con = DriverManager.getConnection(url, user, password);		
			
			status = "Conexão aberta!\n";
			System.out.print(status);

		} catch (SQLException e) {
			status = e.getMessage();
			System.out.println(status);
		} catch (ClassNotFoundException e) {
			status = e.getMessage();
		} catch (Exception e) {
			status = e.getMessage();
		}

		return con;
	}
}
