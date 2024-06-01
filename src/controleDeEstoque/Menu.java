package controleDeEstoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static List<Setor> setores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("\n===== Controle de Estoque =====");
            System.out.println("1. Cadastrar Setor");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Listar Produtos");
            System.out.println("4. Buscar Produto");
            System.out.println("5. Editar Produto");
            System.out.println("6. Excluir Produto");
            System.out.println("7. Inventário do Setor");
            System.out.println("8. Depreciar Estoque");
            System.out.println("9. Depreciar Setor");
            System.out.println("10. Sair");
            System.out.print("Escolha uma opção: ");

            String opcaoStr = scanner.nextLine();
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (NumberFormatException e) {
                System.out.println("Erro: A opção deve ser um número inteiro. Por favor, tente novamente.");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarSetor();
                    break;
                case 2:
                    cadastrarProduto();
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    buscarProduto();
                    break;
                case 5:
                    editarProduto();
                    break;
                case 6:
                    excluirProduto();
                    break;
                case 7:
                    inventarioSetor();
                    break;
                case 8:
                    depreciarEstoque();
                    break;
                case 9:
                    depreciarSetor();
                    break;
                case 10:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 10);
    }

    private static void cadastrarProduto() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        int codigo;
        while (true) {
            System.out.print("Código: ");
            String codigoStr = scanner.nextLine();
            try {
                codigo = Integer.parseInt(codigoStr);
                if (codigoJaExiste(codigo)) {
                    System.out.println("Erro: O código fornecido já está em uso. Por favor, escolha outro código.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: O código deve ser um número inteiro. Por favor, tente novamente.");
            }
        }

        System.out.print("Quantidade em Estoque: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Produto perecível? (S/N): ");
        boolean perecivel = scanner.nextLine().equalsIgnoreCase("S");

        if (perecivel) {
            System.out.print("Data de validade (AAAA-MM-DD): ");
            LocalDate dataValidade = LocalDate.parse(scanner.nextLine());

            System.out.print("Refrigerado? (S/N): ");
            boolean refrigerado = scanner.nextLine().equalsIgnoreCase("S");

            setor.adicionarProduto(new ProdutoPerecivel(nome, codigo, quantidade, preco, descricao, dataValidade, refrigerado));
        } else {
            setor.adicionarProduto(new Produto(nome, codigo, quantidade, preco, descricao));
        }

        System.out.println("Produto cadastrado com sucesso!");
    }

    private static boolean codigoJaExiste(int codigo) {
        for (Setor setor : setores) {
            if (setor.buscarProduto(codigo) != null) {
                return true;
            }
        }
        return false;
    }

    private static void listarProdutos() {
        System.out.println("\n===== Lista de Produtos =====");
        for (Setor setor : setores) {
            System.out.println("Setor: " + setor.getNome());
            System.out.printf("%-20s | %-10s | %-10s | %-10s | %-30s | %-10s | %-15s\n", "Nome", "Código", "Quantidade", "Preço", "Descrição", "Validade", "Refrigerado");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            setor.listarProdutosOrdenados();
            System.out.println();
        }
    }

    private static void buscarProduto() {
        System.out.print("Código do produto: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                exibirDetalhesProduto(produto);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private static void exibirDetalhesProduto(Produto produto) {
        System.out.println("Produto encontrado:");
        System.out.println("Código: " + produto.getCodigo());
        System.out.println("Nome: " + produto.getNome());
        System.out.println("Quantidade em Estoque: " + produto.getQuantidadeEstoque());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("Descrição: " + produto.getDescricao());
        if (produto instanceof ProdutoPerecivel) {
            ProdutoPerecivel perecivel = (ProdutoPerecivel) produto;
            System.out.println("Data de Validade: " + perecivel.getDataValidade());
            System.out.println("Refrigerado: " + (perecivel.isRefrigerado() ? "Sim" : "Não"));
        } else {
            System.out.println("Perecível: Não");
        }
    }

    private static void editarProduto() {
        System.out.print("Código do produto: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                System.out.print("Novo nome: ");
                produto.setNome(scanner.nextLine());

                System.out.print("Nova quantidade em Estoque: ");
                produto.setQuantidadeEstoque(Integer.parseInt(scanner.nextLine()));

                System.out.print("Novo preço: ");
                produto.setPreco(Double.parseDouble(scanner.nextLine()));

                System.out.print("Nova descrição: ");
                produto.setDescricao(scanner.nextLine());

                if (produto instanceof ProdutoPerecivel) {
                    ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) produto;

                    System.out.print("Nova data de validade (AAAA-MM-DD): ");
                    produtoPerecivel.setDataValidade(LocalDate.parse(scanner.nextLine()));

                    System.out.print("Refrigerado? (S/N): ");
                    produtoPerecivel.setRefrigerado(scanner.nextLine().equalsIgnoreCase("S"));
                }

                System.out.println("Produto editado com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private static void excluirProduto() {
        System.out.print("Código do produto: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                System.out.print("Tem certeza que deseja excluir o produto? (S/N): ");
                if (scanner.nextLine().equalsIgnoreCase("S")) {
                    setor.removerProduto(codigo);
                    System.out.println("Produto excluído com sucesso!");
                } else {
                    System.out.println("Exclusão cancelada.");
                }
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private static void inventarioSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }

        double valorTotal = 0;
        System.out.printf("%-20s | %-10s | %-10s | %-10s | %-30s | %-10s | %-15s\n", "Nome", "Código", "Quantidade", "Preço", "Descrição", "Validade", "Refrigerado");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        for (Produto produto : setor.getProdutos()) {
            System.out.println(produto);
            valorTotal += produto.getPreco() * produto.getQuantidadeEstoque();
        }
        System.out.printf("Valor Total do Inventário: R$ %.2f\n", valorTotal);
    }

    private static void depreciarEstoque() {
        System.out.print("Digite a porcentagem de depreciação: ");
        double porcentagem = Double.parseDouble(scanner.nextLine());

        for (Setor setor : setores) {
            setor.depreciarProdutos(porcentagem);
        }
        System.out.println("Depreciação aplicada a todos os produtos do estoque.");
    }

    private static void depreciarSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }

        System.out.print("Digite a porcentagem de depreciação: ");
        double porcentagem = Double.parseDouble(scanner.nextLine());

        setor.depreciarProdutos(porcentagem);
        System.out.println("Depreciação aplicada aos produtos do setor.");
    }

    private static void cadastrarSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        setores.add(new Setor(nomeSetor));
        System.out.println("Setor cadastrado com sucesso!");
    }

    private static Setor buscarSetor(String nomeSetor) {
        for (Setor setor : setores) {
            if (setor.getNome().equalsIgnoreCase(nomeSetor)) {
                return setor;
            }
        }
        return null;
    }
}
