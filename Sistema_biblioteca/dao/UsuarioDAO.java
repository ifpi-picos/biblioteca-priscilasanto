import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // Configurações do banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USER = "postgres"; // Usuário padrão do PostgreSQL
    private static final String PASSWORD = "sua_senha"; // Senha que você definiu durante a instalação

    // Método para salvar notificações no banco de dados
    public void salvarNotificacoes(Usuario usuario) {
        String sql = "INSERT INTO notificacoes (cpf, tipo) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Notificacao notificacao : usuario.getNotificacoes()) {
                stmt.setString(1, usuario.getCpf());
                stmt.setString(2, notificacao.getClass().getSimpleName());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar notificações do banco de dados
    public List<Notificacao> carregarNotificacoes(String cpf) {
        List<Notificacao> notificacoes = new ArrayList<>();
        String sql = "SELECT tipo FROM notificacoes WHERE cpf = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                switch (tipo) {
                    case "NotificacaoConsole":
                        notificacoes.add(new NotificacaoConsole());
                        break;
                    case "NotificacaoEmail":
                        notificacoes.add(new NotificacaoEmail());
                        break;
                    case "NotificacaoSMS":
                        notificacoes.add(new NotificacaoSMS());
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificacoes;
    }
}