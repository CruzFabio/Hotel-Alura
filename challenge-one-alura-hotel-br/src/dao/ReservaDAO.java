package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import model.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, formaPagamento) VALUES (?, ?, ?, ?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setDate(1, reserva.getDataEntrada());
				pstm.setDate(2, reserva.getDataSaida());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());
				pstm.executeUpdate();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Reserva> listar() {
		try {
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));
						reservas.add(reserva);
					}
				}
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	
	public List<Reserva> buscarId(Integer id) {
		try {
			List<Reserva> reservas = new ArrayList<Reserva>();
			String sql = "SELECT * FROM reservas WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));
						reservas.add(reserva);
					}
				}
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void editar(Integer id, Date dataE, Date dataS) {
		try (PreparedStatement stm = connection.prepareStatement("UPDATE reservas R SET R.DATA_ENTRADA = ?, R.DATA_SAIDA = ? WHERE id = ?")) {
			stm.setDate(1, dataE);
            stm.setDate(2, dataS);
            stm.setInt(3, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
	
	
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
