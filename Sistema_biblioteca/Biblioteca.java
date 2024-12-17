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

    // Cadastrar livro
    public void cadastrarLivro() {
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o endereço do livro: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o ano de publicação: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Livro livro = new Livro(autor, titulo, endereco, ano);
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    // Listar todos os livros
    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    // Listar livros emprestados
    public void listarLivrosEmprestados() {
        boolean encontrou = false;
        for (Livro livro : livros) {
            if (livro.isEmprestado()) {
                System.out.println(livro);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Não há livros emprestados no momento.");
        }
    }

    // Listar histórico de empréstimos
    public void listarHistoricoEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado.");
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    // Emprestar livro
    public void emprestarLivro() {
        System.out.print("Digite o título do livro que deseja emprestar: ");
        String titulo = scanner.nextLine();
        Livro livro = buscarLivroPorTitulo(titulo);

        if (livro != null && !livro.isEmprestado()) {
            System.out.print("Digite o nome do usuário: ");
            String nomeUsuario = scanner.nextLine();
            System.out.print("Digite o CPF do usuário: ");
            String cpfUsuario = scanner.nextLine();
            System.out.print("Digite o e-mail do usuário: ");
            String emailUsuario = scanner.nextLine();

            Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, emailUsuario);
            System.out.print("Digite a data de devolução (formato: dd/MM/yyyy): ");
            String dataDevolucaoStr = scanner.nextLine();
            Date dataEmprestimo = new Date();
            Date dataDevolucao = ConverterData.converterParaData(dataDevolucaoStr);

            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucao);
            emprestimos.add(emprestimo);
            livro.setEmprestado(true);

            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Livro não disponível ou não encontrado.");
        }
    }

    // Devolver livro
    public void devolverLivro() {
        System.out.print("Digite o título do livro que deseja devolver: ");
        String titulo = scanner.nextLine();
        Livro livro = buscarLivroPorTitulo(titulo);

        if (livro != null && livro.isEmprestado()) {
            livro.setEmprestado(false);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Este livro não foi emprestado ou não existe.");
        }
    }

    // Buscar livro por título
    private Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

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
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarLivrosEmprestados();
                    break;
                case 4:
                    listarHistoricoEmprestimos();
                    break;
                case 5:
                    emprestarLivro();
                    break;
                case 6:
                    devolverLivro();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
