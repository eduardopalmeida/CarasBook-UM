import java.lang.*;
import java.util.*;
import java.io.*;

public class TesteUtilizador
{

   static void main(String args[])
   {
    
    CarasBook cb = new CarasBook();
    Mapa mapa = new Mapa();
    
    try {
           ObjectInputStream ois = new ObjectInputStream( 
               new FileInputStream("carasbook.dat"));
           cb = (CarasBook) ois.readObject();
    }
    catch(IOException e) { System.out.println(e.getMessage()); }
    catch(ClassNotFoundException e) { System.out.println(e.getMessage()); }
      
    GregorianCalendar d1 = new GregorianCalendar(2011, Calendar.APRIL, 10, 23, 15);
    GregorianCalendar d2 = new GregorianCalendar(2011, Calendar.MARCH, 11, 11, 25);
    GregorianCalendar d3 = new GregorianCalendar(2011, Calendar.JUNE, 30, 16, 59);
    GregorianCalendar d4 = new GregorianCalendar(2011, Calendar.JULY, 25, 7, 30);

        
    Mencacao m1 =  new Mencacao("Ola! tas bom?", "eduardopalmeida@hotmail.com", d1);
    Mencacao m2 =  new Mencacao("Pois! assim a vida...", "aao_costa@hotmail.com", d2);
    
    Mencacao n1 = new Mencacao("1 Pedido de Amizade!", "brunotiago@hotmail.com", d3);
    Mencacao n2 = new Mencacao("Ana Costa comentou a sua foto!", "aao_costa@hotmail.com", d4);
    
    Localizacao porto      = new Localizacao("Porto",       41,11,8,36);
    Localizacao braga      = new Localizacao("Braga",       41,33,8,26);
    Localizacao guim       = new Localizacao("Guimaraes",   41,27,8,18);
    Localizacao barcelos   = new Localizacao("Barcelos",    41,32,8,37);
    Localizacao famalicao  = new Localizacao("Famalicao",   41,25,8,32);
    Localizacao amares     = new Localizacao("Amares",      41,38,8,21);
         
    mapa.addLocalizacao(porto);
    mapa.addLocalizacao(braga);
    mapa.addLocalizacao(guim);
    mapa.addLocalizacao(barcelos);
    mapa.addLocalizacao(famalicao);
    mapa.addLocalizacao(amares);
    

    Utilizador ut1 = new Utilizador(    "Eduardo", 
                                        "eduardopalmeida@hotmail.com", 
                                        "Sou eu", 
                                        braga,
                                        "password"
                                   );
    Utilizador ut2 = new Utilizador(    "Ana", 
                                        "aao_costa@hotmail.com", 
                                       "kakakakaka", 
                                        porto,
                                        "duduzinho"
                                   );
    Utilizador ut3 = new Utilizador(    "Bruno", 
                                        "brunotiago@hotmail.com", 
                                        "Sou o maior!", 
                                        guim,
                                        "nomedaminhanamorada"
                                   );
    cb.addUtilizador(ut1);
    cb.addUtilizador(ut2);
    cb.addUtilizador(ut3);

    System.out.println( ut1.toString() +ut2.toString() +ut3.toString()  );
        
        FrontGUI a = new FrontGUI(ut2);
        a.show();
     
    //Gravar o ficheiro "carasbook.dat"    
    try 
     {
           cb.gravaObj("carasbook.dat");
     }
     catch(IOException e) { System.out.println(e.getMessage()); }
    }                                           
   }
   
