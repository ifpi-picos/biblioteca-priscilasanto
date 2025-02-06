import java.util.Date;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo, Date dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    // Getters e toString
    public Livro getLivro() { return livro; }
    public Usuario getUsuario() { return usuario; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucao() { return dataDevolucao; }

    @Override
    public String toString() {
        return "Livro: " + livro.getTitulo() + ", Usuario: " + usuario.getNome() + 
               ", Data Empréstimo: " + dataEmprestimo + ", Data Devolução: " + dataDevolucao;
    }
}