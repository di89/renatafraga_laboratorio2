/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.util.ArrayList;
import java.util.List;
import model.Venda;

/**
 *
 * @author renat
 */
public class RepositorioVenda {
    private List<Venda> vendas;
    
    
    public RepositorioVenda() {
        vendas = new ArrayList<>();
    }
    
    public boolean addVenda(Venda venda) {
        System.out.println("Venda efetuada com sucesso!");
        return (vendas.add(venda));
    }
    
    public List<Venda> getVendas() {
        return vendas;
    }
    
    public Venda buscarVenda(int codigo) {
        for(Venda venda: vendas) {
            if(venda.getCodigoVenda() == codigo) {
                return venda;
            }
        }
        return null;
    }
    
    
    public boolean existeVenda(int codigo) {
        if(vendas.isEmpty()) {
            return false;
        } else {
            for(Venda venda: vendas) {
                if(venda.getCodigoVenda()==codigo) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void removerVenda(int codigo) {
        for(int i=0;i<vendas.size();i++) {
           if(vendas.get(i).getCodigoVenda()==codigo) {
               vendas.remove(i);
               
               break;
           } else {
               System.out.println("Código inválido!");
           }
        }
        
        
    }
}
