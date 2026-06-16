package Imobiliaria.User;

public class Locatario extends Pessoa {

    private String id;
    private boolean possuiHistoricoNegativo;

    public Locatario() {
    }

    public Locatario(String id, String nome, int idade, String CPF, String endereco, double rendaMensal, String email, String telefone) {

        super(nome, idade, CPF, endereco, rendaMensal, email, telefone);

        this.id = id;
        this.possuiHistoricoNegativo = false;
    }

    public boolean validarRenda(double valorAluguel) {
        return getRendaMensal() >= valorAluguel * 3;
    }

    public String getDetalhes() {
        return String.format(
                "ID: %s\n" +
                        "Nome: %s\n" +
                        "CPF: %s\n" +
                        "Renda: R$ %.2f\n" +
                        "E-mail: %s\n" +
                        "Telefone: %s\n" +
                        "Histórico negativo: %s\n",
                id,
                getNome(),
                getCPF(),
                getRendaMensal(),
                getEmail(),
                getTelefone(),
                possuiHistoricoNegativo ? "Sim" : "Não"
        );
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public boolean isPossuiHistoricoNegativo() {
        return possuiHistoricoNegativo;
    }
    public void setPossuiHistoricoNegativo(boolean possuiHistoricoNegativo) {
        this.possuiHistoricoNegativo = possuiHistoricoNegativo;
    }
}