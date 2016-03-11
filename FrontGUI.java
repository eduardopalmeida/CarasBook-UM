import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;


class FrontGUI extends JFrame {
    
    private CarasBook caras;
    private Mapa mp;

    public FrontGUI (String str1, CarasBook cb, Mapa mapa) {
        
        Toolkit tk = Toolkit.getDefaultToolkit();  
        int xSize = ((int) tk.getScreenSize().getWidth());  
        int ySize = ((int) tk.getScreenSize().getHeight());
        
        final String strUt = str1;
        Class clsUt = cb.getUtilizadores().get(strUt).getClass();
        
        Utilizador ut = cb.getUtilizadores().get(strUt);
        mp = mapa;
        caras = cb;
        final String strLoc = ut.getMorada().getNome();

        setTitle("CarasBook@UM");
        setSize(xSize,ySize);
        setLocation(0,0);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        
        final Container conteudo = getContentPane();
        conteudo.setLayout ( null );
       

        //Ver localizacoes
        JButton btnMapa = new JButton ("Ver Localizacoes");
        btnMapa.setBounds ( xSize-350,10,200,20);
        conteudo.add (btnMapa);
        ActionListener paraMapa = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                
                MapaGUI mapaGui = new MapaGUI( "", mp, true );
                mapaGui.show(); 
            }
        };
        btnMapa.addActionListener(paraMapa);
        
        //Convidar utilizadores
        JButton btnUts = new JButton ("Adicionar amigo");
        btnUts.setBounds ( xSize-350,40,200,20);
        conteudo.add (btnUts);
        ActionListener paraUts = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                
                utilGUI utilGui = new utilGUI( strUt , caras, true );
                utilGui.show(); 
            }
        };
        
        btnUts.addActionListener(paraUts);
        
        //Calcular distancia entre uts
        JButton btnDistU = new JButton ("Distancia para utilizador");
        btnDistU.setBounds ( xSize-350,70,200,20);
        conteudo.add (btnDistU);
        ActionListener paraDistU = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                
                utilGUI utilGui = new utilGUI( strUt , caras, false );
                utilGui.show(); 
            }
        };
        
        btnDistU.addActionListener(paraDistU);
        
        
        //Calcular o caminho mais curto
        JButton btnCaminho = new JButton ("Caminho para:");
        btnCaminho.setBounds ( xSize-350,100,200,20);
        conteudo.add (btnCaminho);
        ActionListener paraCaminho = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                
                MapaGUI mapaGui = new MapaGUI(strLoc , mp , false);
                mapaGui.show(); 
            }
        };
        btnCaminho.addActionListener(paraCaminho);
        
        
        //Gravar para ficheiro
        JButton btnGrava = new JButton ("Gravar para ficheiro");
        btnGrava.setBounds ( xSize-350,200,200,20);
        conteudo.add (btnGrava);
        ActionListener paraGrava = new ActionListener() {
            
            public void actionPerformed(ActionEvent paraLogin) {
                try 
                {
                    caras.gravaObj("carasbook.dat");
                }
                catch(IOException e) { JOptionPane.showMessageDialog(conteudo,e.getMessage()); }
            }                                           
        };
        btnGrava.addActionListener(paraGrava);

        //Notificacoes
        JLabel lblnot = new JLabel ("Notificacoes:");
        lblnot.setBounds(xSize-350,300,100,20);
        conteudo.add(lblnot);
        
        JComboBox notific = new JComboBox(ut.getNotificacoes());
        notific.setBounds(xSize-350,320,200,80);
        conteudo.add(notific);
        
        //Simbolo
        JLabel lblCaras = new JLabel ("CarasBook@UM");
        lblCaras.setBounds (20,8,200,50);
        Font tamanho22 = new Font(lblCaras.getFont().getName(),lblCaras.getFont().getStyle(),22);
        lblCaras.setFont(tamanho22);
        conteudo.add (lblCaras);
        
        //Nome
        JLabel lblNome = new JLabel ("Nome: " + ut.getNome());
        lblNome.setBounds (20,70,150,20);
        Font tamanho13 = new Font (lblNome.getFont().getName(),lblNome.getFont().getStyle(),13);
        lblNome.setFont(tamanho13);
        conteudo.add (lblNome);
        
        //Morada
        JLabel lblMorada = new JLabel ("Morada: " + ut.getMorada().getNome());
        lblMorada.setBounds (20,90,150,30);
        conteudo.add (lblMorada);
        
        //Descricao
        JLabel lblDescricao = new JLabel ("Descricao: " + ut.getDescricao());
        lblDescricao.setBounds (270,70,150,30);
        conteudo.add (lblDescricao);
        
        //Mural
        JLabel lblMural = new JLabel ("Mural");
        lblMural.setBounds(270,155,100,20);
        conteudo.add (lblMural);
        final DefaultListModel model1 = new DefaultListModel();
        //limite de mensagens a aparecer no mural
        int limit = 20;
        Iterator it = ut.getMural().iterator();
        Mencacao mur;
        for(int i = 0; i < limit && it.hasNext(); i++)
        {
            mur = (Mencacao)it.next();
            model1.addElement(mur.toString());
            i++;
        }
        
        final JList list1 = new JList(model1);
        JScrollPane scrollingList1 = new JScrollPane(list1);
        scrollingList1.setBounds(270,180,500,500);
        conteudo.add(scrollingList1);
        
        
        //Amigos individuais 
        int i = 0;
        final DefaultListModel model2 = new DefaultListModel();
        Iterator its = ut.getAmigos().iterator();
        String str;
        for(i = 0; its.hasNext();)
        {
            str = (String)its.next();
            Class cls = cb.getUtilizadores().get(str).getClass();
            if(cls.getName().equals("Individual"))
            {
                model2.addElement(cb.getUtilizadores().get(str).getNome());
                i++;
            }
        }
        
        JLabel lblAmigos = new JLabel ("Amigos (" + i +")");
        lblAmigos.setBounds(20,180,100,20);
        conteudo.add (lblAmigos);  
       
        final JList list2 = new JList(model2);
        JScrollPane scrollingList2 = new JScrollPane(list2);
        scrollingList2.setBounds(20,200,200,180);
        conteudo.add(scrollingList2);

        //se for utilizador individual
        if(clsUt.getName().equals("Individual"))
        {
            JLabel lblProfissao = new JLabel ("Profissao: " + ((Individual)caras.getUtilizadores().get(strUt)).getProfissao());
            lblProfissao.setBounds (20,110,150,30);
            conteudo.add (lblProfissao);
            
            JLabel lblNickname = new JLabel ("Nickname: " + ((Individual)caras.getUtilizadores().get(strUt)).getNickname());
            lblNickname.setBounds (20,130,150,30);
            conteudo.add (lblNickname);
            
            final JLabel lblLoc = new JLabel ("Onde estou: " + ((Individual)caras.getUtilizadores().get(strUt)).getLocalizacao().getNome());
            lblLoc.setBounds (20,150,150,30);
            conteudo.add (lblLoc);
            
             //Amigos Empresas
             i = 0;
             final DefaultListModel model3 = new DefaultListModel();
             its = ut.getAmigos().iterator();
             for(i = 0; its.hasNext();)
             {
                 str = (String)its.next();
                 Class cls = cb.getUtilizadores().get(str).getClass();
                 if(cls.getName().equals("Empresa"))
                 {
                     model3.addElement(cb.getUtilizadores().get(str).getNome());
                     i++;
                 }
                }
        
                JLabel lblEmpresas = new JLabel ("Empresas (" + i +")");
                lblEmpresas.setBounds(20,380,100,20);
                conteudo.add(lblEmpresas);  
       
                final JList list3= new JList(model3);
                JScrollPane scrollingList3 = new JScrollPane(list3);
                scrollingList3.setBounds(20,400,200,150);
                conteudo.add(scrollingList3);
        
                //Amigos Associacoes
                i = 0;
                final DefaultListModel model4 = new DefaultListModel();
                its = ut.getAmigos().iterator();
                for(i = 0; its.hasNext();)
                {
                    str = (String)its.next();
                    Class cls = cb.getUtilizadores().get(str).getClass();
                    if(cls.getName().equals("Associacao"))
                    {
                        model4.addElement(cb.getUtilizadores().get(str).getNome());
                        i++;
                    }
                }
        
                JLabel lblAssociacoes = new JLabel ("Associacoes(" + i +")");
                lblAssociacoes.setBounds(20,550,100,20);
                conteudo.add(lblAssociacoes);  
       
                final JList list4= new JList(model4);
                JScrollPane scrollingList4 = new JScrollPane(list4);
                scrollingList4.setBounds(20,570,200,150);
                conteudo.add(scrollingList4);
                
                 //Check-in
                 JLabel lblChekIn = new JLabel ("Check-in:");
                 lblChekIn.setBounds(xSize-350,140,100,20);
                 conteudo.add(lblChekIn);
                 
                 JTextField check = new JTextField(50);
                 check.setEditable(true);
                 check.setBounds(xSize-350,160,200,20);
                 ActionListener paraCheck = new ActionListener() {
                     public void actionPerformed(ActionEvent paraCheck) {
                          int ind;
                          String text = paraCheck.getActionCommand();
                          ind = mp.getIndice(text);
                          //if( ind == -1 )
                          
                          //else 
                          //{
                              ((Individual)caras.getUtilizadores().get(strUt)).setLocalizacao(mp.getLocalizacao(mp.getIndice(text)));
                               JOptionPane.showMessageDialog(conteudo,ind + " - " + ((Individual)caras.getUtilizadores().get(strUt)).getLocalizacao().getNome() );
                               lblLoc.setText("Onde estou: " + ((Individual)caras.getUtilizadores().get(strUt)).getLocalizacao().getNome() );
                               conteudo.repaint();
                          //}
                     }
                };
                check.addActionListener(paraCheck);
                conteudo.add(check);
                
            
            }
            //se for empresa
            else if(clsUt.getName().equals("Empresa"))
            {
                JLabel lblSector = new JLabel ("Sector: " + ((Empresa)caras.getUtilizadores().get(strUt)).getSector());
                lblSector.setBounds (20,110,150,30);
                conteudo.add (lblSector);
            }
            //se for associacao
            else if(clsUt.getName().equals("Associacao"))
            {
                JLabel lblCash = new JLabel ("Cash: " + ((Associacao)caras.getUtilizadores().get(strUt)).getCash());
                lblCash.setBounds (20,110,150,30);
                conteudo.add (lblCash);
            }
        /*JButton btnMural = new JButton ("Mural");
        btnMural.setBounds (5,400,100,20);
        conteudo.add (btnMural);*/
        
        /*JButton btnAmigos = new JButton ("Amigos");
        btnAmigos.setBounds (5,440,100,20);
        conteudo.add (btnAmigos);*/
    
        
        
        
    }
    
}
    