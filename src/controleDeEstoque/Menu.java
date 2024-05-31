package controleDeEstoque;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static List<Setor> setores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== Controle de Estoque =====");
            System.out.println("1. Cadastrar Setor");
            System.out.println("2. Listar Setores");
            System.out.println("3. Cadastrar Produto");
            System.out.println("4. Listar Produtos");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Editar Produto");
            System.out.println("7. Excluir Produto");
            System.out.println("8. Inventário do Setor");
            System.out.println("9. Depreciar Produtos do Estoque");
            System.out.println("10. Depreciar Produtos de um Setor");
            System.out.println("11. Sair");

            opcao = solicitarOpcao();
            switch (opcao) {
                case 1:
                    cadastrarSetor();
                    break;
                case 2:
                    listarSetores();
                    break;
                case 3:
                    cadastrarProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    buscarProduto();
                    break;
                case 6:
                    editarProduto();
                    break;
                case 7:
                    excluirProduto();
                    break;
                case 8:
                    inventarioSetor();
                    break;
                case 9:
                    depreciarEstoque();
                    break;
                case 10:
                    depreciarSetor();
                    break;
                case 11:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 11);
    }

    private static int solicitarOpcao() {
        boolean entradaValida = false;
        int opcao = 0;
        while (!entradaValida) {
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Erro: A opção deve ser um número inteiro. Por favor, tente novamente.");
            }
        }
        return opcao;
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
            System.out.println("Nome do Setor: " + setor.getNome());
        }
    }

    private static void cadastrarProduto() {
        if (setores.isEmpty()) {
            System.out.println("Nenhum setor cadastrado. Por favor, cadastre um setor primeiro.");
            return;
        }

        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        int codigo;
        while (true) {
            System.out.print("Código do produto: ");
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
        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque = Integer.parseInt(scanner.nextLine());
        System.out.print("Preço: ");
        double preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Produto produto = new Produto(nome, codigo, quantidadeEstoque, preco, descricao);
        setor.adicionarProduto(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static boolean codigoJaExiste(int codigo) {
        for (Setor setor : setores) {
            for (Produto produto : setor.getProdutos()) {
                if (produto.getCodigo() == codigo) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void listarProdutos() {
        for (Setor setor : setores) {
            System.out.println("\nSetor: " + setor.getNome());
            System.out.printf("%-20s | %-10s | %-10s\n", "Nome", "Código", "Quantidade");
            System.out.println("-----------------------------------------------");
            for (Produto produto : setor.getProdutos()) {
                System.out.printf("%-20s | %-10d | %-10d\n", produto.getNome(), produto.getCodigo(), produto.getQuantidadeEstoque());
            }
        }
    }

    private static void buscarProduto() {
        System.out.print("Código do produto: ");
        int codigo;
        try {
            codigo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: O código deve ser um número inteiro.");
            return;
        }

        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                System.out.println("Produto encontrado no setor: " + setor.getNome());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Código: " + produto.getCodigo());
                System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("Descrição: " + produto.getDescricao());
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private static void editarProduto() {
        System.out.print("Código do produto: ");
        int codigo;
        try {
            codigo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: O código deve ser um número inteiro.");
            return;
        }

        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                System.out.println("Produto encontrado no setor: " + setor.getNome());
                System.out.print("Novo nome (deixe vazio para manter o atual): ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) produto.setNome(novoNome);

                System.out.print("Nova quantidade (deixe vazio para manter a atual): ");
                String novaQuantidadeStr = scanner.nextLine();
                if (!novaQuantidadeStr.isEmpty()) produto.setQuantidadeEstoque(Integer.parseInt(novaQuantidadeStr));

                System.out.print("Novo preço (deixe vazio para manter o atual): ");
                String novoPrecoStr = scanner.nextLine();
                if (!novoPrecoStr.isEmpty()) produto.setPreco(Double.parseDouble(novoPrecoStr));

                System.out.print("Nova descrição (deixe vazio para manter o atual): ");
                String novaDescricao = scanner.nextLine();
                if (!novaDescricao.isEmpty()) produto.setDescricao(novaDescricao);

                setor.editarProduto(codigo, produto);
                System.out.println("Produto editado com sucesso!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private static void excluirProduto() {
        System.out.print("Código do produto: ");
        int codigo;
        try {
            codigo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro: O código deve ser um número inteiro.");
            return;
        }

        for (Setor setor : setores) {
            Produto produto = setor.buscarProduto(codigo);
            if (produto != null) {
                setor.removerProduto(codigo);
                System.out.println("Produto excluído com sucesso!");
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

        System.out.println("\n===== Inventário do Setor: " + setor.getNome() + " =====");
        System.out.printf("%-20s | %-10s | %-10s\n", "Nome", "Preço", "Descrição");
        System.out.println("---------------------------------------------------------");
        for (Produto produto : setor.getProdutos()) {
            System.out.printf("%-20s | %-10.2f | %-10s\n", produto.getNome(), produto.getPreco(), produto.getDescricao());
        }

        double valorTotal = setor.calcularValorTotal();
        System.out.println("---------------------------------------------------------");
        System.out.println("Valor total dos produtos no setor " + setor.getNome() + ": " + valorTotal);
    }

    private static void depreciarEstoque() {
        System.out.print("Porcentagem de depreciação: ");
        double porcentagem = Double.parseDouble(scanner.nextLine());

        for (Setor setor : setores) {
            setor.depreciarProdutos(porcentagem);
        }
        System.out.println("Todos os produtos do estoque foram depreciados em " + porcentagem + "%.");
    }

    private static void depreciarSetor() {
        System.out.print("Nome do setor: ");
        String nomeSetor = scanner.nextLine();
        Setor setor = buscarSetor(nomeSetor);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }

        System.out.print("Porcentagem de depreciação: ");
        double porcentagem = Double.parseDouble(scanner.nextLine());

        setor.depreciarProdutos(porcentagem);
        System.out.println("Todos os produtos do setor " + setor.getNome() + " foram depreciados em " + porcentagem + "%.");
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
