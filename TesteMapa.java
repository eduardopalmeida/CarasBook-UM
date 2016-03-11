import java.lang.*;
import java.util.*;

public class TesteMapa
{
     static void main(String args[])
     {
         Mapa mapa = new Mapa();
         ArrayList<Localizacao> caminho;
         
         Localizacao porto      = new Localizacao("Porto",41,11,8,36);
         Localizacao braga      = new Localizacao("Braga",41,33,8,26);
         Localizacao guim       = new Localizacao("Guimaraes",41,27,8,18);
         Localizacao barcelos   = new Localizacao("Barcelos",41,32,8,37);
         Localizacao famalicao  = new Localizacao("Famalicao", 41,25,8,32);
         Localizacao amares     = new Localizacao("Amares", 41,38,8,21);
         
         mapa.addLocalizacao(porto);
         mapa.addLocalizacao(braga);
         mapa.addLocalizacao(guim);
         mapa.addLocalizacao(barcelos);
         mapa.addLocalizacao(famalicao);
         mapa.addLocalizacao(amares);
         
         mapa.addAdjacenteL( porto, braga );
         mapa.addAdjacenteL( guim, braga );
         mapa.addAdjacenteL( barcelos, braga );
         mapa.addAdjacenteL( porto, famalicao );
         mapa.addAdjacenteL( amares, braga );
         mapa.addAdjacenteL( famalicao, barcelos );
         mapa.addAdjacenteL( amares, barcelos );
         mapa.addAdjacenteL( famalicao, guim );
         
         System.out.println(mapa.toString());
         
         caminho = mapa.caminhoMaisCurto( porto, guim );
         
         System.out.println(caminho.toString());
         
         //caminho = mapa.caminhoMaisCurto( porto, amares );
         
         //System.out.println(caminho.toString());
         
         // caminho = mapa.caminhoMaisCurto( porto, barcelos );
         
         // System.out.println(caminho.toString());
     }
}
