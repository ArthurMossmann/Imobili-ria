package Imobiliaria;
import Imobiliaria.Model.*;
import Imobiliaria.Service.ImobiliariaService;
import Imobiliaria.Util.Leitor;

import java.time.LocalDate;

public class Main {

    private static final ImobiliariaService service = new ImobiliariaService();

    public static void main(String[] args) {
        service.carregarDadosDemo();

        int opcao;
        do {
            exibirMenu();
            opcao = Leitor.inteiro("Opção: ");
            System.out.println();
            switch (opcao) {
                case 1  -> menuImoveis();
                case 2  -> menuLocatarios();
                case 3  -> menuContratos();
                case 0  -> System.out.println("Encerrando sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      SISTEMA IMOBILIÁRIA     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Gerenciar Imóveis        ║");
        System.out.println("║  2. Gerenciar Locatários     ║");
        System.out.println("║  3. Gerenciar Contratos      ║");
        System.out.println("║  0. Sair                     ║");
        System.out.println("╚══════════════════════════════╝");
    }

    private static void menuImoveis() {
        System.out.println("\n── IMÓVEIS ──");
        System.out.println("1. Listar todos");
        System.out.println("2. Listar disponíveis");
        System.out.println("3. Cadastrar residencial");
        System.out.println("4. Cadastrar comercial");
        System.out.println("5. Atualizar valores");
        System.out.println("6. Remover imóvel");
        int op = Leitor.inteiro("Opção: ");
        System.out.println();
        switch (op) {
            case 1 -> service.listarImoveis();
            case 2 -> service.listarImoveisDisponiveis();
            case 3 -> cadastrarResidencial();
            case 4 -> cadastrarComercial();
            case 5 -> atualizarImovel();
            case 6 -> service.removerImovel(Leitor.texto("ID do imóvel: "));
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void menuLocatarios() {
        System.out.println("\n── LOCATÁRIOS ──");
        System.out.println("1. Listar todos");
        System.out.println("2. Cadastrar locatário");
        System.out.println("3. Atualizar dados");
        System.out.println("4. Remover locatário");
        int op = Leitor.inteiro("Opção: ");
        System.out.println();
        switch (op) {
            case 1 -> service.listarLocatarios();
            case 2 -> cadastrarLocatario();
            case 3 -> atualizarLocatario();
            case 4 -> service.removerLocatario(Leitor.texto("ID do locatário: "));
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void menuContratos() {
        System.out.println("\n── CONTRATOS ──");
        System.out.println("1. Listar contratos");
        System.out.println("2. Criar contrato");
        System.out.println("3. Rescindir contrato (multa 10%)");
        System.out.println("4. Aplicar reajuste anual");
        int op = Leitor.inteiro("Opção: ");
        System.out.println();
        switch (op) {
            case 1 -> service.listarContratos();
            case 2 -> criarContrato();
            case 3 -> service.rescindirContrato(Leitor.texto("ID do contrato: "));
            case 4 -> aplicarReajuste();
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarResidencial() {
        System.out.println("─ Cadastro: Imóvel Residencial ─");
        String id         = Leitor.texto("ID: ");
        String endereco   = Leitor.texto("Endereço: ");
        double aluguel    = Leitor.decimal("Valor aluguel R$: ");
        double cond       = Leitor.decimal("Condomínio R$: ");
        double iptu       = Leitor.decimal("IPTU R$: ");
        int quartos       = Leitor.inteiro("Nº quartos: ");
        boolean garagem   = Leitor.simNao("Tem garagem?");
        String bairro     = Leitor.texto("Bairro: ");
        double area       = Leitor.decimal("Área m²: ");

        service.adicionarImovel(new ImovelResidencial(
                id, endereco, aluguel, cond, iptu,
                quartos, garagem, bairro, area
        ));
    }

    private static void cadastrarComercial() {
        System.out.println("─ Cadastro: Imóvel Comercial ─");
        String id          = Leitor.texto("ID: ");
        String endereco    = Leitor.texto("Endereço: ");
        double aluguel     = Leitor.decimal("Valor aluguel R$: ");
        double cond        = Leitor.decimal("Condomínio R$: ");
        double iptu        = Leitor.decimal("IPTU R$: ");
        double area        = Leitor.decimal("Área m²: ");
        String tipoComercio = Leitor.texto("Tipo de comércio: ");
        boolean estac      = Leitor.simNao("Tem estacionamento?");
        int salas          = Leitor.inteiro("Nº de salas: ");

        service.adicionarImovel(new ImovelComercial(
                id, endereco, aluguel, cond, iptu,
                area, tipoComercio, estac, salas
        ));
    }

    private static void atualizarImovel() {
        String id      = Leitor.texto("ID do imóvel: ");
        double aluguel = Leitor.decimal("Novo aluguel R$: ");
        double cond    = Leitor.decimal("Novo condomínio R$: ");
        double iptu    = Leitor.decimal("Novo IPTU R$: ");
        service.atualizarValoresImovel(id, aluguel, cond, iptu);
    }

    private static void cadastrarLocatario() {
        System.out.println("─ Cadastro: Locatário ─");
        String id      = Leitor.texto("ID: ");
        String nome    = Leitor.texto("Nome completo: ");
        String cpf     = Leitor.texto("CPF: ");
        double renda   = Leitor.decimal("Renda mensal R$: ");
        String email   = Leitor.texto("E-mail: ");
        String tel     = Leitor.texto("Telefone: ");

        service.adicionarLocatario(new Locatario(id, nome, cpf, renda, email, tel));
    }

    private static void atualizarLocatario() {
        String id    = Leitor.texto("ID do locatário: ");
        double renda = Leitor.decimal("Nova renda R$: ");
        String email = Leitor.texto("Novo e-mail: ");
        String tel   = Leitor.texto("Novo telefone: ");
        service.atualizarLocatario(id, renda, email, tel);
    }

    private static void criarContrato() {
        System.out.println("─ Novo Contrato ─");
        String idContrato  = Leitor.texto("ID do contrato: ");
        String idImovel    = Leitor.texto("ID do imóvel: ");
        String idLocatario = Leitor.texto("ID do locatário: ");

        System.out.println("Data de início (formato AAAA-MM-DD):");
        LocalDate inicio   = LocalDate.parse(Leitor.texto("  Início: "));
        LocalDate fim      = LocalDate.parse(Leitor.texto("  Fim:    "));
        double reajuste    = Leitor.decimal("Índice reajuste anual (ex: 0.05 para 5%): ");

        service.criarContrato(idContrato, idImovel, idLocatario, inicio, fim, reajuste);
    }

    private static void aplicarReajuste() {
        String id     = Leitor.texto("ID do contrato: ");
        double indice = Leitor.decimal("Novo índice (ex: 0.05 = 5%): ");
        service.aplicarReajuste(id, indice);
    }
}
