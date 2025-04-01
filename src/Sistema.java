import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private final String arquivoLivros = "livros.dat";
    private final String arquivoUsuarios = "usuarios.dat";

    public Sistema() {
        this.livros = carregarLivros();
        this.usuarios = carregarUsuarios();
    }

    public void adicionarLivro(String titulo, String autor, String isbn) {
        livros.add(new Livro(titulo, autor, isbn));
        salvarLivros();
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void cadastrarUsuario(String nome, String email) {
        usuarios.add(new Usuario(nome, email));
        salvarUsuarios();
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public void recomendarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Ainda não há livros cadastrados.");
            return;
        }

        List<Livro> ranking = new ArrayList<>(livros);
        ranking.sort(Comparator.comparingDouble(Livro::calcularNotaMedia).reversed());

        System.out.println("\nTOP 5 LIVROS MAIS BEM AVALIADOS");
        int limite = Math.min(5, ranking.size()); 
        for (int i = 0; i < limite; i++) {
            Livro livro = ranking.get(i);
            System.out.printf("%d. %s - Nota Média: %.2f\n", i + 1, livro.getTitulo(), livro.calcularNotaMedia());
        }
    }


    private void salvarLivros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoLivros))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Livro> carregarLivros() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoLivros))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) { 
                List<?> lista = (List<?>) obj; 
                for (Object item : lista) {
                    if (!(item instanceof Livro)) {
                        System.out.println("Erro: A lista contém itens que não são do tipo Livro.");
                        return new ArrayList<>();
                    }
                }
                return (List<Livro>) lista;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    

    private void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoUsuarios))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
    // pequena gambiarra aqui rs
    @SuppressWarnings("unchecked")
    private List<Usuario> carregarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoUsuarios))) {
            Object obj = ois.readObject(); 
            if (obj instanceof List<?>) { 
                List<?> lista = (List<?>) obj;
                for (Object item : lista) {
                    if (!(item instanceof Usuario)) {
                        System.out.println("Erro: A lista contém itens que não são do tipo Usuario.");
                        return new ArrayList<>();
                    }
                }
                return (List<Usuario>) lista;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    

    public void executar() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Adicionar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Avaliar Livro");
            System.out.println("4. Exibir Livros");
            System.out.println("5. Ver Avaliações de um Livro");
            System.out.println("6. Ver Ranking de Livros Recomendados");
            
            

            String entrada = scanner.nextLine();
            int opcao;
            
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
                continue;
            }
            

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    adicionarLivro(titulo, autor, isbn);
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    cadastrarUsuario(nome, email);
                    break;

                case 3:
                    System.out.print("Nome do usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    Usuario usuario = buscarUsuario(nomeUsuario);
                    if (usuario == null) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    System.out.print("Título do livro: ");
                    String tituloLivro = scanner.nextLine();
                    Livro livro = buscarLivro(tituloLivro);
                    if (livro == null) {
                        System.out.println("Livro não encontrado!");
                        break;
                    }

                    System.out.print("Nota (1 a 5): ");
                    int nota = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Comentário: ");
                    String comentario = scanner.nextLine();

                    usuario.avaliarLivro(livro, nota, comentario);
                    salvarLivros();
                    break;

                case 4:
                    for (Livro l : livros) {
                        l.exibirDetalhes();
                        System.out.println("---------------------");
                    }
                    break;

                case 5:
                    System.out.print("Digite o título do livro: ");
                    String tituloLivroAvaliar = scanner.nextLine();
                    Livro livroAvaliar = buscarLivro(tituloLivroAvaliar);
                        
                    if (livroAvaliar == null) {
                        System.out.println("Livro não encontrado!");
                    } else {
                        livroAvaliar.exibirAvaliacoes();
                    }
                    break;

                case 6:
                    recomendarLivros();
                    break;
                

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
