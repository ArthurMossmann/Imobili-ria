package Imobiliaria.Model;

public class ImovelResidencial extends Imovel {

    private int numQuartos;
    private boolean temGaragem;
    private String bairro;
    private double areaM2;

    public ImovelResidencial(String id, String endereco, double valorAluguel,
                             double valorCondominio, double valorIptu,
                             int numQuartos, boolean temGaragem,
                             String bairro, double areaM2) {
        super(id, endereco, valorAluguel, valorCondominio, valorIptu);
        this.numQuartos = numQuartos;
        this.temGaragem = temGaragem;
        this.bairro = bairro;
        this.areaM2 = areaM2;
    }

    @Override
    public String getTipo() {
        return "Residencial";
    }

    @Override
    public String getDetalhes() {
        return super.getDetalhes() + String.format(
                "\n  Quartos: %d | Garagem: %s | Bairro: %s | Área: %.1f m²",
                numQuartos, temGaragem ? "Sim" : "Não", bairro, areaM2
        );
    }

    public int getNumQuartos()     { return numQuartos; }
    public boolean isTemGaragem()  { return temGaragem; }
    public String getBairro()      { return bairro; }
    public double getAreaM2()      { return areaM2; }
    public void setNumQuartos(int n)       { this.numQuartos = n; }
    public void setTemGaragem(boolean t)   { this.temGaragem = t; }
}

