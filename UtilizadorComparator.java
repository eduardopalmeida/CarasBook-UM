/**
 * Comparator para a class Utilizador
 */

import java.util.Comparator;

class UtilizadorComparator implements Comparator<Utilizador>
{  
    public int compare(Utilizador ut1, Utilizador ut2){
       
           return ut1.getNome().compareTo(ut2.getNome());
    }
   
}