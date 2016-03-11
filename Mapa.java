
/**
 * Classe onde se organiza a informacao relativa as localizacoes
 * e aos caminhos. 
 * 
 * @author Ana|Bruno|Eduardo 
 */

import java.lang.*;
import java.io.*;
import java.util.*;

public class Mapa implements Serializable 
{
    //VARIAVEIS DE INSTANCIA
    ArrayList<Localizacao> localizacoes;
    ArrayList<Adjacentes> caminhos;
    
    //METODOS DE INSTANCIA
    //Construtores
    /**
     * Construtor por omissao
     */
    public Mapa()
    {
        localizacoes =  new ArrayList<Localizacao>();
        caminhos = new ArrayList<Adjacentes>();
    }
    
    /**
     * Construtor para clone 
     */
    public Mapa( Mapa mp )
    {
        localizacoes = mp.getLocalizacoes();
        caminhos = mp.getCaminhos();
    }
    
    /**
     * Construtor por partes
     */
    public Mapa( ArrayList<Localizacao> localizacoes, ArrayList<Adjacentes> caminhos )
    {
        setLocalizacoes( localizacoes );
        setCaminhos( caminhos );
    }
    
    
    //Sets
    public void setLocalizacoes( ArrayList<Localizacao> localizacao )
    {
        this.localizacoes = new ArrayList<Localizacao>();
        for(Localizacao loc: localizacoes)
            this.localizacoes.add(loc.clone());
    }
    
    public void setCaminhos( ArrayList<Adjacentes> caminhos )
    {
        this.caminhos = new ArrayList<Adjacentes>();
        for(Adjacentes adj: caminhos)
            this.caminhos.add(adj.clone());
    }
    
    //Gets
    public ArrayList<Localizacao> getLocalizacoes( )
    {
        ArrayList<Localizacao> aux = new ArrayList<Localizacao>();
        for(Localizacao loc: localizacoes)
            aux.add(loc.clone());
        return aux;
    }
    
    public ArrayList<Adjacentes> getCaminhos( )
    {
        ArrayList<Adjacentes> aux = new ArrayList<Adjacentes>();
        for(Adjacentes adj: caminhos)
            aux.add(adj.clone());
        return aux;
    }
    
    /**
     * Verifica se a localizacao ja existe pelo nome
     */
    public boolean containsLocalizacao( Localizacao loc )
    {
        boolean find =  false;
        String nome = loc.getNome();
        Localizacao auxLoc;
        for(Iterator it = localizacoes.iterator(); it.hasNext() && !find; )
        {
            auxLoc = (Localizacao) it.next();
            find = nome.equals(auxLoc.getNome());
        }
        
        return find;
    }
    
    /**
     * Adiciona uma localizacao ha lista de localizacoes
     * 
     * @return Um boleano que indica se correu bem a insercao da nova localizacao
     */
    public boolean addLocalizacao( Localizacao loc )   
    { 
        boolean res = true;
        if( !containsLocalizacao(loc) )
        {
            res = localizacoes.add(loc.clone()); 
            //por cada nova localizacao criar os adjacentes respectivos
            caminhos.add(new Adjacentes());
            return true;
        }
        else return false;
    }
    
    
    /**
     * Muda a lista de adjacentes de uma dada localizacao
     * 
     * @param indice Indice da localizacao na lista de localizacoes
     * @param adj Nova lista de adjacentes
    */
    public boolean setAdjacentesDe( int indice, Adjacentes adj )        
    { 
        Adjacentes aux = new Adjacentes(adj);
        if (indice < localizacoes.size())
        {
            caminhos.set( indice, aux );
            return true;
        }
        else
            return false;
            
    }
    
    /**
     * Cria um caminho entre dois adjacentes
     *
     * @return Boleano que o caminho foi bem criado
     */
    public boolean addAdjacente( Adjacente ad1, Adjacente ad2 )
    {
        Adjacente aux1 = ad1.clone();
        Adjacente aux2 = ad2.clone();
        return ( caminhos.get(aux2.getIndice()).addAdjacente(aux1)
                && caminhos.get(aux1.getIndice()).addAdjacente(aux2) );
    }
    
    /**
     * Cria um caminho entre os dois indices dados
     */
    public void addAdjacenteI( int indice1, int indice2 )
    {
        Localizacao loc1 = localizacoes.get(indice1).clone();
        Localizacao loc2 = localizacoes.get(indice2).clone();
        double dist = calcDist(loc1, loc2);
        Adjacente aux1 = new Adjacente(indice1, dist);
        Adjacente aux2 = new Adjacente(indice2, dist);
        this.addAdjacente(aux1, aux2);
    }
    
    /**
     * Cria um caminho entre as duas localizacoes
     * 
     */
    public void addAdjacenteL( Localizacao loc1, Localizacao loc2  )
    {
        double dist = calcDist(loc1, loc2);
        Adjacente aux1= new Adjacente(getIndice(loc1), dist);
        Adjacente aux2= new Adjacente(getIndice(loc2), dist);
        addAdjacente(aux1, aux2);
    }
    
    /**
     * Devolve uma localizacao dado o indice no ArrayList de Localizacoes
     * 
     * @param indice Indice em Localizacoes
     * @return Localizacao respectiva
     */
    public Localizacao getLocalizacao( int indice )
    {
        return localizacoes.get(indice).clone();
    }
    
    /**
     * Devolve o indice de uma dada localizacao
     * 
     * @return -1 se a localizacao nao pertence a lista de localizacoes
     */
    public int getIndice( Localizacao loc )
    {
        String nome = loc.getNome();
        return getIndice(nome);
    }
    
    /**
     * Devolve o indice de uma dada localizacao
     * 
     * @return -1 se a localizacao nao pertence a lista de localizacoes
     */
    public int getIndice( String nome )
    {
        Localizacao auxLoc;
        boolean find = false;
        int i = -1;
        for(Iterator it = localizacoes.iterator(); it.hasNext() && !find; )
        {
            auxLoc = (Localizacao) it.next();
            find = nome.equals(auxLoc.getNome());
            i++;
        }
        
        if(find) return i;
        else return -1;
    }
    
    /**
     * Realiza o calculo das distancias entre duas Localizacos
     * 
     * @param x Localizacao 
     * @param y Localizacao
     * @return Distancia entre x e y
     */
    public double calcDist(Localizacao x, Localizacao y)
    {
        double d2r = (3.14159265358979323846 / 180.0);
        
        double long1 = x.getLonG() + (x.getLonM() / 60);
        double long2 = y.getLonG() + (y.getLonM() / 60);
        double lat1  = x.getLatG() + (x.getLatM() / 60);
        double lat2  = y.getLatG() + (y.getLatM() / 60);
        
        double dlong = (long2 - long1) * d2r;
        double dlat = (lat2 - lat1) * d2r;
        
        double a = Math.pow(Math.sin(dlat/2.0), 2) + Math.cos(lat1*d2r) 
                 * Math.cos(lat2*d2r) * Math.pow(Math.sin(dlong/2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = 6367 * c;

       return d;
    }
    
    /**
     * Implmentacao do algoritmo de Dijkstra
     */
    
    public ArrayList<Localizacao> caminhoMaisCurto(Localizacao loc1, Localizacao loc2)
    {
        //indices das localizacoes
        int indice1 = getIndice(loc1);
        int indice2 = getIndice(loc2);
        
        int sizeLoc =  localizacoes.size();
        
        //V'
        ArrayList<Integer> v = new ArrayList<Integer>();
        //Orla
        ArrayList<Integer> orla = new ArrayList<Integer>();
        //Candidatos
        ArrayList<Integer> candidatos = new ArrayList<Integer>();
        //T
        ArrayList<Adjacentes> t = new ArrayList<Adjacentes>(sizeLoc);
        //dist
        ArrayList<Double> dist = new ArrayList<Double>(sizeLoc);
        
        //V'={x}
        v.add(indice1);
        //stuck = 0
        boolean stuck = false;
        //x = v
        Integer x = indice1;
        
        //Variaveis auxiliares
        Adjacentes adjAux;
        Localizacao locAux;
        Adjacente adAux, adAux2;
        Double distAux;
        double distMenor;
        Integer menor;
        //Adjacente ad, adjacente;
        Iterator itAux;
        Integer candidato;
        int indice, iC = 0, i;
        
        //inicializacoes
        for(int l = 0; l < sizeLoc; l++)
        {
            dist.add(0.0);
            t.add(new Adjacentes());
        }
            

        while( (x != indice2) && !stuck )
        {
            //Adjacentes de x e distancia percorrida ate agora
            adjAux = caminhos.get(x);
            distAux = dist.get(x);
            locAux = localizacoes.get(x);
            
            //Percorrer a orla
            for(Integer y: orla)
            {
                adAux = adjAux.getAdjacente(y);
                if( adAux != null ) //Y adjacente de X
                {
                    if ( (distAux + adAux.getDist()) < dist.get(y) )
                    {
                        //adiciona aos candidatos
                        candidatos.add(y);
                        dist.set( y, distAux + adAux.getDist() );
                    } 
                }
            }
            
            
            //Adicionar candidatos
            //Tem que pertencer aos adjacente de x
            for (Iterator it = adjAux.getIterator(); it.hasNext(); ) 
            {
                adAux = (Adjacente)it.next();
                indice = adAux.getIndice();
                //Nao pode pertencer a V ou a Orla
                if( !v.contains(indice) && !orla.contains(indice)) 
                {
                    orla.add(indice);
                    candidatos.add(indice);
                    dist.set(indice, distAux + adAux.getDist());
                    adAux2 = new Adjacente(x,dist.get(indice));
                    t.get(indice).addAdjacente(adAux2);
               }
            }
            
            //se nao ha mais candidatos
            if(candidatos.isEmpty()) stuck = true;
            else
            {
               //escolher arco candidato com dist menor
               distMenor = -1;
               menor = 0;
               itAux = candidatos.iterator();
               i = 0;
               while( itAux.hasNext() ) 
               {
                   candidato = (Integer) itAux.next();
                   if(candidato != x)
                   {
                        if(distMenor == -1 || dist.get(candidato) < distMenor)
                        {
                           distMenor = dist.get(candidato);
                           menor = candidato;
                           iC = i;
                        }
                    }
                  i++;
               }
               x = menor;
               v.add(menor);
               candidatos.remove(iC);
            } 
        }
        
        
        //Criar o caminho
        menor  = -1;
        ArrayList<Localizacao> caminho = new ArrayList<Localizacao>();
        
        caminho.add(getLocalizacao(indice2));
        
        for(indice = indice2; indice != indice1; )
        {
            menor = -1;
            for(Iterator it2 = t.get(indice).getIterator(); it2.hasNext(); ) {
                adAux = (Adjacente)it2.next();
                if( adAux.getIndice() != indice)
                {
                    if (menor == -1 || adAux.getDist() < menor)
                    {
                        distMenor = adAux.getDist();
                        menor = adAux.getIndice();
                    }
                }
            }
            caminho.add(getLocalizacao(menor));
            indice = menor; 
        }
        
        return caminho;
    }
    
   //toString
   public String toString()
   {
        StringBuilder str  = new StringBuilder();
        
        Iterator it = localizacoes.iterator();
        Localizacao auxLoc;
        Adjacente ad;
        
        for(Adjacentes adj: caminhos)
        {
            str.append("\n#--------------------------#\n");
            auxLoc = (Localizacao)it.next();
            str.append(auxLoc.toString());
            str.append("\nAdjacentes: ");
            for(Iterator it2 = adj.getIterator(); it2.hasNext(); )
            {
                ad = (Adjacente) it2.next();
                //Nome da localizacao adjacente
                str.append("\n-> " + getLocalizacao(ad.getIndice()).getNome());
                //Distancia
                str.append("\t" + ad.getDist());
            }
            
        }
        return str.toString();
   }
   
   //GravaObjs
    public void gravaObj(String fich) throws IOException 
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush(); oos.close();
    }    
    
    //Clone
    public Mapa clone() { return new Mapa(this);    }
    
    //equals
    public boolean equals( Object mp )
    {
        if(this == mp) 
            return true;
            
        if( mp == null || this.getClass() != mp.getClass() )
            return(false);
            
        Mapa mapa = (Mapa) mp;
        return localizacoes.equals(mapa.getLocalizacoes()) && caminhos.equals(mapa.getCaminhos());   
    }
   
}
