package Imobiliaria.Model;

public class Contrato {
    private String idContrato;
    private Imovel imovel;
    private Locatario locatario;
    private Vendedor vendedorResponsavel; // Vinculado ao contrato
    private double valorFinalContrato;

    public Contrato(String idContrato, Imovel imovel, Locatario locatario, Vendedor vendedorResponsavel, double valorFinalContrato) {
        this.idContrato = idContrato;
        this.imovel = imovel;
        this.locatario = locatario;
        this.vendedorResponsavel = vendedorResponsavel;
        this.valorFinalContrato = valorFinalContrato;
    }

    public double calcularMultaRescisao() {
        return this.valorFinalContrato * 0.10; // RFO 4: 10% de multa
    }

    public double aplicarReajusteIgpm(double taxaPercentual) {
        // RFO 5: Reajuste anual por taxa informada
        this.valorFinalContrato += (this.valorFinalContrato * (taxaPercentual / 100));
        return this.valorFinalContrato;
    }

    // Getters e Setters
    public String getIdContrato() { return idContrato; }
    public Imovel getImovel() { return imovel; }
    public Locatario getLocatario() { return locatario; }
    public Vendedor getVendedorResponsavel() { return vendedorResponsavel; }
    public double getValorFinalContrato() { return valorFinalContrato; }
}