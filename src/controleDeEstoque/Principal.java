package controleDeEstoque;

public class Principal {
    public static void main(String[] args) {
        cadastrarSetoresAutomaticamente();
        Menu.exibirMenu();
    }

    private static void cadastrarSetoresAutomaticamente() {
        Setor setor1 = new Setor("Roupas");
        setor1.adicionarProduto(new Produto("Camisa", 1, 20, 29.99, "Camisa casual de algodão"));
        setor1.adicionarProduto(new Produto("Calça", 2, 15, 49.99, "Calça jeans slim fit"));

        Setor setor2 = new Setor("Calçados");
        setor2.adicionarProduto(new Produto("Tênis", 3, 10, 99.99, "Tênis esportivo confortável"));

        Setor setor3 = new Setor("Acessórios");
        setor3.adicionarProduto(new Produto("Boné", 4, 30, 19.99, "Boné de baseball ajustável"));

        Menu.setores.add(setor1);
        Menu.setores.add(setor2);
        Menu.setores.add(setor3);
    }
}
