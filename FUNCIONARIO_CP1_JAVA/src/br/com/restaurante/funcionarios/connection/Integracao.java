package br.com.restaurante.funcionarios.connection;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.restaurante.funcionarios.model.Cozinheiro;
import br.com.restaurante.funcionarios.model.FichaCadastral;
import br.com.restaurante.funcionarios.model.Funcionario;
import br.com.restaurante.funcionarios.model.Garcom;
import br.com.restaurante.funcionarios.model.Gerente;
import br.com.restaurante.funcionarios.IFuncionario;
import br.com.restaurante.funcionarios.connection.*;

public class Integracao {
	
	private static java.sql.Connection conn;
	
	public Integracao(Connection conn) {
		this.conn = conn;
	}
	
	public void salvarFuncionario(IFuncionario funcionario) {
		
		//CREATE-INSERT - INSERÇÃO DOS DADOS NA TABELA
		
	    int idPessoaGerado = -1;

	    String insertPessoa = "INSERT INTO pessoa (NOME, TELEFONE, EMAIL, CPF) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa, new String[] {"ID_PESSOA"})) {
	        stmtPessoa.setString(1, ((FichaCadastral) funcionario).getNome());
	        stmtPessoa.setString(2, ((FichaCadastral) funcionario).getTelefone());
	        stmtPessoa.setString(3, ((FichaCadastral) funcionario).getEmail());
	        stmtPessoa.setString(4, ((FichaCadastral) funcionario).getCpf());

	        stmtPessoa.executeUpdate();

	        ResultSet rs = stmtPessoa.getGeneratedKeys();
	        if (rs.next()) {
	            idPessoaGerado = rs.getInt(1);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.out.println("Erro ao inserir na tabela PESSOA: " + e.getMessage());
	        return;
	    }

	    if (idPessoaGerado != -1) {
	        String insertFuncionario = "INSERT INTO funcionario (CARGO, SALARIO, COMISSAO, TURNO, DATA_ADMISSAO, ID_PESSOA) VALUES (?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement stmtFunc = conn.prepareStatement(insertFuncionario)) {
	            stmtFunc.setString(1, ((Funcionario) funcionario).getCargo());
	            stmtFunc.setDouble(2, ((Funcionario) funcionario).getSalario());
	            stmtFunc.setDouble(3, ((Funcionario) funcionario).getComissao());
	            stmtFunc.setString(4, ((Funcionario) funcionario).getTurno());

	            java.sql.Date dataSQL = new java.sql.Date(((Funcionario) funcionario).getDataAdmissao().getTime());
	            stmtFunc.setDate(5, dataSQL);
	            stmtFunc.setInt(6, idPessoaGerado); // FK

	            stmtFunc.executeUpdate();
	            System.out.println("Funcionário criado com sucesso!");
	        } catch (SQLException e) {
	            System.out.println("Erro ao inserir na tabela FUNCIONARIO: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Erro: ID_PESSOA não foi gerado.");
	    }
	}
	    
	    //READ - SELECT - REALIZAR CONSULTA DE DADOS NAS TABELAS
	    
	public static List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        String query = "SELECT ID_FUNCIONARIO, ID_PESSOA, NOME, TELEFONE, EMAIL, CPF, " +
                       "CARGO, SALARIO, COMISSAO, TURNO, DATA_ADMISSAO " +
                       "FROM FUNCIONARIO, PESSOA " +
                       "WHERE FUNCIONARIO.ID_PESSOA = PESSOA.ID_PESSOA";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idPessoa = rs.getInt("ID_PESSOA");
                String nome = rs.getString("NOME");
                String telefone = rs.getString("TELEFONE");
                String email = rs.getString("EMAIL");
                String cpf = rs.getString("CPF");
                String cargo = rs.getString("CARGO");
                double salario = rs.getDouble("SALARIO");
                double comissao = rs.getDouble("COMISSAO");
                String turno = rs.getString("TURNO");
                Date dataAdmissao = rs.getDate("DATA_ADMISSAO");

                Funcionario funcionario = null;

                if (cargo.equalsIgnoreCase("Cozinheiro")) {
                    funcionario = new Cozinheiro(idPessoa, nome, cpf, telefone, email, cargo, salario, turno, dataAdmissao, comissao);
                } else if (cargo.equalsIgnoreCase("Garcom")) {
                    funcionario = new Garcom(idPessoa, nome, cpf, telefone, email, cargo, salario, turno, dataAdmissao, comissao, 0);
                } else if (cargo.equalsIgnoreCase("Gerente")) {
                    funcionario = new Gerente(idPessoa, nome, telefone, email, cpf, cargo, salario, turno, dataAdmissao, 0.0, "Administrativo");
                }

                if (funcionario != null) {
                    funcionarios.add(funcionario);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }

        return funcionarios;
    }
	    
	    
	    //UPDATE - ATUALIZA OS DADOS DA TABELA
	    
	public void atualizarFuncionario(Funcionario funcionario) {
	    String updateQuery = "UPDATE FUNCIONARIO SET CARGO = ?, SALARIO = ?, COMISSAO = ?, TURNO = ?, DATA_ADMISSAO = ? " +
	                         "WHERE ID_FUNCIONARIO = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
	        stmt.setString(1, funcionario.getCargo());
	        stmt.setDouble(2, funcionario.getSalario());
	        stmt.setDouble(3, funcionario.getComissao());
	        stmt.setString(4, funcionario.getTurno());
	        stmt.setDate(5, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
	        stmt.setInt(6, funcionario.getId()); // ID do funcionário

	        int linhasAfetadas = stmt.executeUpdate();

	        if (linhasAfetadas > 0) {
	            System.out.println("Funcionário atualizado com sucesso!");
	        } else {
	            System.out.println("Nenhum funcionário foi atualizado.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
	    }
	}

	    
	    //DELETE - DELETA OS DADOS DA TABELA
	
	public void deletarFuncionarioPorId(int idFuncionario) {
	    String deleteFuncionario = "DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
	    String deletePessoa = "DELETE FROM PESSOA WHERE ID_PESSOA = ?";

	    try {
	        // Primeiro, buscar o ID_PESSOA associado ao ID_FUNCIONARIO
	        String selectIdPessoa = "SELECT ID_PESSOA FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?";
	        int idPessoa = -1;

	        try (PreparedStatement stmtSelect = conn.prepareStatement(selectIdPessoa)) {
	            stmtSelect.setInt(1, idFuncionario);
	            ResultSet rs = stmtSelect.executeQuery();
	            if (rs.next()) {
	                idPessoa = rs.getInt("ID_PESSOA");
	            }
	        }

	        if (idPessoa == -1) {
	            System.out.println("Funcionário não encontrado!");
	            return;
	        }

	        // Deletar da tabela FUNCIONARIO
	        try (PreparedStatement stmtDelFunc = conn.prepareStatement(deleteFuncionario)) {
	            stmtDelFunc.setInt(1, idFuncionario);
	            stmtDelFunc.executeUpdate();
	        }

	        // Deletar da tabela PESSOA
	        try (PreparedStatement stmtDelPessoa = conn.prepareStatement(deletePessoa)) {
	            stmtDelPessoa.setInt(1, idPessoa);
	            stmtDelPessoa.executeUpdate();
	        }

	        System.out.println("Funcionário e dados pessoais excluídos com sucesso!");

	    } catch (SQLException e) {
	        System.out.println("Erro ao deletar funcionário: " + e.getMessage());
	    }
	}

	
}



	    
	    
	    
	    
	    

