/**
 * Classe para guardar e gerir os dados de cada utilizador.
 * 
 * @author Ana|Bruno|Eduardo
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Utilizador implements Serializable
{
    //VARIAVEIS DE INSTANCIA
    private String email;
    private String nome;
    private String descricao;
    private Localizacao morada;
    private Stack<Mencacao> mural;
    private Stack<Mencacao> notificacoes;
    private String password;
    private TreeSet<String> amigos;
    
     //METODOS DE INSTANCIA
    /**
     * Construtor por omissao
     */
    public Utilizador () 
    {
        this.email = "";
        this.nome = "";
        this.descricao = "";
        this.morada = new Localizacao();
        this.mural = new Stack<Mencacao>();
        this.notificacoes = new Stack<Mencacao>();
        this.password = "";
        this.amigos = new TreeSet<String>();  
    }
    
    /**
     * Construtor para clone
     */
    public Utilizador ( Utilizador u ) 
    {
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.descricao = u.getDescricao();
        this.morada = u.getMorada();
        this.mural = u.getMural();
        this.notificacoes = u.getNotificacoes();
        this.password = u.getPassword();
        this.amigos = u.getAmigos();       
    }
    
    /**
     * Construtor por partes
     */
    public Utilizador (String cNome, String cEmail, String cDescricao, Localizacao cMorada, Stack<Mencacao> cMural,
                       Stack<Mencacao> cNotificacoes, String cPassword, TreeSet<String> cAmigos) 
    {    
        this.nome = cNome;
        this.email = cEmail;
        this.descricao = cDescricao;
        this.morada = cMorada;
        setMural( cMural );
        setNotificacoes( cNotificacoes );
        this.password = cPassword;
        setAmigos( cAmigos );
    }
    
    /**
     * Construtor sem Mencacoes e Amigos
     */
    public Utilizador (String cNome, String cEmail, String cDescricao, Localizacao cMorada, String cPassword) 
    {    
        this.nome = cNome;
        this.email = cEmail;
        this.descricao = cDescricao;
        this.morada = cMorada;
        this.mural = new Stack<Mencacao>();
        this.notificacoes = new Stack<Mencacao>();
        this.password = cPassword;
        this.amigos = new TreeSet<String>();
    }
    
   
    //Gets
   
    public String getEmail ()                       { return this.email;                }
    public String getNome ()                        { return this.nome;                 }
    public String getDescricao ()                   { return this.descricao;            }
    public Localizacao getMorada ()                 { return this.morada.clone();       }
    public String getPassword()                     { return this.password;             }
    
    public Stack<Mencacao> getNotificacoes()     
    {
        Stack<Mencacao> aux = new Stack<Mencacao>();
        copyStackTo (this.notificacoes, aux);
        return aux;
    }
   
    public Stack<Mencacao> getMural() 
    {
       Stack<Mencacao> aux = new Stack<Mencacao>();
       copyStackTo (this.mural, aux);
       return aux;
    }
    
    public TreeSet<String> getAmigos()
    {
        TreeSet<String> aux = new TreeSet<String>();
        for(String str: amigos)
            aux.add(str);
        return aux;
    }
    
    //Sets
    
    public void setEmail ( String email )                           { this.email = email;                           }
    public void setNome ( String nome )                             { this.nome = nome;                             }
    public void setDescricao ( String descricao )                   { this.descricao = descricao;                   }
    public void setMorada ( Localizacao morada )                    { this.morada = morada;                         }
    public void setMural ( Stack<Mencacao> cMural )                 { if(mural == null) mural = new Stack<Mencacao>();
                                                                        copyStackTo ( cMural, mural );                }
    public void setNotificacoes ( Stack<Mencacao> cNotificacoes )   { if(notificacoes == null) notificacoes = new Stack<Mencacao>();
                                                                        copyStackTo ( cNotificacoes, notificacoes );  }
    
    public void setAmigos ( TreeSet<String> amigos )    
    {
        this.amigos = new TreeSet<String>();
        for(String str: amigos)
            this.amigos.add(str);    
    }
    
    /**
     * Copia a primeira stack para a segunda
     * 
     * @param sA Stack a copiar
     * @param sB Stack que recebe a copia
     */
    public void copyStackTo ( Stack<Mencacao> sA, Stack<Mencacao> sB )
    {
        Mencacao elem;
        Stack<Mencacao> aux1 = new Stack<Mencacao>();
     
        if(sB!=null)
        {
            int size = sA.size();
            while( size > 0 ) 
            {
                aux1.push(sA.pop());
                size --;
            }
        
            size = aux1.size();
            while(size > 0)
            {
                elem = aux1.pop();
                sB.push(elem.clone());
                sA.push(elem.clone());
                size --; 
            }
        }
    }
    
    /**
     * Adiciona uma notificacao
     */
    public void addNotificacoes ( Mencacao cMencacao )  { this.notificacoes.push ( cMencacao.clone() ); }
    
    /**
     * Adiciona uma mensagem no mural
     */
    public void addMural ( Mencacao cMencacao )         { this.mural.push ( cMencacao.clone() );        }
    
    /**
     * Adiciona um amigo
     */
    public void addAmigo(String emailA)                 { amigos.add(emailA);                           }
    
    /**
     * Remove um amigo
     */
    public boolean remAmigo ( String email)             { return( amigos.remove(email) );               }
    
    //equals
    public boolean equals ( Object ob )
    {
        if (this == ob) return true;
        if( ob == null || this.getClass() != ob.getClass() )
            return false;
        
        Utilizador ut = (Utilizador) ob;
        return ( this.nome.equals( ut.getNome() ) &&
                 this.email.equals( ut.getEmail() ) &&
                 this.descricao.equals( ut.getDescricao() ) &&
                 this.morada.equals( ut.getMorada() ) &&
                 this.amigos.equals( ut.getAmigos() ) &&
                 this.notificacoes.equals( ut.getNotificacoes() ) &&
                 this.mural.equals( ut.getMural() ) &&
                 this.password.equals( ut.getPassword() ) );
    }
    
    //Clone
    public Utilizador clone () { return new Utilizador (this); }
    
    //toString
    public String toString () {
        StringBuilder str = new StringBuilder();
        str.append("\nNome: " + this.nome );
        str.append("\nEmail: " + this.email );
        str.append("\nDescricao: " + this.descricao );
        str.append("\nMorada: " + this.morada.toString() );
        str.append("\nAmigos: " + this.amigos.toString() );
        str.append("\nMural: " + this.mural.toString() );
        str.append("\nNotificacoes: " + this.notificacoes.toString() +"\n" );
        return str.toString();
    }
}