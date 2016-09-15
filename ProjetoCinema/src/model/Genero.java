
package model;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Genero {
    
    private static int CODIGO_GERADO = 0;
    private int codigo;
    private String nome;
    private String descricao;

    public Genero(String nome, String descricao) {
        this.codigo = gerarCodigo();
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    private int gerarCodigo() {
        return (CODIGO_GERADO++);
    }

   public int getCodigo() {
       return codigo;
   }
    
}
