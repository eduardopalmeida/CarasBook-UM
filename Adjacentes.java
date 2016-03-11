import java.util.*;
import java.io.*;

public class Adjacentes implements Serializable 
{
    //VARIAVEIS DE INSTANCIA
    TreeSet<Adjacente> adjacentes;
    
    //METODOS DE INSTANCIA
    //Construtor
    /**
     * Construtor por omissao
     */
    public Adjacentes()
    {
        adjacentes = new TreeSet<Adjacente>( new AdjacenteComparator ());
    }
    
    /**
     * Construtor com comparator
     */
    public Adjacentes( AdjacenteComparator ac)
    {
        adjacentes = new TreeSet<Adjacente>( ac );
    }
    
    /**
     * Construtor para clone
     */
    public Adjacentes( Adjacentes ad )
    {
        adjacentes = ad.getAdjacentes();
    }
    
    /**
     * Construtor por partes
     */
    public Adjacentes( TreeSet<Adjacente> ad )
    {
        adjacentes = new TreeSet<Adjacente>();
        for(Adjacente adj : ad)
            adjacentes.add(adj.clone());
    }
    
   
    //Gets
    public TreeSet<Adjacente> getAdjacentes( )
    {
        TreeSet<Adjacente> aux = new TreeSet<Adjacente>(new AdjacenteComparator ());
        for(Adjacente adj : adjacentes)
            aux.add(adj.clone());
        return aux;
    }
    
    //Sets
    public void setAdjacentes( TreeSet<Adjacente> ad )
    {
        adjacentes = new TreeSet<Adjacente>();
        for(Adjacente adj : ad)
            adjacentes.add(adj.clone());
    }
    /**
     * 
     */
    public ArrayList<Adjacente> devolveList()
    {
        ArrayList<Adjacente> res = new ArrayList<Adjacente>();
        for(Adjacente ad: adjacentes)
            res.add(ad.clone());
        return res;
    }
    
    /**
     * Devolve um adjacente dado o seu indice
     * 
     */
    public Adjacente getAdjacente( int indice )   
    { 
        Adjacente ad = null;
        boolean find = false;
        for (Iterator it = adjacentes.iterator(); it.hasNext() && !find; ) 
        {
            ad = (Adjacente)it.next();
            if(ad.getIndice() == indice)
                find = true;
        } 
        if(!find) ad = null;
        return ad;
    }
    
    
    /**
     * Adicona um adjacente
     * 
     * @return Boleano que indica se a insercao foi bem sucedida
     */
    public boolean addAdjacente( Adjacente ad )     
    { 
        Adjacente adAux;
        int indice = ad.getIndice();
        boolean find =  false;
        
        for(Iterator it = adjacentes.iterator(); it.hasNext() && !find; )
        {
            adAux = (Adjacente) it.next();
            find = adAux.getIndice() == indice;
        }
        
        if( !find ) return adjacentes.add(ad.clone()); 
        else return false;
    }
    
    public double getDist( int indice )
    {
        Adjacente ad = this.getAdjacente(indice);
        return ad.getDist();
    }
    
    
    //iterator
    public Iterator<Adjacente> getIterator()
    {
        return adjacentes.iterator();
    }
    
    
    //Clone
    public Adjacentes clone()   { return new Adjacentes(this); }
    
    
    // Equals
    public boolean Equals(Adjacentes a)
    {
        boolean res;
        
        if(this == a) 
            res = true;
            
        if(a== null || this.getClass()!=a.getClass())
            res = false;
            
        Adjacentes aA = (Adjacentes) a;
        res = ( adjacentes.equals(aA.adjacentes) );
        
        return res;    
    }
    
    //toString
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("\nTree: ");
    
        adjacentes = new TreeSet<Adjacente>();
        for(Adjacente adj : this.adjacentes)
            str.append( adj +", ");        
        
        return str.toString();
    }
}
