import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;
    private Scanner scanner;

    public Biblioteca() {
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Métodos para cadastrar, listar, emprestar e devolver livros
    public void cadastrarLivro() { /* ... */ }
    public void listarLivros() { /* ... */ }
    public void listarLivrosEmprestados() { /* ... */ }
    public void listarHistoricoEmprestimos() { /* ... */ }
    public void emprestarLivro() { /* ... */ }
    public void devolverLivro() { /* ... */ }
    private Livro buscarLivroPorTitulo(String titulo) { /* ... */ }

    // Menu de opções
    public void exibirMenu() {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Listar todos os livros");
            System.out.println("3. Listar livros emprestados");
            System.out.println("4. Listar histórico de empréstimos");
            System.out.println("5. Emprestar livro");
            System.out.println("6. Devolver livro");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1: cadastrarLivro(); break;
                case 2: listarLivros(); break;
                case 3: listarLivrosEmprestados(); break;
                case 4: listarHistoricoEmpréstimos(); break;
                case 5: emprestarLivro(); break;
                case 6: devolverLivro(); break;
                case 7: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}