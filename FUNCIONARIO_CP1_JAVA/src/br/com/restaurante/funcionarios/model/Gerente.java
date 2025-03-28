package br.com.restaurante.funcionarios.model;

import java.util.Date;

import br.com.restaurante.funcionarios.IFuncionario;

public class Gerente extends Funcionario implements IFuncionario {
    private String departamento;

    public Gerente(int id,String nome, String telefone, String email, String cpf, String cargo,
                   double salario, String turno, Date dataAdmissao, double comissao, String departamento) {
        super(id, nome, telefone, email, cpf, cargo, salario, turno, dataAdmissao, comissao);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void supervisionar() {
        System.out.println(getNome() + " está supervisionando o departamento " + departamento + ".");
    }

    @Override
    public void calcularComissao() {
        System.out.println("Gerentes não recebem comissão.");
    }

    @Override
    public void calcularSalario() {
        double salarioBase = getSalario();
        double salarioTotal = salarioBase;
        
        System.out.println("Salário do Gerente: " + getNome());
        System.out.println("Salário base: " + salarioBase);
        System.out.println("Comissão: " + 0);
        System.out.println("Salário total: " + salarioTotal);
    }

    @Override
    public void calcularRescisao() {
        Date dataAdmissao = getDataAdmissao();
        double diasTrabalhados = (new Date().getTime() - dataAdmissao.getTime()) / (1000 * 60 * 60 * 24);
        double salarioBase = getSalario();
        double rescisao = salarioBase * diasTrabalhados;
        
        System.out.println("Rescisão do Gerente: " + getNome());
        System.out.println("Dias trabalhados: " + diasTrabalhados);
        System.out.println("Salário base: " + salarioBase);
        System.out.println("Rescisão total: " + rescisao);
    }
    
}
