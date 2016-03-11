
/**
 * Classe responsavel pelas mensagens do mural e pelas notificacoes
 * 
 * @author Ana|Bruno|Eduardo
 * @version 18.Maio.2011
 */

import java.lang.*;
import java.util.*;
import java.io.*;

public class Mencacao implements Serializable
{
    // Variaveis de Instancia
    private String texto;
    private String deUtilizador; 
    private GregorianCalendar data;
    
    //Metodos de instancia
    // Constructores
   
    /**
     * Construtor por omissao
     */
    public Mencacao()
    {
        texto = "";
        deUtilizador = "";
        data = new GregorianCalendar();
    }
    
     /**
     * Construtor por partes
     */
    public Mencacao(String texto, String deUtilizador, GregorianCalendar data)
    {
        this.texto = texto;
        this.deUtilizador = deUtilizador;
        this.data = (GregorianCalendar) data.clone(); //??????????
    }
    
    /**
     * Construtor para clone
     */
    public Mencacao( Mencacao m )
    {
        texto = m.getTexto();
        deUtilizador = m.getDeUtilizador();
        data = m.getData();
    }
    
    // Gets
    public String getTexto()            { return texto;        }
    public String getDeUtilizador()     { return deUtilizador; }
    public GregorianCalendar getData()  { return (GregorianCalendar) data.clone(); }
    
    // Set's
    public void setTexto( String texto )                { this.texto = texto; }
    public void setDeUtilizador( String deUtilizador )  { this.deUtilizador = deUtilizador; }
    public void setData( GregorianCalendar data )       { this.data = (GregorianCalendar) data.clone(); }
    
    // Clone
    public Mencacao clone() { return new Mencacao(this); }
    
    //toString
    public String toString() 
    {
        StringBuilder str = new StringBuilder();
        str.append("<html>De: " + deUtilizador);
        str.append("<br> " + texto + "<br>Data: ");
        str.append(data.get(Calendar.DAY_OF_MONTH) +  "-" + data.get(Calendar.MONTH) + "-" + data.get(Calendar.YEAR) + "</html>");
        str.append(data.toString());
        return str.toString();
    }
    
    // Métodos
    
    
    // Equals
    public boolean equals( Object ob )
    {
       if ( this == ob) return true;
       if ( ob == null || this.getClass() != ob.getClass() )
           return false;
            
       Mencacao aux = (Mencacao) ob;
       return ( this.texto.equals(aux.getTexto()) &&
       this.deUtilizador.equals(aux.getDeUtilizador()) &&
       this.data.equals(aux.getData()) );
    }
}