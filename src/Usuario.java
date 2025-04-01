import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String email;
    private List<Avaliacao> listaAvaliacoes;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.listaAvaliacoes = new ArrayList<>();
    }

    public void avaliarLivro(Livro livro, int nota, String comentario) {
        Avaliacao avaliacao = new Avaliacao(this, livro, nota, comentario);
        livro.adicionarAvaliacao(avaliacao);
        listaAvaliacoes.add(avaliacao);
        System.out.println("Avaliação adicionada com sucesso!");
    }

    public String getNome() { return nome; }
}
