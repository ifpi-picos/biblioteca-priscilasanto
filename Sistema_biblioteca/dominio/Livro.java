public class Livro {
    private String autor;
    private String titulo;
    private String endereco;
    private int ano;
    private boolean emprestado;

    public Livro(String autor, String titulo, String endereco, int ano) {
        this.autor = autor;
        this.titulo = titulo;
        this.endereco = endereco;
        this.ano = ano;
        this.emprestado = false;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getAno() {
        return ano;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Ano: " + ano + ", Endereço: " + endereco + ", Emprestado: " + (emprestado ? "Sim" : "Não");
    }
}

