package connectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory conectar = new ConnectionFactory();
		Connection conexao = conectar.recuperaConexao();
		System.out.println("Fechando Conexão");
		conexao.close();
	}
}
