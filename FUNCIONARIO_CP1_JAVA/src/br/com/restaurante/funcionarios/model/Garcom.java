package br.com.restaurante.funcionarios.model;

import java.util.Date;

import br.com.restaurante.funcionarios.IFuncionario;

public class Garcom extends Funcionario implements IFuncionario {
    private int mesasResponsaveis;

    public Garcom(int id,String nome, String telefone, String email, String cpf, String cargo,
                  double salario, String turno, Date dataAdmissao, double comissao, int mesasResponsaveis) {
        super(id, nome, telefone, email, cpf, cargo, salario, turno, dataAdmissao, comissao);
        this.mesasResponsaveis = mesasResponsaveis;
    }

    public int getMesasResponsaveis() {
        return mesasResponsaveis;
    }

    public void setMesasResponsaveis(int mesasResponsaveis) {
        this.mesasResponsaveis = mesasResponsaveis;
    }

    public void atenderMesa(int numeroMesa) {
        System.out.println(getNome() + " está atendendo a mesa " + numeroMesa + ".");
    }

    public void entregarPedido(int numeroMesa) {
        System.out.println(getNome() + " está entregando o pedido para a mesa " + numeroMesa + ".");
    }

    @Override
    public void calcularComissao() {
        double diasTrabalhadosPorMes = 30;
        
        double comissaoDiaria = (getSalario() / diasTrabalhadosPorMes) * getComissao() * mesasResponsaveis;
        double comissaoSemanal = comissaoDiaria * (diasTrabalhadosPorMes / 4);
        double comissaoMensal = comissaoDiaria * diasTrabalhadosPorMes;
        
        System.out.println("Comissão do Garçom: " + getNome());
        System.out.println("Comissão diária: " + comissaoDiaria);
        System.out.println("Comissão semanal: " + comissaoSemanal);
        System.out.println("Comissão mensal: " + comissaoMensal);
    }

    @Override
    public void calcularSalario() {        
        double salarioBase = getSalario();
        double comissao = getComissao();

        double salarioTotal = salarioBase + (salarioBase * comissao * mesasResponsaveis);
        System.out.println("Salário do Garçom: " + getNome());
        System.out.println("Salário base: " + salarioBase);
        System.out.println("Comissão: " + comissao);
        System.out.println("Salário total: " + salarioTotal);
    }

    @Override
    public void calcularRescisao() {
        Date dataAdmissao = getDataAdmissao();
        double diasTrabalhados = (new Date().getTime() - dataAdmissao.getTime()) / (1000 * 60 * 60 * 24);
        double salarioBase = getSalario();
        double rescisao = salarioBase * diasTrabalhados;
        
        System.out.println("Rescisão do Garçom: " + getNome());
        System.out.println("Dias trabalhados: " + diasTrabalhados);
        System.out.println("Salário base: " + salarioBase);
        System.out.println("Rescisão total: " + rescisao);
        System.out.println("Rescisão total com comissão: " + (rescisao + (salarioBase * getComissao() * mesasResponsaveis)));
    }
}