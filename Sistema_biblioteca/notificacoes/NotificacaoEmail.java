public class NotificacaoEmail implements Notificacao {
    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação por E-mail: " + mensagem);
    }
}