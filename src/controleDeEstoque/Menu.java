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
            System.out.println("1. Setor");
            System.out.println("2. Produto");
            System.out.println("3. Depreciação");
            System.out.println("4. Sair");
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
                    menuSetor();
                    break;
                case 2:
                    menuProduto();
                    break;
                case 3:
                    menuDepreciacao();
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuSetor() {
        int opcao = 0;
        do {
            System.out.println("\n===== Menu Setor =====");
            System.out.println("1. Cadastrar Setor");
            System.out.println("2. Listar Setores");
            System.out.println("3. Excluir Setor");
            System.out.println("4. Editar Setor");
            System.out.println("5. Voltar");
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
                    listarSetores();
                    break;
                case 3:
                    excluirSetor();
                    break;
                case 4:
                    editarSetor();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuProduto() {
        int opcao = 0;
        do {
            System.out.println("\n===== Menu Produto =====");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Excluir Produto");
            System.out.println("4. Editar Produto");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Voltar");
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
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    editarProduto();
                    break;
                case 5:
                    System.out.print("Digite o código do produto: "); // Solicita o código do produto
                    int codigoProduto = Integer.parseInt(scanner.nextLine()); // Lê o código fornecido pelo usuário
                    buscarProduto(codigoProduto); // Chama o método buscarProduto() passando o código como argumento
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }
    

    private static void menuDepreciacao() {
        int opcao = 0;
        do {
            System.out.println("\n===== Menu Depreciação =====");
            System.out.println("1. Depreciar Estoque");
            System.out.println("2. Depreciar Setor");
            System.out.println("3. Voltar");
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
                    depreciarEstoque();
                    break;
                case 2:
                    depreciarSetor();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
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

    private static void listarProdutos()
    {
        System.out.println("\n===== Lista de Produtos =====");
        for (Setor setor : setores) {
            System.out.println("Setor: " + setor.getNome());
            System.out.printf("%-20s | %-10s | %-10s | %-10s | %-30s | %-10s | %-15s\n", "Nome", "Código", "Quantidade", "Preço", "Descrição", "Validade", "Refrigerado");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            setor.listarProdutosOrdenados();
            System.out.println();
        }
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

    private static void cadastrarSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        setores.add(new Setor(nomeSetor));
        System.out.println("Setor cadastrado com sucesso!");
    }

    private static void listarSetores() {
        System.out.println("\n===== Lista de Setores =====");
        for (Setor setor : setores) {
            System.out.println(setor.getNome());
        }
    }

    private static void excluirSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }

        System.out.print("Tem certeza que deseja excluir o setor? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            setores.remove(setor);
            System.out.println("Setor excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }
    }

    private static void editarSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }
    
        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();
        setor.setNome(novoNome);
    
        System.out.println("Setor editado com sucesso!");
    }
    
    private static void buscarProduto(int codigo) {
        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                exibirDetalhesProduto(produto);
                return; // Sai da função após encontrar e exibir o produto
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
        }
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

    private static Setor buscarSetor(String nomeSetor) {
        for (Setor setor : setores) {
            if (setor.getNome().equalsIgnoreCase(nomeSetor)) {
                return setor;
            }
        }
        return null;
    }
}
