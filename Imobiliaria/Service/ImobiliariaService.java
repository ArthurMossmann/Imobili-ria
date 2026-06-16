package Imobiliaria.Service;

import Imobiliaria.Model.*;
import Imobiliaria.Util.RendaInsuficienteException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImobiliariaService {
    private ArrayList<Imovel> listaImoveis;
    private ArrayList<Locatario> listaLocatarios;
    private ArrayList<Vendedor> listaVendedores; // Nova lista
    private ArrayList<Contrato> listaContratos;
    private ArrayList<String> historicoAuditoria;

    private int geradorIdContrato = 1;

    public ImobiliariaService() {
        this.listaImoveis = new ArrayList<>();
        this.listaLocatarios = new ArrayList<>();
        this.listaVendedores = new ArrayList<>();
        this.listaContratos = new ArrayList<>();
        this.historicoAuditoria = new ArrayList<>();
        gerarDadosPreCadastrados();
    }

    private void registrarLog(String message) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/06/2026 HH:mm:ss");
        historicoAuditoria.add("[" + agora.format(formatador) + "] - " + message);
    }

    private void gerarDadosPreCadastrados() {
        Endereco end1 = new Endereco("Av. Paulista", "1500", "Bela Vista", "01311-200");
        Endereco end2 = new Endereco("Rua Funchal", "418", "Vila Olímpia", "04551-060");

        listaImoveis.add(new ImovelResidencial("R-1", end1, 2000.0, 500.0, 120.0));
        listaImoveis.add(new ImovelComercial("C-2", end2, 4500.0, 350.0, 250.0));

        listaLocatarios.add(new Locatario("Rodrigo Amaral", "222.333.444-55", "91234-5678", "rodrigo@email.com", 9000.0));
        listaLocatarios.add(new Locatario("Fernanda Costa", "333.444.555-66", "98765-4321", "fernanda@email.com", 4500.0));

        // Dados de teste para os Vendedores exigidos
        listaVendedores.add(new Vendedor("Carlos Corretor", "111.222.333-44", "9999-8888", "carlos@imobiliaria.com", "CRECI-12345", 10.0));
        listaVendedores.add(new Vendedor("Ana Imóveis", "444.555.666-77", "9888-7777", "ana@imobiliaria.com", "CRECI-67890", 12.0));

        registrarLog("Ambiente inicializado com instâncias padrão e corretores credenciados.");
    }

    public void cadastrarLocatario(Locatario locatario) {
        listaLocatarios.add(locatario);
        registrarLog("Locatário registrado com sucesso: " + locatario.getNome());
    }

    public ArrayList<Imovel> getListaImoveis() { return listaImoveis; }
    public ArrayList<Contrato> getListaContratos() { return listaContratos; }
    public ArrayList<String> getHistoricoAuditoria() { return historicoAuditoria; }

    public Imovel buscarImovelPorCodigo(String codigo) {
        for (Imovel i : listaImoveis) {
            if (i.getCodigo().equalsIgnoreCase(codigo)) return i;
        }
        return null;
    }

    public Locatario buscarLocatarioPorCpf(String cpf) {
        for (Locatario l : listaLocatarios) {
            if (l.getCpf().equals(cpf)) return l;
        }
        return null;
    }

    public Contrato buscarContratoPorCpf(String cpf) {
        for (Contrato c : listaContratos) {
            if (c.getLocatario().getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public String emitirContratoLocacao(String codigoImovel, String cpfLocatario) {
        Imovel imovel = buscarImovelPorCodigo(codigoImovel);
        Locatario locatario = buscarLocatarioPorCpf(cpfLocatario);

        if (imovel == null) return "Erro: Imóvel não localizado.";
        if (locatario == null) return "Erro: Locatário não localizado.";
        if (!imovel.isDisponivel()) return "Erro: Imóvel já alugado.";

        double valorTotalFinal = (imovel instanceof Calculavel) ? ((Calculavel) imovel).calcularValorTotalAluguel() : imovel.getValorBaseAluguel();

        double limiteMinimoRenda = valorTotalFinal * 3;
        try {
            if (locatario.getRendaComprovada() < limiteMinimoRenda) {
                throw new RendaInsuficienteException("Renda insuficiente para critérios de risco.");
            }
        } catch (RendaInsuficienteException e) {
            registrarLog("CONTRATO BLOQUEADO: Renda de " + locatario.getNome() + " é menor do que 3x o aluguel.");
            return "Erro Crítico: " + e.getMessage();
        }

        imovel.setDisponivel(false);
        String idContratoGerado = "CONTRATO-" + geradorIdContrato++;

        // Pega automaticamente o primeiro corretor da lista para intermediar
        Vendedor vendedorResponsavel = listaVendedores.get(0);

        Contrato novoContrato = new Contrato(idContratoGerado, imovel, locatario, vendedorResponsavel, valorTotalFinal);
        listaContratos.add(novoContrato);

        registrarLog("Contrato " + idContratoGerado + " emitido por " + vendedorResponsavel.getNome());
        return "SUCESSO: " + idContratoGerado + " ativado via Corretor " + vendedorResponsavel.getNome();
    }

    public void exibirDashboardEstatistico() {
        int totalImoveis = listaImoveis.size();
        int imoveisLocados = 0;
        double faturamentoMensalTotal = 0;
        double totalComissoesPagas = 0;

        for (Imovel i : listaImoveis) {
            if (!i.isDisponivel()) imoveisLocados++;
        }
        for (Contrato c : listaContratos) {
            faturamentoMensalTotal += c.getValorFinalContrato();
            // Calcula quanto do contrato vai para o corretor com base na comissão dele
            totalComissoesPagas += c.getValorFinalContrato() * (c.getVendedorResponsavel().getPercentualComissao() / 100);
        }

        System.out.println("\n================= PAINEL DE INDICADORES (BI) =================");
        System.out.println("-> Volume de Imóveis sob Custódia: " + totalImoveis + " unidades.");
        System.out.println("-> Carteira de Contratos Ativos: " + listaContratos.size() + " locações.");
        System.out.println("-> Taxa de Ocupação Patrimonial: " + (totalImoveis > 0 ? (imoveisLocados * 100 / totalImoveis) : 0) + "%");
        System.out.println("-> Faturamento Bruto Mensal: R$ " + faturamentoMensalTotal);
        System.out.println("-> Total de Comissões Devidas aos Vendedores: R$ " + totalComissoesPagas);
        System.out.println("-> Lucro Líquido Retido pela Imobiliária: R$ " + (faturamentoMensalTotal - totalComissoesPagas));
        System.out.println("===============================================================");
    }
}