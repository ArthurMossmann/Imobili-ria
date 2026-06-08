package Imobiliaria.Model;

public class Locatario {

    private String id;
    private String nome;
    private String cpf;
    private double rendaMensal;
    private String email;
    private String telefone;
    private boolean possuiHistoricoNegativo;

    public Locatario(String id, String nome, String cpf,
                     double rendaMensal, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rendaMensal = rendaMensal;
        this.email = email;
        this.telefone = telefone;
        this.possuiHistoricoNegativo = false;
    }

    public boolean validarRenda(double valorAluguel) {
        return rendaMensal >= (valorAluguel * 3);
    }

    public String getDetalhes() {
        return String.format(
                "ID: %s\n" +
                "Nome: %s\n" +
                "CPF: %s\n" +
                "Renda: R$ %.2f\n" +
                "E-mail: %s\n" +
                "Tel: %s\n" +
                "Histórico negativo: %s\n",
                id, nome, cpf, rendaMensal, email, telefone,
                possuiHistoricoNegativo ? "Sim ⚠" : "Não"
        );
    }

    public String getId()                         { return id; }
    public String getNome()                       { return nome; }
    public String getCpf()                        { return cpf; }
    public double getRendaMensal()                { return rendaMensal; }
    public String getEmail()                      { return email; }
    public String getTelefone()                   { return telefone; }
    public boolean isPossuiHistoricoNegativo()    { return possuiHistoricoNegativo; }
    public void setRendaMensal(double r)          { this.rendaMensal = r; }
    public void setEmail(String e)                { this.email = e; }
    public void setTelefone(String t)             { this.telefone = t; }
    public void setPossuiHistoricoNegativo(boolean h) { this.possuiHistoricoNegativo = h; }
}

