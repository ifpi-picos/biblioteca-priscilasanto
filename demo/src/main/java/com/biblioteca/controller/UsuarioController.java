package com.biblioteca.controller;

import java.util.ArrayList;

import com.biblioteca.Dao.UsuarioDao;
import com.biblioteca.model.Usuario;


public class UsuarioController {
    private final UsuarioDao usuarioDao;

    public UsuarioController() {
        this.usuarioDao = new UsuarioDao();
    }

    // Método para cadastrar um novo usuário
    public void cadastrarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarioDao.create(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro: O usuário fornecido é nulo.");
        }
    }

    // Método para listar todos os usuários
    public void listarUsuarios() {
        ArrayList<Usuario> usuarios = usuarioDao.read();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("CPF: " + usuario.getCpf());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("------------------------------");
            }
        }
    }

    // Método para listar um usuário por ID
    public Usuario listarUsuarioPorId(int id) {
        Usuario usuario = usuarioDao.readById(id);
        if (usuario != null) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Email: " + usuario.getEmail());
        } else {
            System.out.println("Usuário não encontrado com o ID: " + id);
        }
        return usuario;
    }

    // Método para atualizar os dados de um usuário
    public void atualizarUsuario(Usuario usuario, int id) {
        if (usuario != null) {
            usuarioDao.update(usuario, id);
            System.out.println("Usuário atualizado com sucesso!");
        } else {
            System.out.println("Erro: O usuário fornecido é nulo.");
        }
    }

    // Método para deletar um usuário
    public void deletarUsuario(int id) {
        Usuario usuario = listarUsuarioPorId(id);
        if (usuario != null) {
            usuarioDao.delete(id);
            System.out.println("Usuário deletado com sucesso!");
        } else {
            System.out.println("Erro: Usuário não encontrado para deletar.");
        }
    }
}
