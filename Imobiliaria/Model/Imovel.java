package Imobiliaria.Model;

public abstract class Imovel {
    protected String codigo;
    protected Endereco endereco; // Usando a nossa classe especializada de Endereço
    protected double valorBaseAluguel;
    protected boolean disponivel; // Atributo crucial para a Regra de Negócio Obrigatória (RFO) 3

    // Construtor do Imóvel - Todo imóvel novo começa como DISPONÍVEL (true)
    public Imovel(String codigo, Endereco endereco, double valorBaseAluguel) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.valorBaseAluguel = valorBaseAluguel;
        this.disponivel = true;
    }

    // MÉTODO ABSTRATO: Cada classe filha (Residencial/Comercial) implementará o seu próprio retorno
    public abstract String getDescricaoTipo();

    public String getCodigo() {
        return codigo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getValorBaseAluguel() {
        return valorBaseAluguel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}