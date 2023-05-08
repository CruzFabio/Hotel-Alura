package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import connectionFactory.ConnectionFactory;
import dao.HospedeDAO;
import model.Hospede;

public class HospedeController {
	
	private HospedeDAO hospedeDAO;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().recuperaConexao();
		this.hospedeDAO = new HospedeDAO(connection);
	}
	
	public void salvar(Hospede hospede) {
		this.hospedeDAO.salvar(hospede);
	}
	
	public List<Hospede> listar(){
		return this.hospedeDAO.listar();
	}
	
	public List<Hospede> buscarSobrenome(String sobrenome){
		return this.hospedeDAO.buscarSobrenome(sobrenome);
	}
	
	public void editar(Integer id, String nome, String sobrenome, Date dataNacimento, String nacionalidade, String telefone) {
		this.hospedeDAO.editar(id, nome, sobrenome, dataNacimento, nacionalidade, telefone);
	}
	
	public void deletar(Integer id) {
		this.hospedeDAO.deletar(id);
	}
	
	public void deletarIdReserva(Integer idReserva) {
		this.hospedeDAO.deletarIdReserva(idReserva);
	}
	
	
}
