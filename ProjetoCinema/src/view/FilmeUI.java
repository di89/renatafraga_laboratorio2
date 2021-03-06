
package view;

import DAO.FilmeDAO;
import DAO.GeneroDAO;
import DAO.impl_bd.FilmeDAOBD;
import DAO.impl_bd.GeneroDAOBD;
import dominio.Filme;
import dominio.Genero;
import java.util.InputMismatchException;
import java.util.List;
import util.Console;
import view.menu.FilmeMenu;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class FilmeUI {
    
   private GeneroDAO generoDao;
   private FilmeDAO filmeDao;

    public FilmeUI() {
        generoDao = new GeneroDAOBD();
        filmeDao = new FilmeDAOBD();
    }
    
    
    
    public void iniciar() {
        int opcao = 0;
        do {
            try {
                System.out.println(FilmeMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");

            switch(opcao) {
                case FilmeMenu.OP_INSERIRFILME:
                    cadastrarFilme();
                    break;
                case FilmeMenu.OP_INSERIRGENERO:
                    cadastrarGenero();
                    break;
                case FilmeMenu.OP_BUSCARFILME:
                    buscarFilme(); 
                    break;
                case FilmeMenu.OP_BUSCARGENERO:
                     buscarGenero();
                    break;
                case FilmeMenu.OP_LISTARFILMES:
                      mostrarFilme();
                    break;
                case FilmeMenu.OP_LISTARGENEROS:
                     mostrarGenero();
                    break;
                case FilmeMenu.OP_REMOVERFILME:
                     removerFilme();
                    break;
                case FilmeMenu.OP_REMOVERGENERO:
                     removerGenero();
                     break;
                case FilmeMenu.OP_VOLTAR:
                     break;
                default:
                    System.out.println("Opcao invalida!");
            }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }
        } while(opcao != FilmeMenu.OP_VOLTAR);
    }  
     
    private void cadastrarFilme() {
        
        mostrarGenero();
        int codigoGenero = Console.scanInt("Codigo genero: ");
        String titulo = Console.scanString("Titulo: ");
        String sinopse = Console.scanString("Sinopse: ");
        
        filmeDao.cadastrar(new Filme(titulo,generoDao.buscarPorCodigo(codigoGenero),sinopse));
        
        System.out.println("Filme " + titulo + " cadastrado com sucesso!");
       
        
        
    }
   
    
    public void listarFilme() {
        List<Filme> listaFilmes = filmeDao.listar();
        this.mostrarFilme(listaFilmes);
    }
    
    
    
    private void removerFilme() {
       int codigo = Console.scanInt("Codigo do filme: ");
        Filme filme = filmeDao.buscarPorCodigo(codigo);
        
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse filme?")) {
            filmeDao.remover(filme);
            System.out.println("Filme removido com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        } 
    }

    private void buscarFilme() {
       int codigo = Console.scanInt("Codigo do filme: ");
        mostrarFilme(filmeDao.buscarPorCodigo(codigo));
    }
    
     private void buscarGenero() {
       int codigo = Console.scanInt("Codigo do genero: ");
       mostrarGenero(generoDao.buscarPorCodigo(codigo));
    }
    
     private void mostrarFilme(Filme f) {
        System.out.println("-----------------------------");
        System.out.println("Filme");
         System.out.println("Codigo: "+ f.getCodigo());
        System.out.println("Titulo: " + f.getTitulo());
        System.out.println("Genero " + f.getGenero().getCodigo());
        System.out.println("Sinopse " + f.getSinopse());
        System.out.println("-----------------------------");
    }

    private void mostrarFilme(List<Filme> listaFilmes) {
        if (listaFilmes.isEmpty()) {
            System.out.println("Filmes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-30s", "|SINOPSE ") + "\t" 
            + String.format("%-30s", "|GENERO"));
          
            for (Filme filme: listaFilmes) {
                System.out.println(String.format("%-10s", filme.getCodigo()) + "\t"
                        + String.format("%-20s", "|" + filme.getTitulo()) + "\t"
                        + String.format("%-30s", "|" + filme.getSinopse()) + "\t"
                        + String.format("%-30s", "|" + filme.getGenero().getNome()));
            }
        }
    }
     
   
     private void cadastrarGenero() {
        String nome = Console.scanString("Gênero: ");
        String descricao = Console.scanString("Descricao: ");
        generoDao.cadastrar(new Genero(nome, descricao));
        
        System.out.println("Gênero" + nome + " cadastrado com sucesso!");
    }
     
    
    
   

    private void removerGenero() {
        
        int codigo = Console.scanInt("Codigo do genero: ");
        Genero gen = generoDao.buscarPorCodigo(codigo);
        this.mostrarGenero(gen);
        if (UIUtil.getConfirmacao("Realmente deseja excluir esse paciente?")) {
            generoDao.remover(gen);
            System.out.println("Gênero removido com sucesso!");
        } else {
            System.out.println("Operacao cancelada!");
        } 
    }
    
    
     public void mostrarFilme() {
        List<Filme> listaFilmes = filmeDao.listar();
        this.mostrarFilme(listaFilmes);
    
     }
     
    public void mostrarGenero() {
        List<Genero> listaGeneros = generoDao.listar();
        this.mostrarGenero(listaGeneros);
    }
    
    
  
    private void mostrarGenero(Genero g) {
        System.out.println("-----------------------------");
        System.out.println("Genero");
        System.out.println("Nome: " + g.getNome());
        System.out.println("Descricao: " + g.getDescricao());
        System.out.println("-----------------------------");
    }

    private void mostrarGenero(List<Genero> listaGeneros) {
        if (listaGeneros.isEmpty()) {
            System.out.println("Generos nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-30s", "|DESCRICAO"));
            for (Genero genero : listaGeneros) {
                System.out.println(String.format("%-10s", genero.getCodigo()) + "\t"
                        + String.format("%-20s", "|" + genero.getNome()) + "\t"
                        + String.format("%-30s", "|" + genero.getDescricao()));
            }
        }
    }
    
    
    
}
    

    
    
   
    
