/**
 * @author Ana|Bruno|Eduardo
 * @version 18.Maio.2011
 */

import java.lang.*;
import java.util.*;

public class Individual extends Utilizador
{
    // Variaveis de Instancia
    private String profissao;
    private String nickname;
    private Localizacao localizacao;
    private ArrayList<String> pedidosAmizade;
    
    
    // Metodos de instancia
    // Construtores
    /**
     * Construtor por omissao
     */
    public Individual()
    {
        super();
        this.profissao = "";
        this.nickname = "";
        this.localizacao = new Localizacao();
        this.pedidosAmizade = new  ArrayList<String>();
    }
    
    /**
     * Construtor por partes
     */
    public Individual(String cNome, String cEmail, String cDescricao, Localizacao cMorada, Stack<Mencacao> cMural, String cPassword, 
                      Stack<Mencacao> cNotificacoes, String cProfissao, String cNickname, TreeSet<String> cAmigos, Localizacao cLocalizacao,  ArrayList<String> pA)
    {
        super(cNome, cEmail, cDescricao, cMorada, cMural, cNotificacoes, cPassword, cAmigos);
        this.profissao   = cProfissao;
        this.nickname    = cNickname;
        this.localizacao = cLocalizacao;
    }
    
    public Individual(String cNome, String cEmail, String cDescricao, Localizacao cMorada, String cPassword, 
                      String cProfissao, String cNickname, Localizacao cLocalizacao)
    {
        super(cNome, cEmail, cDescricao, cMorada, cPassword);
        this.profissao   = cProfissao;
        this.nickname    = cNickname;
        this.localizacao = cLocalizacao.clone();
        pedidosAmizade = new ArrayList<String>();
    }
    
    
    
    /**
     * Construtor para clone
     */
    public Individual( Individual i)
    {
        super(i.getNome(), i.getEmail(), i.getDescricao(), i.getMorada(), i.getMural(), i.getNotificacoes(), i.getPassword(), i.getAmigos());
        profissao   = i.getProfissao();
        nickname    = i.getNickname();
        localizacao = i.getLocalizacao();
        pedidosAmizade = i.getPedidosAmizade();
    }
    
    
    
    // Gets
    public String getProfissao()            { return ( this.profissao   );   }
    public String getNickname()             { return ( this.nickname    );   }
    public Localizacao getLocalizacao()     { return ( this.localizacao );   }
    public ArrayList<String> getPedidosAmizade()
    {
        ArrayList<String> aux = new ArrayList<String>();
        for(String str: pedidosAmizade)
            aux.add(str);
        return aux;
    }
    
    // Sets
    public void setProfissao(String sProfissao)          { this.profissao = sProfissao;      }
    public void setNickname(String sNickname)            { this.nickname = sNickname;        }
    public void setLocalizacao(Localizacao sLocalizacao) { this.localizacao = sLocalizacao.clone();  }
    public void setPedidosAmizade(ArrayList<String> pA)
    {
        ArrayList<String> aux = new ArrayList<String>();
        for(String str: pedidosAmizade)
            aux.add(str);
        pedidosAmizade = aux;
    }
  
    /**
     * Adicionar pedido de amizade
     */
    public void addPedido(String str)
    {
         pedidosAmizade.add(str);
    }
    
    /**
     * Aceitar amigo
     */
    public void aceitarAmigo( String str )
    {
        pedidosAmizade.remove(str);
        this.getAmigos().add(str);
    }
    
     /**
     * Rejeitar Amizade
     */
    public void rejectAmigo( String str )
    {
        pedidosAmizade.remove(str);
    }
    
    
    // toString
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        
        s.append(super.toString());
        s.append( "Profissao: "     + this.profissao              +"\n"   );
        s.append( "Nickname: "      + this.nickname               +"\n"   );
        s.append( "Localizacao: "   + this.localizacao.toString() + "\n\n");
        s.append( "Pedidos: "   + this.pedidosAmizade.toString() + "\n\n");
        return s.toString();
    }
    
    // Clone
    public Individual clone()
    {
        return new Individual(this);
    }
    
    // Equals
    public boolean Equals( Object ob )
    {
        boolean res;

        if(this == ob) 
            res = true;

        if(ob == null || this.getClass()!=ob.getClass())
            res = false;

       Individual ind = (Individual) ob;
        res = ( this.profissao      == ind.getProfissao()   &&
                this.nickname       == ind.getNickname()    &&
                this.localizacao    == ind.getLocalizacao() &&
                this.pedidosAmizade.equals(ind.getPedidosAmizade()) &&
                super.equals(this) );

        return res;
    }
}