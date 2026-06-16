package Imobiliaria;

import Imobiliaria.Model.*;
import Imobiliaria.Service.ImobiliariaService;
import Imobiliaria.Util.Leitor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ImobiliariaService imobiliariaService = new ImobiliariaService();
        Scanner leitor = new Scanner(System.in);
        int opcaoMenu = 0;

        do {
            // DESIGN DO MENU: Interface Gráfica Adaptada para Terminal (CLI Premium)
            System.out.println("\n\u001B[36m┌────────────────────────────────────────────────────────────────────────┐\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m      \u001B[1;33mSISTEMA INTEGRADO DE GESTÃO IMOBILIÁRIA\u001B[0m | \u001B[1;32mENTERPRISE v2.5\u001B[0m       \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m├────────────────────────────────────────────────────────────────────────┤\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[1]\u001B[0m Registrar Novo Locatário Proponente                               \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[2]\u001B[0m Consultar Portfólio de Imóveis e Taxas (RFO 1)                     \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[3]\u001B[0m Emitir Contrato de Locação (RFO 2 e 3)                             \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[4]\u001B[0m Simular Rescisão Contratual por CPF (RFO 4)                        \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[5]\u001B[0m Aplicar Atualização por IGP-M via CPF (RFO 5)                     \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[6]\u001B[0m Painel Estatístico e BI Empresarial (Diretoria)                  \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;34m[7]\u001B[0m Exibir Relatório de Auditoria (Logs de Segurança)                  \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m│\u001B[0m  \u001B[1;31m[8]\u001B[0m Encerrar Sessão do Operador                                      \u001B[36m│\u001B[0m");
            System.out.println("\u001B[36m└────────────────────────────────────────────────────────────────────────┘\u001B[0m");
            System.out.print("\u001B[1;35m➔ Selecione a operação (ou '0' para cancelar/voltar): \u001B[0m");

            try {
                opcaoMenu = Integer.parseInt(leitor.nextLine());
            } catch (NumberFormatException e) {
                Leitor.exibirMensagemErro("Entrada inválida! Digite apenas o número correspondente à operação.");
                continue;
            }

            switch (opcaoMenu) {
                case 1:
                    System.out.println("\n\u001B[34m▶ MÓDULO DE CADASTRO DE CLIENTE\u001B[0m (Digite 'sair' a qualquer momento para abortar)");
                    System.out.print("Nome Completo do Proponente: ");
                    String nome = leitor.nextLine();
                    if (nome.equalsIgnoreCase("sair") || nome.equals("0")) { Leitor.exibirMensagemErro("Operação abortada."); break; }

                    System.out.print("CPF (apenas números): ");
                    String cpf = leitor.nextLine();
                    if (cpf.equalsIgnoreCase("sair") || cpf.equals("0")) { Leitor.exibirMensagemErro("Operação abortada."); break; }

                    System.out.print("Telefone de Contato: ");
                    String tel = leitor.nextLine();
                    if (tel.equalsIgnoreCase("sair") || tel.equals("0")) { Leitor.exibirMensagemErro("Operação abortada."); break; }

                    System.out.print("E-mail Corporativo: ");
                    String email = leitor.nextLine();
                    if (email.equalsIgnoreCase("sair") || email.equals("0")) { Leitor.exibirMensagemErro("Operação abortada."); break; }

                    double renda = 0;
                    System.out.print("Renda Mensal Comprovada (R$): ");
                    String rendaInput = leitor.nextLine();
                    if (rendaInput.equalsIgnoreCase("sair") || rendaInput.equals("0")) { Leitor.exibirMensagemErro("Operação abortada."); break; }
                    try {
                        renda = Double.parseDouble(rendaInput);
                    } catch (NumberFormatException e) {
                        Leitor.exibirMensagemErro("Erro: Renda inválida. Operação cancelada.");
                        break;
                    }

                    imobiliariaService.cadastrarLocatario(new Locatario(nome, cpf, tel, email, renda));
                    Leitor.exibirMensagemSucesso("Locatário registrado com sucesso no banco de dados.");
                    break;

                case 2:
                    System.out.println("\n\u001B[34m▶ PORTFÓLIO DE IMÓVEIS DISPONÍVEIS\u001B[0m");
                    for (Imovel i : imobiliariaService.getListaImoveis()) {
                        double custoTotal = (i instanceof Calculavel) ? ((Calculavel) i).calcularValorTotalAluguel() : i.getValorBaseAluguel();
                        System.out.println("  ───────────────────────────────────────────────────────────────");
                        System.out.println("  Ref: " + i.getCodigo() + " | Tipo: " + i.getDescricaoTipo());
                        System.out.println("  Local: " + i.getEndereco());
                        System.out.println("  Base: R$ " + i.getValorBaseAluguel() + " | Final (c/ Taxas): R$ " + custoTotal);
                        System.out.println("  Status: " + (i.isDisponivel() ? "\u001B[32mDISPONÍVEL\u001B[0m" : "\u001B[31mALUGADO\u001B[0m"));
                    }
                    System.out.println("  ───────────────────────────────────────────────────────────────");

                    System.out.print("\nPressione ENTER para retornar ao menu principal...");
                    leitor.nextLine();
                    break;

                case 3:
                    System.out.println("\n\u001B[34m▶ MÓDULO DE EMISSÃO DE CONTRATOS\u001B[0m (Digite 'sair' para cancelar)");
                    System.out.print("Insira o Código de Referência do Imóvel: ");
                    String codImovel = leitor.nextLine();
                    if (codImovel.equalsIgnoreCase("sair") || codImovel.equals("0")) { Leitor.exibirMensagemErro("Operação cancelada."); break; }

                    System.out.print("Insira o CPF do Cliente Proponente: ");
                    String cpfCliente = leitor.nextLine();
                    if (cpfCliente.equalsIgnoreCase("sair") || cpfCliente.equals("0")) { Leitor.exibirMensagemErro("Operação cancelada."); break; }

                    String resultadoEmissao = imobiliariaService.emitirContratoLocacao(codImovel, cpfCliente);
                    if (resultadoEmissao.startsWith("SUCESSO")) {
                        Leitor.exibirMensagemSucesso(resultadoEmissao);
                    } else {
                        Leitor.exibirMensagemErro(resultadoEmissao);
                    }
                    break;

                case 4:
                    System.out.println("\n\u001B[34m▶ DEPARTAMENTO JURÍDICO - SIMULAÇÃO DE DISTRATO\u001B[0m");
                    System.out.print("Informe o CPF do cliente para rescindir o contrato: ");
                    String cpfRescisao = leitor.nextLine();
                    if (cpfRescisao.equalsIgnoreCase("sair") || cpfRescisao.equals("0")) { Leitor.exibirMensagemErro("Operação cancelada."); break; }

                    Contrato contratoAlvoRescisao = imobiliariaService.buscarContratoPorCpf(cpfRescisao);

                    if (contratoAlvoRescisao != null) {
                        double multa = contratoAlvoRescisao.calcularMultaRescisao();
                        Leitor.exibirMensagemSucesso("Cláusula de rescisão ativada com sucesso!");
                        System.out.println("-> ID do Contrato rescindido: " + contratoAlvoRescisao.getIdContrato());
                        System.out.println("-> Titular do Vínculo: " + contratoAlvoRescisao.getLocatario().getNome());
                        System.out.println("-> Penalidade Aplicada (10% RFO 4): R$ " + multa);
                    } else {
                        Leitor.exibirMensagemErro("Nenhum contrato ativo foi localizado para o CPF informado.");
                    }
                    break;

                case 5:
                    System.out.println("\n\u001B[34m▶ AJUSTE FINANCEIRO - ATUALIZAÇÃO INFLACIONÁRIA ANUAL\u001B[0m");
                    System.out.print("Informe o CPF do cliente para aplicar o reajuste: ");
                    String cpfReajuste = leitor.nextLine();
                    if (cpfReajuste.equalsIgnoreCase("sair") || cpfReajuste.equals("0")) { Leitor.exibirMensagemErro("Operação cancelada."); break; }

                    Contrato contratoAlvoReajuste = imobiliariaService.buscarContratoPorCpf(cpfReajuste);

                    if (contratoAlvoReajuste != null) {
                        System.out.print("Informe o índice acumulado do IGP-M (%): ");
                        String taxaInput = leitor.nextLine();
                        if (taxaInput.equalsIgnoreCase("sair") || taxaInput.equals("0")) { Leitor.exibirMensagemErro("Operação cancelada."); break; }

                        double taxaPercentual = 0;
                        try {
                            taxaPercentual = Double.parseDouble(taxaInput);
                        } catch (NumberFormatException e) {
                            Leitor.exibirMensagemErro("Erro: Taxa inválida. Atualização abortada.");
                            break;
                        }

                        double novoValorContrato = contratoAlvoReajuste.aplicarReajusteIgpm(taxaPercentual);
                        Leitor.exibirMensagemSucesso("Aditivo de reajuste por IGP-M (RFO 5) processado!");
                        System.out.println("-> Contrato Vinculado: " + contratoAlvoReajuste.getIdContrato());
                        System.out.println("-> Novo valor mensal recalculado: R$ " + novoValorContrato);
                    } else {
                        Leitor.exibirMensagemErro("Nenhum contrato ativo foi localizado para o CPF informado.");
                    }
                    break;

                case 6:
                    imobiliariaService.exibirDashboardEstatistico();
                    System.out.print("\nPressione ENTER para retornar ao menu principal...");
                    leitor.nextLine();
                    break;

                case 7:
                    System.out.println("\n\u001B[35m▶ TRILHA DE AUDITORIA DE SEGURANÇA (LOGS)\u001B[0m");
                    for (String log : imobiliariaService.getHistoricoAuditoria()) {
                        System.out.println("  " + log);
                    }
                    System.out.print("\nPressione ENTER para retornar ao menu principal...");
                    leitor.nextLine();
                    break;

                case 8:
                    System.out.println("\n\u001B[33mFinalizando auditoria interna e encerrando sessão de forma segura. Até logo!\u001B[0m");
                    break;

                default:
                    Leitor.exibirMensagemErro("Operação desconhecida. Selecione uma opção válida da tabela.");
            }
        } while (opcaoMenu != 8);

        leitor.close();
    }
}