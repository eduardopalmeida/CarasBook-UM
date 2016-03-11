import javax.swing.*;
import java.awt.*;
import java.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.io.*;

class Login extends JFrame {
    private static final long serialVersionUID = 123456789;


    private CarasBook cb2;
    
    public Login() {
        
        Toolkit tk = Toolkit.getDefaultToolkit();  
        int w = ((int) tk.getScreenSize().getWidth());  
        int h = ((int) tk.getScreenSize().getHeight());
        
        setTitle ("CarasBook@UM");
        setSize (450,250);
        int x = (w-450)/2;
        int y = (h-250)/2;
        setLocation (x,y);

        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );
        getContentPane().add( topPanel );
        
        //Componentes tab para Login
        final JPanel tabLogin = new JPanel();
        tabLogin.setLayout (null);
        JLabel lblEmailL = new JLabel( "Email:" );
        lblEmailL.setBounds( 10, 15, 150, 20 );
        tabLogin.add( lblEmailL );
        final JTextField txtEmailL = new JTextField ();
        txtEmailL.setBounds( 100, 16, 150, 20);
        tabLogin.add ( txtEmailL );
        JLabel lblPassL = new JLabel ( "Password:");
        lblPassL.setBounds (10, 60, 150, 20);
        tabLogin.add ( lblPassL );
        final JPasswordField txtPassL = new JPasswordField ();
        txtPassL.setBounds ( 100, 61, 150, 20);
        tabLogin.add (txtPassL);
        JButton btnLogin = new JButton ("Login");
        btnLogin.setBounds ( 300, 40, 80, 20);
        tabLogin.add (btnLogin);
        final JLabel lblErro = new JLabel ("");
        lblErro.setBounds (90,80,350,20);
        tabLogin.add (lblErro);
        
 
        
        
        
        Mapa mapa = new Mapa();
        CarasBook cb = new CarasBook();
        
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
    
        Individual ut1 = new Individual( "Eduardo", 
                                        "eduardopalmeida@hotmail.com", 
                                        "Sou eu", 
                                        braga,
                                        "password",
                                        "estudante",
                                        "nickas",
                                        porto
                                   );
        Individual ut2 = new Individual(    "Ana", 
                                        "aao_costa@hotmail.com", 
                                       "kakakakaka", 
                                        porto,
                                        "duduzinho",
                                        "estudante",
                                        "nickas",
                                        porto
                                        );
        Individual ut3 = new Individual(    "Bruno", 
                                        "brunotiago@hotmail.com", 
                                        "Sou o maior!", 
                                        guim,
                                        "nomedaminhanamorada",
                                        "estudante",
                                        "nickas",
                                        porto
                                   );
        Individual ut4 = new Individual( "a", 
                                        "a", 
                                        "aaaaaaaaaaaaaaaaaaaa", 
                                        braga,
                                        "a",
                                        "estudante",
                                        "nickas",
                                        porto
                                   );
        Empresa e1 = new Empresa(    "empresa", 
                                        "empresa", 
                                        "aaaaaaaaaaaaaaaaaaaa", 
                                        braga,
                                        "a",
                                        "binho"
                                   );
        Associacao a1 = new Associacao(    "assoc", 
                                        "assoc", 
                                        "aaaaaaaaaaaaaaaaaaaa", 
                                        braga,
                                        "a",
                                        1.0
                                   );
        
        
    GregorianCalendar d1 = new GregorianCalendar(2011, Calendar.APRIL, 10, 23, 15);
    GregorianCalendar d2 = new GregorianCalendar(2011, Calendar.MARCH, 11, 11, 25);
    GregorianCalendar d3 = new GregorianCalendar(2011, Calendar.JUNE, 30, 16, 59);
    GregorianCalendar d4 = new GregorianCalendar(2011, Calendar.JULY, 25, 7, 30);

        
    Mencacao m1 =  new Mencacao("Ola! tas bom?", "eduardopalmeida@hotmail.com", d1);
    Mencacao m2 =  new Mencacao("Pois! assim a vida...", "aao_costa@hotmail.com", d2);
    
    Mencacao n1 = new Mencacao("1 Pedido de Amizade!", "brunotiago@hotmail.com", d3);
    Mencacao n2 = new Mencacao("Ana Costa comentou a sua foto!", "aao_costa@hotmail.com", d4);
    
    ut4.addMural(m1);
    ut4.addMural(m2);
    
    ut4.addNotificacoes(n1);
    ut4.addNotificacoes(n2);
    
    ut4.addAmigo("eduardopalmeida@hotmail.com");
    ut1.addAmigo("a");
    ut4.addAmigo("empresa");
    e1.addAmigo("a");
    ut4.addAmigo("assoc");
    a1.addAmigo("a");
    ut1.addAmigo("brunotiago@hotmail.com");
    ut3.addAmigo("eduardopalmeida@hotmail.com");
    
    cb.addUtilizador(ut1);
    cb.addUtilizador(ut2);
    cb.addUtilizador(ut3);
    cb.addUtilizador(ut4);
     cb.addUtilizador(e1);
     cb.addUtilizador(a1);
    
    //cb.getUtilizadores().get("a").addAmigo("empresa");
    //cb.getUtilizadores().get("a").addAmigo("assoc");
        final Mapa mp = new Mapa(mapa);
        cb2 = cb;
        ActionListener paraLogin = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                
                Utilizador log;
               
                log = cb2.login( txtEmailL.getText(), txtPassL.getPassword().toString());
               
                
                if ( log != null ) { FrontGUI fr = new FrontGUI(log.getEmail(),cb2, mp); fr.setVisible(true); setVisible(false); }
                else lblErro.setText("Email e/ou Password nao sao validos!");
            
            }
        };
        
        btnLogin.addActionListener (paraLogin);
 
        //Componentes tab para Registo
        JPanel tabRegisto = new JPanel();
        tabRegisto.setLayout (null);
        JLabel lblEmailR = new JLabel( "Email:" );
        lblEmailR.setBounds( 10, 15, 150, 20 );
        tabRegisto.add( lblEmailR );
        JTextField txtEmailR = new JTextField ();
        txtEmailR.setBounds( 100, 16, 150, 20);
        tabRegisto.add ( txtEmailR );
        JLabel lblPassR = new JLabel ( "Password:");
        lblPassR.setBounds (10, 60, 150, 20);
        tabRegisto.add ( lblPassR );
        JPasswordField txtPassR = new JPasswordField ();
        txtPassR.setBounds ( 100, 61, 150, 20);
        tabRegisto.add (txtPassR);
        JButton btnRegisto = new JButton ("Registo");
        btnRegisto.setBounds ( 300, 40, 80, 20);
        tabRegisto.add (btnRegisto);
        
        //Ler de ficheiro
        JButton btnLer = new JButton ("Ler do ficheiro");
        btnLer.setBounds(200, 150, 200, 20);
        
        ActionListener paraLer = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                
                try {
                        ObjectInputStream ois = new ObjectInputStream( 
                        new FileInputStream("carasbook.dat"));
                        cb2 = (CarasBook) ois.readObject();
                    }
                    catch(IOException e) {  JOptionPane.showMessageDialog(tabLogin,e.getMessage()); }
                    catch(ClassNotFoundException e) { JOptionPane.showMessageDialog(tabLogin,e.getMessage()); }
            }
        };
        btnLer.addActionListener(paraLer);
        tabLogin.add (btnLer);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Login", tabLogin);
        tabs.addTab("Registo", tabRegisto);
        
        
        
        topPanel.add( tabs, BorderLayout.CENTER );
        
    }

    
    public static void main (String[] args) {
        
        JFrame a = new Login();
        a.setVisible(true);
        
    }

}  
