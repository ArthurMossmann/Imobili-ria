package Imobiliaria.Model;

public class Locatario extends Pessoa {
    private double rendaComprovada; // Crucial para a RFO 2 (bloqueio se renda < 3x o aluguer)
    private String dataCadastro;
    private boolean possuiFiador;
    private String statusSocioeconomico;

    public Locatario(String nome, String cpf, String telefone, String email, double rendaComprovada) {
        super(nome, cpf, telefone, email); // Passa os dados para o construtor de Pessoa
        this.rendaComprovada = rendaComprovada;
        this.dataCadastro = "16/06/2026"; // Valores padrão para inicializar os atributos obrigatórios
        this.possuiFiador = true;
        this.statusSocioeconomico = "Regular";
    }

    public double getRendaComprovada() {
        return rendaComprovada;
    }
}