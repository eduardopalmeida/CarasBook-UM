import java.lang.*;
import java.util.*;

public class Empresa extends Utilizador
{
    // Variaveis de Instancia
    private String sector;

     /**
     * Construtor por partes
     */
    
    public Empresa(String cNome, String cEmail, String cDescricao, Localizacao cMorada,String cSector, String cPassword)
    {
        super(cNome, cEmail, cDescricao, cMorada, cPassword);
        this.sector = cSector;
    }
    
   /**
     * Construtor por omissao
     */
    public Empresa()
    {
        super();
        this.sector = "";
    }
    /**
     * Construtor para clone
     */    
    public Empresa(Empresa e)
    {
        super(e.getNome(), e.getEmail(), e.getDescricao(), e.getMorada(), e.getMural(), e.getNotificacoes(), e.getPassword(), e.getAmigos());
        sector = e.getSector();
    }

    // Get's
    public String getSector()   { return ( this.sector );   }

    // Set's
    public void setSector(String sSector)   { this.sector = sSector;    }

    // toString
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(super.toString());
        s.append("Sector: " + sector +"\n\n");
        
        return s.toString();
    }
    
    // Clone
    public Empresa clone()
    {
        return new Empresa(this);
    }
    
    // Equals
    public boolean equals( Object ob )    {
        boolean res;
        
        if(this == ob) 
            res = true;
            
        if(ob == null || this.getClass()!=ob.getClass())
            res = false;
            
        Empresa aE = (Empresa) ob;
        res = ( sector.equals(aE.sector) && super.equals(this));
        
        return res;
    }
}