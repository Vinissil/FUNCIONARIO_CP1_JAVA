package br.com.restaurante.funcionarios.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.restaurante.funcionarios.IFuncionario;
import br.com.restaurante.funcionarios.connection.ConnectionSQL;
import br.com.restaurante.funcionarios.connection.Integracao;
import br.com.restaurante.funcionarios.model.Cozinheiro;
import br.com.restaurante.funcionarios.model.Funcionario;
import br.com.restaurante.funcionarios.model.Garcom;
import br.com.restaurante.funcionarios.model.Gerente;

public class Main {

	public static void main(String[] args) throws SQLException {

		IFuncionario cozinheiro = new Cozinheiro(1, "John Doe", "5511999999999", "john-doe@email.com", "99999999999", "Sous-chef", 6000.00, "manhã", new Date(), 0.15);
		IFuncionario garcom = new Garcom(1, "Jane Smith", "5511988888888", "jane-smith@email.com", "88888888888", "Atendente", 3000.00, "tarde", new Date(), 0.10, 5);
		IFuncionario gerente = new Gerente(1, "Alice Johnson", "5511977777777", "alice-johnson@email.com", "77777777777", "Gerente Geral", 10000.00, "integral", new Date(), 0.20, "Administração");

		System.out.println("\n-------------------------------------------------");
		cozinheiro.calcularComissao();
		cozinheiro.calcularSalario();
		cozinheiro.calcularRescisao();

		System.out.println("\n-------------------------------------------------");
		garcom.calcularComissao();
		garcom.calcularSalario();
		garcom.calcularRescisao();

		System.out.println("\n-------------------------------------------------");
		gerente.calcularComissao();
		gerente.calcularSalario();
		gerente.calcularRescisao();

		System.out.println("-------------------------------------------------");
	
		//INSERT
		
		try {
			Connection conn = ConnectionSQL.getConnection();		
			
			Integracao integracao = new Integracao(conn);
			
			IFuncionario cozinheiro1 = new Cozinheiro(1, "John Doe", "5511999999999", "john-doe@email.com", "99999999999", "Sous-chef", 6000.00, "manhã", new Date(), 0.15);
			
			IFuncionario garcom1 = new Garcom(1, "Jane Smith", "5511988888888", "jane-smith@email.com", "88888888888", "Atendente", 3000.00, "tarde", new Date(), 0.10, 5);
			
			IFuncionario gerente1 = new Gerente(1, "Alice Johnson", "5511977777777", "alice-johnson@email.com", "77777777777", "Gerente Geral", 10000.00, "integral", new Date(), 0.20, "Administração");
			
			integracao.salvarFuncionario(cozinheiro1);
			integracao.salvarFuncionario(garcom1);
			integracao.salvarFuncionario(gerente1);
			
			
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
		}
		
		//SELECT
		
		List<Funcionario> lista = Integracao.listarFuncionarios();

		for (Funcionario f : lista) {
		    System.out.println("Nome: " + f.getNome());
		}
		
		//UPDATE
		
		Funcionario func = new Garcom(3, "Nome", "9999-9999", "email@email.com", "123.456.789-00",
                "Garçom", 3000.0, "Noturno", new Date(), 0.15, 5);

		Integracao integracao = new Integracao(ConnectionSQL.getConnection());
		integracao.atualizarFuncionario(func);
		
		//DELETE
		
		Integracao integracao1 = new Integracao(ConnectionSQL.getConnection());
		integracao1.deletarFuncionarioPorId(2); // Substitua pelo ID do funcionário
	}

}
