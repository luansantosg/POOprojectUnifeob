import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String titulo;
    private String autor;
    private String isbn;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public double calcularNotaMedia() {
        if (avaliacoes.isEmpty()) {
            return 0;
        }
    
        double soma = 0;
        for (Avaliacao a : avaliacoes) {
            soma += a.getNota();
        }
        return soma / avaliacoes.size();
    }
    
    

    public void exibirDetalhes() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Média de Notas: " + calcularMediaNotas());
            }
        
            private String calcularMediaNotas() {
                throw new UnsupportedOperationException("Unimplemented method 'calcularMediaNotas'");
            }
        
            public void exibirAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            System.out.println("Ainda não há avaliações para este livro.");
            return;
        }
    
        System.out.println("Avaliações para \"" + titulo + "\":");
        for (Avaliacao a : avaliacoes) {
            System.out.println("- " + a.getUsuario().getNome() + " deu nota " + a.getNota());
            System.out.println("  Comentário: " + a.getComentario());
            System.out.println("----------------------");
        }
    }
    

    public String getTitulo() { return titulo; }
}
