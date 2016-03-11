
/**
 * Classe onde serao guardados os utilizadores do CarasBook@UM
 * 
 * @author Ana|Bruno|Eduardo
 */

import java.util.*;
import java.io.*;

public class CarasBook implements Serializable
{
    //VARIAVEIS DE INSTANCIA
    private TreeMap<String,Utilizador> utilizadores;

    //METODOS DE INSTANCIA
    /**
     * Constructor por omissao
     */
    public CarasBook()
    {
        utilizadores = new TreeMap<String, Utilizador>();
    }
    
    /**
     * Constructor para clone
     */
    public CarasBook( CarasBook cb )
    {
        utilizadores = new TreeMap<String, Utilizador>();
        utilizadores = cb.getUtilizadores();
    }
    
    // Gets
    public TreeMap<String,Utilizador> getUtilizadores()
    {
        TreeMap<String, Utilizador> aux = new TreeMap<String,Utilizador>();
        Set<String> keys = utilizadores.keySet();
        for(String k: keys)
            aux.put(k,utilizadores.get(k).clone());
        return aux;
    }
    
    
    //Sets
    public void setUtilizadores(TreeMap<String,Utilizador> ut)
    {
        Set<String> keys = ut.keySet();
        for(String k: keys)
            utilizadores.put(k, ut.get(k).clone());
    }
    
    
    public Utilizador login( String email, String password )
    {
        if( utilizadores.get(email) != null )
        {
            if (utilizadores.get(email).getPassword().equals(password))
                return utilizadores.get(email).clone();
            else return null;
        }
        else return null;
    }
    
    public ArrayList<Utilizador> giveUtilizadores()
    {
        ArrayList<Utilizador> uts = new ArrayList<Utilizador>();
        for(String str: utilizadores.keySet())
            uts.add(utilizadores.get(str).clone());
        Collections.sort(uts, new UtilizadorComparator());
        return uts;
    }
   
    
    /**
     * 
     */
    public Utilizador get(String str) { return utilizadores.get(str); }
    
    /**
     * Adiciona um utilizador dado um utilizador
     */
    
    public boolean addUtilizador( Utilizador ut )
    {
        if (utilizadores.keySet().contains(ut.getEmail()))
            return false;
        else
        {
            utilizadores.put(ut.getEmail(), ut.clone());
            return true;
        }
    }
    

    /**
     * Envia um pedido de amizade 
     * 
     * @param emailU e-mail do utilizador a que se esta a pedir amizade
     * @param emailA e-amil do utilizador que pediu a amizade
     */
    public void envPedAmig( String emailU, String emailA )
    {
        Mencacao m = new Mencacao("Pedido de amizade! :)", emailA, new GregorianCalendar());
        utilizadores.get(emailU).addNotificacoes(m);
        ((Individual)utilizadores.get(emailU)).addPedido(emailA);
    }    
   
    /**
     * Calcula nivel entre dois utilizadores
     */
    public int nivelEntre( String email1, String email2)
    {
        TreeSet<String> auxTree1 =  utilizadores.get(email1).getAmigos();
        TreeSet<String> auxTree2 = new TreeSet<String>() ;
        boolean find = false;
        int nivel = 1;
        
        while( !find )
        {
            if( auxTree1.contains(email2))
                find = true;
            else if(auxTree1.isEmpty()) { find = true; nivel = -1; }
            else 
            {
                nivel ++;
                auxTree2.addAll(auxTree1);
                auxTree1.clear();
                for(String str: auxTree2)
                    auxTree1.addAll(utilizadores.get(str).getAmigos());
            }
        }
        return nivel;
    }
    /**
     * Adiciona mensagem no proprio mural e no dos amigos
     */
    
    public void addMuralU ( String emailU, Mencacao m )         
    { 
        utilizadores.get(emailU).addMural(m);
    
        for( String str : utilizadores.get(emailU).getAmigos() )
        {
            utilizadores.get(str).addMural(m);
        }
    }
    
    //Clone
    public CarasBook clone() { return  new CarasBook(this); }
    
    //toString
    public String toString() 
    {
        StringBuilder str = new StringBuilder();
        Set<String> keys = utilizadores.keySet();
        for(String k: keys)
            str.append("\n" + utilizadores.get(k).toString() + "\n");
        return str.toString();
    }
    
   
    //Equals
    public boolean equals( Object ob )
    {
       if ( this == ob) return true;
       if ( ob == null || this.getClass() != ob.getClass() )
            return false;
            
       CarasBook aux = (CarasBook) ob;
       return ( this.utilizadores.equals(aux.getUtilizadores()) );
    }
    
    //GravaObjs
    public void gravaObj(String fich) throws IOException 
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush(); oos.close();
    }
}
