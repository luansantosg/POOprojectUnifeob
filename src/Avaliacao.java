public class Avaliacao {
    private Usuario usuario;
    private Livro livro;
    private int nota;
    private String comentario;

    public Avaliacao(Usuario usuario, Livro livro, int nota, String comentario) {
        this.usuario = usuario;
        this.livro = livro;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
