package com.example.dao;

import com.example.dominio.Emprestimo;
import com.example.dominio.Livro;
import com.example.dominio.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private Connection conexao;

    public EmprestimoDAO() {
        conexao = ConexaoDao.getConexao();
    }

    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (livro_titulo, usuario_cpf, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, emprestimo.getLivro().getTitulo());
            pstmt.setString(2, emprestimo.getUsuario().getCpf());
            pstmt.setDate(3, new Date(emprestimo.getDataEmprestimo().getTime()));
            pstmt.setDate(4, new Date(emprestimo.getDataDevolucao().getTime()));
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                emprestimo.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo"; // Ajuste a consulta conforme necessário
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Para criar o Emprestimo, você precisará buscar o Livro e o Usuario
                Livro livro = new LivroDAO().buscarPorTitulo(rs.getString("livro_titulo"));
                Usuario usuario = new UsuarioDAO().buscarPorCpf(rs.getString("usuario_cpf"));
                Date dataEmprestimo = rs.getDate("data_emprestimo");
                Date dataDevolucao = rs.getDate("data_devolucao");

                Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucao);
                emprestimo.setId(rs.getInt("id")); // Define o ID do empréstimo
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    
}