
/**
 * Guarda as coordenadas geograficas (latitude e longitude) de um dado local
 * O nome desse local deve ser unico
 * 
 * @author Ana|Bruno|Eduardo
 */

import java.lang.Math;
import java.io.*;

public class Localizacao implements Serializable
{
    //VARIAVEIS DE INSTANCIA 
    private String nome;
    private int latG;
    private double latM;
    private int lonG;
    private double lonM;
    
    //METODOS
    /**
     * Construtor por omissao
     */
    public Localizacao()
    {
        nome = "";
        latG = 0;
        latM = 0.0;
        lonG = 0;
        lonM = 0.0;
    }
    
    /**
     * Construtor para clone
     */
    public Localizacao( Localizacao loc )
    {
        nome = loc.getNome();;
        latG = loc.getLatG();
        latM = loc.getLatM();
        lonG = loc.getLonG();
        lonM = loc.getLonM();
    }
    
    /**
     * Construtor por partes
     */
    public Localizacao( String nome, int latG, double latM, int lonG, double lonM ) 
    {
        this.nome = nome;
        this.latG = latG;
        this.latM = latM;
        this.lonG = lonG;
        this.lonM = lonM;
    }
    
    //Gets
    public String getNome() { return nome; }
    public int getLatG()    { return latG; }
    public double getLatM() { return latM; }
    public int getLonG()    { return lonG; }
    public double getLonM() { return lonM; }

    //Sets
    public void setNome ( String nome )     { this.nome = nome; }
    public void setLatG ( int latG )        { this.latG = latG; }
    public void setLatM ( double latM )     { this.latM = latM; }
    public void setLonG ( int lonG )        { this.lonG = lonG; }
    public void setLonM ( double lonM )     { this.lonM = lonM; }
    
    //Clone
    public Localizacao clone () { return new Localizacao (this); }

    // Equals
    public boolean Equals(Localizacao l)
    {
        boolean res;
        
        if(this == l) 
            res = true;
            
        if(l== null || this.getClass()!=l.getClass())
            res = false;
            
        Localizacao aL = (Localizacao) l;
        res = ( nome.equals(aL.nome) &&
                (latG == aL.latG) && 
                (latM == aL.latM) && 
                (lonG == aL.lonG) && 
                (lonM == aL.lonM)
              );
        
        return res;    
    }
    
    //toString
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append( this.nome  );
        str.append("\nLatitude: " + this.latG +" o " + this.latM + "'" );
        str.append("\nLongitude " + this.lonG +" o " + this.lonM + "'" );
    
        return str.toString();
    }
    
}
