package Imobiliaria.Model;

/**
 * Classe abstrata base para todos os tipos de imóveis.
 * RN3: Valida se o imóvel está disponível antes de qualquer operação.
 */
public abstract class Imovel implements Calculavel {

    private String id;
    private String endereco;
    private double valorAluguel;
    private double valorCondominio;
    private double valorIptu;
    private boolean disponivel;

    public Imovel(String id, String endereco, double valorAluguel,
                  double valorCondominio, double valorIptu) {
        this.id = id;
        this.endereco = endereco;
        this.valorAluguel = valorAluguel;
        this.valorCondominio = valorCondominio;
        this.valorIptu = valorIptu;
        this.disponivel = true;
    }

    // RN1: Soma condomínio + IPTU ao valor do aluguel
    @Override
    public double calcularValorTotal() {
        return valorAluguel + valorCondominio + valorIptu;
    }

    // Método abstrato — cada subclasse define seu tipo
    public abstract String getTipo();

    public String getDetalhes() {
        return String.format(
                "ID: %s\n " +
                "Tipo: %s\n" +
                "Endereço: %s\n" +
                "Aluguel: R$ %.2f\n" +
                "Cond.: R$ %.2f\n" +
                "IPTU: R$ %.2f\n" +
                "Total mensal: R$ %.2f\n" +
                "Disponível: %s\n",
                id, getTipo(), endereco,
                valorAluguel, valorCondominio, valorIptu,
                calcularValorTotal(),
                disponivel ? "Sim" : "Não"
        );
    }

    // Getters e setters
    public String getId()                     { return id; }
    public String getEndereco()               { return endereco; }
    public double getValorAluguel()           { return valorAluguel; }
    public double getValorCondominio()        { return valorCondominio; }
    public double getValorIptu()              { return valorIptu; }
    public boolean isDisponivel()             { return disponivel; }
    public void setDisponivel(boolean d)      { this.disponivel = d; }
    public void setValorAluguel(double v)     { this.valorAluguel = v; }
    public void setValorCondominio(double v)  { this.valorCondominio = v; }
    public void setValorIptu(double v)        { this.valorIptu = v; }
}
