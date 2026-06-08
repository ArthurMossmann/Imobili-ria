package Imobiliaria.Model;

public class ImovelComercial extends Imovel {

    private double areaM2;
    private String tipoComercio;
    private boolean temEstacionamento;
    private int numSalas;

    public ImovelComercial(String id, String endereco, double valorAluguel,
                           double valorCondominio, double valorIptu,
                           double areaM2, String tipoComercio,
                           boolean temEstacionamento, int numSalas) {
        super(id, endereco, valorAluguel, valorCondominio, valorIptu);
        this.areaM2 = areaM2;
        this.tipoComercio = tipoComercio;
        this.temEstacionamento = temEstacionamento;
        this.numSalas = numSalas;
    }

    @Override
    public String getTipo() {
        return "Comercial";
    }

    @Override
    public String getDetalhes() {
        return super.getDetalhes() + String.format(
                "\n  Área: %.1f m²\n" +
                "Tipo comércio: %s\n" +
                "Estacionamento: %s\n" +
                "Salas: %d\n",
                areaM2, tipoComercio, temEstacionamento ? "Sim" : "Não", numSalas
        );
    }

    public double getAreaM2()             { return areaM2; }
    public String getTipoComercio()       { return tipoComercio; }
    public boolean isTemEstacionamento()  { return temEstacionamento; }
    public int getNumSalas()              { return numSalas; }
    public void setTipoComercio(String t) { this.tipoComercio = t; }
    public void setNumSalas(int n)        { this.numSalas = n; }
}
