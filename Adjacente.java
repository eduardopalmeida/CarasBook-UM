
/**
 * Indice e distancia de uma localizacao adjacente a outra.
 * 
 * @author Ana|Bruno|Eduardo 
 */
import java.io.*;
public class Adjacente implements Serializable
{
    //VARIAVEIS DE INSTANCIA
    private int indice;
    private double dist;
    
    //METODOS DE INSTANCIA
    
    //Construtores
    /**
     * Construtor por omissao
      */
    public Adjacente() {
        indice = 0;
        dist = 0.0;
    }
    
    /**
     * Construtor para clone
     */
    public Adjacente( Adjacente ad) 
    {
        indice = ad.getIndice();
        dist = ad.getDist();
    }
    
    /**
     * Construtor por partes
     */
    public Adjacente( int indice, double dist )
    {
        this.indice = indice;
        this.dist = dist;
    }
    
    //Gets
    public int getIndice()  { return indice; }
    public double getDist() { return dist; }
    
    //Sets
    public void setIndice( int indice ) { this.indice = indice; }
    public void setDist( double dist )  { this.dist = dist; }
  
    //Clone
    public Adjacente clone()    { return new Adjacente(this); }
    
    //toString
    public String toString()    { return ("\nIndice: " + indice + "\tDistancia:" + dist ); }
    
    //equals
    public boolean equals( Object ad)
    {
        if (this == ad) return true;
        if ( (ad != null) || (this.getClass() != ad.getClass()) )
            return false;
            
        Adjacente aux = (Adjacente) ad;
        return ( (indice == aux.getIndice()) && (dist == aux.getDist()) );
    }
   
}
