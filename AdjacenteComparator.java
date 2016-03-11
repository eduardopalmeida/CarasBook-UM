
/**
 * Comparator para a class Adjacente
 */

import java.util.Comparator;

class AdjacenteComparator implements Comparator<Adjacente>
{  
    public int compare(Adjacente adj1, Adjacente adj2){
       
        int indice1 = adj1.getIndice();
        int indice2 = adj2.getIndice();    
       
        if(indice1 > indice2)
            return 1;
        else if(indice1 < indice2)
            return -1;
        else
            return 0;    
    }
   
}

