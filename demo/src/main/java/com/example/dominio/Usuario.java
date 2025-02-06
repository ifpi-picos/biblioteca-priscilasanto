import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private List<Notificacao> notificacoes;

    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.notificacoes = new ArrayList<>();
    }

    // Getters, setters e métodos para notificações
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public List<Notificacao> getNotificacoes() { return notificacoes; }

    public void adicionarNotificacao(Notificacao notificacao) {
        this.notificacoes.add(notificacao);
    }

    public void enviarNotificacoes(String mensagem) {
        for (Notificacao notificacao : notificacoes) {
            notificacao.enviarNotificacao(mensagem);
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Email: " + email;
    }
}