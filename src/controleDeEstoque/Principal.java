package controleDeEstoque;

import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) {
        // Cadastro automático de produtos e setores para teste
        Setor bebidas = new Setor("Bebidas");
        bebidas.adicionarProduto(new Produto("Suco de Laranja", 1, 20, 5.99, "Suco natural de laranja"));
        bebidas.adicionarProduto(new Produto("Refrigerante", 2, 15, 4.99, "Refrigerante de cola"));
        bebidas.adicionarProduto(new Produto("Água Mineral", 3, 50, 1.99, "Água mineral sem gás"));
        bebidas.adicionarProduto(new ProdutoPerecivel("Leite", 4, 30, 3.99, "Leite integral", LocalDate.now().plusDays(20), true));
        bebidas.adicionarProduto(new ProdutoPerecivel("Cerveja", 5, 40, 2.99, "Cerveja pilsner", LocalDate.now().plusDays(180), false));
        bebidas.adicionarProduto(new Produto("Vinho", 6, 10, 25.99, "Vinho tinto seco"));
        bebidas.adicionarProduto(new Produto("Whisky", 7, 5, 99.99, "Whisky escocês 12 anos"));

        Setor laticinios = new Setor("Laticínios");
        laticinios.adicionarProduto(new ProdutoPerecivel("Queijo", 8, 20, 19.99, "Queijo minas", LocalDate.now().plusDays(15), true));
        laticinios.adicionarProduto(new ProdutoPerecivel("Iogurte", 9, 30, 4.99, "Iogurte natural", LocalDate.now().plusDays(10), true));
        laticinios.adicionarProduto(new ProdutoPerecivel("Manteiga", 10, 25, 7.99, "Manteiga sem sal", LocalDate.now().plusDays(30), true));
        laticinios.adicionarProduto(new Produto("Leite Condensado", 11, 50, 3.49, "Leite condensado tradicional"));
        laticinios.adicionarProduto(new Produto("Creme de Leite", 12, 35, 3.89, "Creme de leite fresco"));
        laticinios.adicionarProduto(new Produto("Requeijão", 13, 15, 5.49, "Requeijão cremoso"));
        laticinios.adicionarProduto(new ProdutoPerecivel("Ricota", 14, 10, 6.99, "Ricota fresca", LocalDate.now().plusDays(20), true));

        Setor frutas = new Setor("Frutas");
        frutas.adicionarProduto(new ProdutoPerecivel("Maçã", 15, 100, 2.99, "Maçã Fuji", LocalDate.now().plusDays(15), false));
        frutas.adicionarProduto(new ProdutoPerecivel("Banana", 16, 80, 1.99, "Banana Nanica", LocalDate.now().plusDays(10), false));
        frutas.adicionarProduto(new ProdutoPerecivel("Laranja", 17, 150, 3.49, "Laranja Pera", LocalDate.now().plusDays(20), false));
        frutas.adicionarProduto(new ProdutoPerecivel("Uva", 18, 60, 4.99, "Uva Thompson", LocalDate.now().plusDays(7), false));
        frutas.adicionarProduto(new ProdutoPerecivel("Manga", 19, 40, 3.99, "Manga Palmer", LocalDate.now().plusDays(5), false));
        frutas.adicionarProduto(new ProdutoPerecivel("Morango", 20, 50, 5.99, "Morango Orgânico", LocalDate.now().plusDays(3), false));
        frutas.adicionarProduto(new ProdutoPerecivel("Melancia", 21, 20, 7.99, "Melancia", LocalDate.now().plusDays(15), false));

        Menu.setores.add(bebidas);
        Menu.setores.add(laticinios);
        Menu.setores.add(frutas);

        Menu.exibirMenu();
    }
}
