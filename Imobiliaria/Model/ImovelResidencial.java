package Imobiliaria.Model;

public class ImovelResidencial extends Imovel implements Calculavel {
    private double taxaCondominio;
    private double valorIptu;
    private int quantidadeQuartos;
    private boolean possuiVagaGaragem;

    public ImovelResidencial(String codigo, Endereco endereco, double valorBaseAluguel, double taxaCondominio, double valorIptu) {
        super(codigo, endereco, valorBaseAluguel); // Envia para o construtor de Imovel
        this.taxaCondominio = taxaCondominio;
        this.valorIptu = valorIptu;
        this.quantidadeQuartos = 2; // Valor padrão
        this.possuiVagaGaragem = true; // Valor padrão
    }

    @Override
    public String getDescricaoTipo() {
        return "Residencial";
    }

    @Override
    public double calcularValorTotalAluguel() {
        // Regra: Somar taxa de condomínio e IPTU ao valor do aluguel
        return this.valorBaseAluguel + this.taxaCondominio + this.valorIptu;
    }
}