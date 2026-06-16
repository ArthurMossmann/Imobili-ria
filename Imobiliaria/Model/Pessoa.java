package Imobiliaria.Model;

public abstract class Pessoa {
    // Atributos protegidos para que as classes filhas possam acessar diretamente
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String email;

    public Pessoa(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public abstract String getDescricaoTipo();

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}