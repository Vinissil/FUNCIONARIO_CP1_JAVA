package br.com.restaurante.funcionarios.model;

import java.util.Date;

public abstract class Funcionario extends FichaCadastral {
    private String cargo;
    private double salario;
    private double comissao;
    private String turno;
    private Date dataAdmissao;

    public Funcionario(int id, String nome, String telefone, String email, String cpf, String cargo,
            double salario, String turno, Date dataAdmissao, double comissao) {
        super(id, nome, telefone, email, cpf);
        this.cargo = cargo;
        this.salario = salario;
        this.turno = turno;
        this.dataAdmissao = dataAdmissao;
        this.comissao = comissao;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

}