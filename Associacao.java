import java.lang.*;
import java.util.*;

public class Associacao extends Utilizador
{
    // Variaveis de Instancia
    private double cash;

    /**
     * Construtor por omissao
     */
     public Associacao()
    {
        super();
        this.cash = 0;
    }
    
    /**
     * Construtor por partes
     */
    public Associacao(String cNome, String cEmail, String cDescricao, Localizacao cMorada, Stack<Mencacao> cMural, Stack<Mencacao> cNotificacoes, String cPassword, double cCash, TreeSet<String> cAmigos)
    {
        super(cNome, cEmail, cDescricao, cMorada, cMural, cNotificacoes, cPassword, cAmigos);
        this.cash = cCash;
    }
    
    public Associacao(String cNome, String cEmail, String cDescricao, Localizacao cMorada, String cPassword, double cCash)
    {
        super(cNome, cEmail, cDescricao, cMorada, cPassword);
        this.cash = cCash;
    }
    
    /**
     * Construtor para clone
     */    
    public Associacao( Associacao a)
    {
        super(a.getNome(), a.getEmail(), a.getDescricao(), a.getMorada(), a.getMural(), a.getNotificacoes(), a.getPassword(), a.getAmigos());
        cash = a.getCash();
    }

    // Get's
    public double getCash()   { return ( this.cash );   }

    // Set's
    public void setSector(double sCash)   { this.cash = sCash;  }
    
    // toString
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        
        s.append(super.toString());
        s.append( "Cash: " + this.cash +"\n\n");
    
        return s.toString();
    }
    
    // Clone
    public Associacao clone()
    {
        return new Associacao(this);
    }
    
    // Equals
    public boolean equals( Object ob )
    {
        boolean res;

        if(this == ob) 
            res = true;

        if(ob == null || this.getClass()!=ob.getClass())
            res = false;

        Associacao aA = (Associacao) ob;
        res = ( this.cash == aA.getCash()) && super.equals(this);

        return res;
    }
}