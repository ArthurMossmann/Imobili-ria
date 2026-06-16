package Imobiliaria.Service;

import Imobiliaria.Model.*;
import Imobiliaria.User.Locatario;
import Imobiliaria.User.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImobiliariaService {

    private ArrayList<Imovel> imoveis = new ArrayList<>();
    private ArrayList<Locatario> locatarios = new ArrayList<>();
    private ArrayList<Contrato> contratos = new ArrayList<>();
    private ArrayList<Pessoa> pessoa = new ArrayList<>();

    // IMÓVEIS

    public void adicionarImovel(Imovel imovel) {
        imoveis.add(imovel);
        System.out.println("✔ Imóvel [" + imovel.getId() + "] cadastrado com sucesso.");
    }

    public boolean removerImovel(String id) {
        Imovel i = buscarImovelPorId(id);
        if (i == null) { System.out.println("✘ Imóvel não encontrado."); return false; }
        imoveis.remove(i);
        System.out.println("✔ Imóvel removido.");
        return true;
    }

    public void listarImoveis() {
        if (imoveis.isEmpty()) { System.out.println("Nenhum imóvel cadastrado."); return; }
        System.out.println("\n════════ IMÓVEIS ════════");
        for (Imovel i : imoveis) {
            System.out.println(i.getDetalhes());
            System.out.println("─────────────────────────");
        }
    }

    public void listarImoveisDisponiveis() {
        boolean achou = false;
        System.out.println("\n════════ DISPONÍVEIS ════════");
        for (Imovel i : imoveis) {
            if (i.isDisponivel()) {
                System.out.println(i.getDetalhes());
                System.out.println("─────────────────────────");
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum imóvel disponível.");
    }

    public Imovel buscarImovelPorId(String id) {
        for (Imovel i : imoveis) {
            if (i.getId().equalsIgnoreCase(id)) return i;
        }
        return null;
    }

    public void atualizarValoresImovel(String id, double aluguel, double cond, double iptu) {
        Imovel i = buscarImovelPorId(id);
        if (i == null) { System.out.println("✘ Imóvel não encontrado."); return; }
        i.setValorAluguel(aluguel);
        i.setValorCondominio(cond);
        i.setValorIptu(iptu);
        System.out.printf("✔ Valores atualizados. Novo total: R$ %.2f%n", i.calcularValorTotal());
    }

    // ─────────────────────────────────────────
    // LOCATÁRIOS
    // ─────────────────────────────────────────

    public void adicionarPessoa(Pessoa pes) {
        pessoa.add(pes);
        System.out.println("✔ Pessoa [" + pes.getNome() + "] cadastrado com sucesso.");
    }

    public Pessoa buscarPessoaPorCPF(String cpf) {
        for (Pessoa p : Pessoa) {
            if (p.getCPF().equalsIgnoreCase(cpf)) return p;
        }
        return null;
    }

    public boolean removerPessoa(String CPF) {
        pessoa p = buscarLocatarioPorId()
    }

    public void adicionarLocatario(Locatario loc) {
        locatarios.add(loc);
        System.out.println("✔ Locatário [" + loc.getId() + "] cadastrado com sucesso.");
    }

    public boolean removerLocatario(String id) {
        Locatario l = buscarLocatarioPorId(id);
        if (l == null) { System.out.println("✘ Locatário não encontrado."); return false; }
        locatarios.remove(l);
        System.out.println("✔ Locatário removido.");
        return true;
    }

    public void listarLocatarios() {
        if (locatarios.isEmpty()) { System.out.println("Nenhum locatário cadastrado."); return; }
        System.out.println("\n════════ LOCATÁRIOS ════════");
        for (Locatario l : locatarios) {
            System.out.println(l.getDetalhes());
            System.out.println("─────────────────────────");
        }
    }

    public Locatario buscarLocatarioPorId(String id) {
        for (Locatario l : locatarios) {
            if (l.getId().equalsIgnoreCase(id)) return l;
        }
        return null;
    }

    public void atualizarLocatario(String id, double novaRenda, String email, String tel) {
        Locatario l = buscarLocatarioPorId(id);
        if (l == null) { System.out.println("✘ Locatário não encontrado."); return; }
        l.setRendaMensal(novaRenda);
        l.setEmail(email);
        l.setTelefone(tel);
        System.out.println("✔ Locatário atualizado.");
    }

    public boolean criarContrato(String idContrato, String idImovel,
                                 String idLocatario, LocalDate inicio,
                                 LocalDate fim, double indiceReajuste) {

        Imovel imovel = buscarImovelPorId(idImovel);
        Locatario locatario = buscarLocatarioPorId(idLocatario);

        if (imovel == null)    { System.out.println("✘ Imóvel não encontrado."); return false; }
        if (locatario == null) { System.out.println("✘ Locatário não encontrado."); return false; }

        if (!imovel.isDisponivel()) {
            System.out.println("✘ [RN3] Imóvel indisponível para locação.");
            return false;
        }

        if (!locatario.validarRenda(imovel.getValorAluguel())) {
            System.out.printf("✘ [RN2] Renda insuficiente. Mínimo exigido: R$ %.2f (3x aluguel).%n",
                    imovel.getValorAluguel() * 3);
            return false;
        }

        Contrato c = new Contrato(idContrato, imovel, locatario, inicio, fim, indiceReajuste);
        contratos.add(c);
        imovel.setDisponivel(false);

        System.out.printf("✔ Contrato criado! Valor total mensal: R$ %.2f%n", imovel.calcularValorTotal());
        return true;
    }

    public void rescindirContrato(String idContrato) {
        Contrato c = buscarContratoPorId(idContrato);
        if (c == null) { System.out.println("✘ Contrato não encontrado."); return; }
        if (!c.isAtivo()) { System.out.println("✘ Contrato já está encerrado."); return; }

        double multa = c.calcularMulta();
        c.encerrarContrato();
        System.out.printf("✔ [RN4] Contrato rescindido. Multa aplicada: R$ %.2f%n", multa);
    }

    public void aplicarReajuste(String idContrato, double novoIndice) {
        Contrato c = buscarContratoPorId(idContrato);
        if (c == null) { System.out.println("✘ Contrato não encontrado."); return; }
        if (!c.isAtivo()) { System.out.println("✘ Contrato encerrado."); return; }

        c.setIndiceReajuste(novoIndice);
        System.out.printf("✔ [RN5] Reajuste de %.1f%% aplicado. Novo valor: R$ %.2f%n",
                novoIndice * 100, c.calcularValorReajustado());
    }

    public void listarContratos() {
        if (contratos.isEmpty()) { System.out.println("Nenhum contrato cadastrado."); return; }
        System.out.println("\n════════ CONTRATOS ════════");
        for (Contrato c : contratos) {
            System.out.println(c.getDetalhes());
            System.out.println("─────────────────────────");
        }
    }

    public Contrato buscarContratoPorId(String id) {
        for (Contrato c : contratos) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    // ─────────────────────────────────────────
    // DADOS PRÉ-CADASTRADOS (para demo)
    // ─────────────────────────────────────────

    public void carregarDadosDemo() {

        Locatario lucas = new Locatario(
                "L001",
                "Lucas",
                19,
                "9",
                "Rua da Pamonha",
                10000000000.0,
                "lucas@gmail.com",
                "956784001"
        );

        Locatario arthur = new Locatario(
                "L002",
                "Arthur",
                19,
                "123456",
                "Mora na rua",
                100000000.0,
                "arthurzin@gmail.com",
                "9638494"
        );

        locatarios.add(lucas);
        locatarios.add(arthur);
    }

    public ArrayList<Imovel> getImoveis()       { return imoveis; }
    public ArrayList<Locatario> getLocatarios() { return locatarios; }
    public ArrayList<Contrato> getContratos()   { return contratos; }
    public ArrayList<Pessoa> getPessoa() {return pessoa; }
}
