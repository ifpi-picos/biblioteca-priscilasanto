package com.example.dao;

import com.example.dominio.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection conexao;

    public LivroDAO() {
        conexao = ConexaoDao.getConexao();
    }

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, endereco, emprestado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setInt(3, livro.getAno());
            pstmt.setString(4, livro.getEndereco());
            pstmt.setBoolean(5, livro.isEmprestado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM livro WHERE titulo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Livro livro = new Livro(rs.getString("autor"), rs.getString("titulo"), rs.getString("endereco"), rs.