public class NotificacaoConsole implements Notificacao {
    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação no Console: " + mensagem);
    }
}