package Imobiliaria.Model;

public class Vendedor extends Pessoa {
    private String creci;
    private double percentualComissao;

    public Vendedor(String nome, String cpf, String telefone, String email, String creci, double percentualComissao) {
        super(nome, cpf, telefone, email);
        this.creci = creci;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public String getDescricaoTipo() {
        return "Corretor Associado (CRECI: " + this.creci + ")";
    }

    public String getCreci() { return creci; }
    public double getPercentualComissao() { return percentualComissao; }
}