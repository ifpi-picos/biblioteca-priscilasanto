package com.example;

import com.example.dao.EmprestimoDAO;
import com.example.dao.LivroDAO;
import com.example.dao.UsuarioDAO;
import com.example.dominio.Emprestimo;
import com.example.dominio.Livro;
import com.example.dominio.Usuario;
import com.example.notificacoes.Notificacao;
import com.example.notificacoes.NotificacaoConsole;
import com.example.notificacoes.NotificacaoEmail;
import com.example.notificacoes.NotificacaoSMS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private LivroDAO livroDAO = new LivroDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private Scanner scanner = new Scanner(System.in);

    public void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        if (livroDAO.buscarPorTitulo(titulo) != null) {
            System.out.println("Livro com esse título já existe.");
            return;
        }
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Livro livro = new Livro(autor, titulo, endereco, ano);
        livroDAO.salvar(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void listarLivros() {
        List<Livro> livros = livroDAO.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public void listarLivrosEmprestados() {
        List<Livro> livros = livroDAO.listarEmprestados();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro emprestado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    public void listarHistoricoEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
        } else {
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }

    public void emprestarLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = livroDAO.buscarPorTitulo(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (livro.isEmprestado()) {
            System.out.println("Livro já está emprestado.");
            return;
        }

        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = usuarioDAO.buscarPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Data de empréstimo (dd/MM/yyyy): ");
        String dataEmprestimoStr = scanner.nextLine();
        Date dataEmprestimo = ConverterData.converterParaData(dataEmprestimoStr);
        if (dataEmprestimo == null) {
            return;
        }

        System.out.print("Data de devolução (dd/MM/yyyy): ");
        String dataDevolucaoStr = scanner.nextLine();
        Date dataDevolucao = ConverterData.converterParaData(dataDevolucaoStr);
        if (dataDevolucao == null) {
            return;
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucao);
        emprestimoDAO.salvar(emprestimo);
        livro.setEmprestado(true);
        livroDAO.atualizar(livro); // Atualiza o livro no banco de dados

        // Enviar notificações
        enviarNotificacoes(usuario, "Livro '" + livro.getTitulo() + "' emprestado com sucesso!");

        System.out.println("Empréstimo realizado com sucesso!");
    }

    public void devolverLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = livroDAO.buscarPorTitulo(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (!livro.isEmprestado()) {
            System.out.println("Livro não está emprestado.");
            return;
        }

        livro.setEmprestado(false);
        livroDAO.atualizar(livro);

        // Enviar notificações
        enviarNotificacoes(livro.getUsuario(), "Livro '" + livro.getTitulo() + "' devolvido com sucesso!");

        System.out.println("Livro devolvido com sucesso!");
    }

    private void enviarNotificacoes(Usuario usuario, String mensagem) {
        List<Notificacao> notificacoes = new ArrayList<>();
        notificacoes.add(new NotificacaoConsole());
        notificacoes.add(new NotificacaoEmail());
        notificacoes.add(new NotificacaoSMS());

        usuario.enviarNotificacoes(notificacoes, mensagem);
    }

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
            scanner.nextLine(); // Limpar o buffer

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