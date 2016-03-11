import javax.swing.*;
import java.awt.*;
import java.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.util.*;

class utilGUI extends JFrame {

    public utilGUI( String str, CarasBook cb, boolean eConvite ) {

        Toolkit tk = Toolkit.getDefaultToolkit();  
        int w = ((int) tk.getScreenSize().getWidth());  
        int h = ((int) tk.getScreenSize().getHeight());
        
        setTitle ("Todos Utilizadores");
        setSize (600,350);
        int x = (w-450)/2;
        int y = (h-250)/2;
        setLocation (x,y);
        setResizable(false);
        final Container conteudo = getContentPane();
        conteudo.setLayout(new BorderLayout());
        
        final ArrayList<Utilizador> uts = cb.giveUtilizadores();
        final String strUt = str;
        final CarasBook caras = new CarasBook(cb);
        final boolean eC = eConvite;
         
        JTextField[] txtUser = new JTextField[ uts.size() ];
         
        int size = uts.size();
        int pos = 50;
         
        /*for( int i = 0; i < size; i++ )
        {
             txtUser[i] = new JTextField();
             txtUser[i].setBounds(10,pos,150,40);
             txtUser[i].setText(uts.get(i).getNome() + "\n" + uts.get(i).getMorada().getNome());
             conteudo.add(txtUser[i]);
             pos = pos + 60;
        }*/
        /*String[] items = new String[size];
        int i = 0;
        for(Utilizador ut: uts)
            items[i++] = ut.getNome() + " (" + ut.getMorada().getNome() + ")";*/
        
        final DefaultListModel model = new DefaultListModel();
        final JList list = new JList(model);
        JScrollPane scrollingList = new JScrollPane(list);
        
        
        //String[] items = new String[size];
        //int i = 0;
        for(Utilizador ut: uts)
            model.addElement( "<html>" + ut.getNome() + " <br> (" + ut.getMorada().getNome() + ") </html" );
        
        conteudo.add(scrollingList);
         
         list.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                 if (e.getClickCount() == 2) {
                     int index = list.locationToIndex(e.getPoint());
                     if(eC)
                     {
                        caras.envPedAmig(uts.get(index).getEmail(), strUt);
                        JOptionPane.showMessageDialog(conteudo,"Convidou " + uts.get(index).getEmail());
                    }
                    else
                    {
                        int nivel =  caras.nivelEntre(uts.get(index).getEmail(), strUt);
                        if(nivel > -1)
                            JOptionPane.showMessageDialog(conteudo,"Esta' a' distancia de " + nivel + " do utilizador " + uts.get(index).getEmail());
                        else JOptionPane.showMessageDialog(conteudo,"Não ha' ligacao para o utilizador " + uts.get(index).getEmail());
                    }
                }
            }});
    }
    
}