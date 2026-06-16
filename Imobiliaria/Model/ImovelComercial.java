package Imobiliaria.Model;

public class ImovelComercial extends Imovel implements Calculavel {
    private double taxaSegurancaEletronica;
    private double impostoAlvara;
    private int quantidadeSalas;
    private boolean possuiAcessibilidade;

    public ImovelComercial(String codigo, Endereco endereco, double valorBaseAluguel, double taxaSegurancaEletronica, double impostoAlvara) {
        super(codigo, endereco, valorBaseAluguel); // Envia para o construtor de Imovel
        this.taxaSegurancaEletronica = taxaSegurancaEletronica;
        this.impostoAlvara = impostoAlvara;
        this.quantidadeSalas = 4; // Valor padrão
        this.possuiAcessibilidade = true; // Valor padrão
    }

    @Override
    public String getDescricaoTipo() {
        return "Comercial";
    }

    @Override
    public double calcularValorTotalAluguel() {
        // Regra para Comercial: Aluguel base + taxa de segurança + alvará
        return this.valorBaseAluguel + this.taxaSegurancaEletronica + this.impostoAlvara;
    }
}