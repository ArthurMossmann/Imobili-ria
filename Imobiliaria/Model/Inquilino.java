package Imobiliaria.Model;

public class Inquilino extends Pessoa {
    private String dataMudanca;
    private double valorCaucaoPago;
    private int quantidadeMoradores;
    private boolean possuiAnimaisEstimacao;

    public Inquilino(String nome, String cpf, String telefone, String email, double valorCaucaoPago) {
        super(nome, cpf, telefone, email); // Envia para o construtor de Pessoa
        this.dataMudanca = "A definir";
        this.valorCaucaoPago = valorCaucaoPago;
        this.quantidadeMoradores = 1;
        this.possuiAnimaisEstimacao = false;
    }

    public double getValorCaucaoPago() {
        return valorCaucaoPago;
    }
}