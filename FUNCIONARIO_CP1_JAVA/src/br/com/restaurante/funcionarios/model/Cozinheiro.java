package br.com.restaurante.funcionarios.model;

import java.util.Date;
import java.util.Map;

import br.com.restaurante.funcionarios.IFuncionario;
import br.com.restaurante.funcionarios.exception.PedidoInvalidoException;

public class Cozinheiro extends Funcionario implements IFuncionario {
    
    private Map<String, String> pedidosAtivos;

    public Cozinheiro(int id, String nome, String telefone, String email, String cpf, String cargo,
            double salario, String turno, Date dataAdmissao, double comissao) {
        super(id, nome, telefone, email, cpf, cargo, salario, turno, dataAdmissao, comissao);
    }
    
    @Override
    public void calcularComissao() {
        double diasTrabalhadosPorMes = 30;
        double comissaoPorDia = (getSalario() / diasTrabalhadosPorMes) * getComissao();
        double comissaoMensal = comissaoPorDia * diasTrabalhadosPorMes;
        double comissaoDiaria = (getSalario() / diasTrabalhadosPorMes) * getComissao();
        double comissaoSemanal = (getSalario() / 7) * getComissao();
        
        System.out.println("Comissão do Cozinheiro: " + getNome());
        System.out.println("Comissão mensal: " + comissaoMensal);
        System.out.println("Comissão diária: " + comissaoDiaria);
        System.out.println("Comissão semanal: " + comissaoSemanal);
        System.out.println("Comissão total: " + (comissaoMensal + comissaoDiaria + comissaoSemanal));
    }

    @Override
    public void calcularSalario() {
        double salarioBase = getSalario();
        double comissao = getComissao();
        double salarioTotal = salarioBase + (salarioBase * comissao);
        
        System.out.println("Salário do Cozinheiro: " + getNome());
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
        
        System.out.println("Rescisão do Cozinheiro: " + getNome());
        System.out.println("Dias trabalhados: " + diasTrabalhados);
        System.out.println("Salário base: " + salarioBase);
        System.out.println("Rescisão total: " + rescisao);
        System.out.println("Rescisão total com comissão: " + (rescisao + (salarioBase * getComissao())));
    }

    public String getPedidoAtivoStatus(String pedido) throws PedidoInvalidoException {
        try {
            return pedidosAtivos.get(pedido);
        } catch (NullPointerException e) {
            throw new PedidoInvalidoException("O pedido: " + pedido + " não existe ou não está ativo.");
        }
    }

    public String getPedidosAtivos() {
        return pedidosAtivos.toString();
    }

    public void setPedidosAtivos(Map<String, String> pedidosAtivos) throws IllegalArgumentException {
        if (pedidosAtivos == null || pedidosAtivos.isEmpty()) {
            throw new IllegalArgumentException("A lista de pedidos ativos não pode ser nula ou vazia.");
        }
        for (String pedido : pedidosAtivos.keySet()) {
            if (pedido == null || pedido.isEmpty()) {
                throw new IllegalArgumentException("O pedido não pode ser nulo ou vazio.");
            }
        }
        for (String status : pedidosAtivos.values()) {
            if (status == null || status.isEmpty()) {
                throw new IllegalArgumentException("O status do pedido não pode ser nulo ou vazio.");
            }
        }
        if (pedidosAtivos.size() > 10) {
            throw new IllegalArgumentException("A lista de pedidos ativos não pode ter mais de 10 itens.");
        }
        this.pedidosAtivos = pedidosAtivos;
    }

    public void prepararPrato() {
        System.out.println(getNome() + " (Cozinheiro) está preparando o prato.");
    }
    
    public void organizarCozinha() {
        System.out.println(getNome() + " (Cozinheiro) está organizando a cozinha.");
    }
    
    public void limparAreaTrabalho() {
        System.out.println(getNome() + " (Cozinheiro) está limpando a área de trabalho.");
    }
    
}
