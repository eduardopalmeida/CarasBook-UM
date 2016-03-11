import javax.swing.*;
import java.awt.*;
import java.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.util.*;


class MapaGUI extends JFrame {

    public MapaGUI( String strU, Mapa mp , boolean eShow) {

        Toolkit tk = Toolkit.getDefaultToolkit();  
        int w = ((int) tk.getScreenSize().getWidth());  
        int h = ((int) tk.getScreenSize().getHeight());
        
        setTitle ("Mapa");
        setSize (600,350);
        int x = (w-450)/2;
        int y = (h-250)/2;
        setLocation (x,y);
        setResizable(false);
        final Container conteudo = getContentPane();
        conteudo.setLayout(new BorderLayout());
        
        final boolean eS = eShow;
        final String strUt = strU;
        final DefaultListModel model = new DefaultListModel();
        final JList list = new JList(model);
        final DefaultListModel model2 = new DefaultListModel();
        final JList list2 = new JList(model2);
        final JScrollPane scrollingList = new JScrollPane(list);
        final JScrollPane scrollingList2 = new JScrollPane(list2);
        final Box box = Box.createHorizontalBox();
        scrollingList.setColumnHeaderView(new JLabel("Localizacoes"));
        if(eS) scrollingList2.setColumnHeaderView(new JLabel("Adjacentes"));
        else scrollingList2.setColumnHeaderView(new JLabel("Caminho"));
        final Mapa mapa = new Mapa(mp);
        
        
        
        for(Localizacao loc: mapa.getLocalizacoes())
            model.addElement( loc.getNome() );
        
        list.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                 if (e.getClickCount() == 2) {
                     if(eS)
                     {
                         model2.clear();
                         int index = list.locationToIndex(e.getPoint());
                         String item = (String) model.getElementAt(index);
                         Adjacentes ads = mapa.getCaminhos().get(mapa.getIndice(item));
                         Adjacente ad =  new Adjacente();
                         for(Iterator it = ads.getIterator(); it.hasNext(); )
                         {
                            ad = (Adjacente) it.next();
                            model2.addElement( mapa.getLocalizacoes().get(ad.getIndice()).getNome() );
                        }
                        conteudo.repaint();
                        scrollingList2.repaint();
                        box.repaint();
                    }
                    else
                    {
                         model2.clear();
                         int index = list.locationToIndex(e.getPoint());
                         String item = (String) model.getElementAt(index);
                         ArrayList<Localizacao> locs = mapa.caminhoMaisCurto( mapa.getLocalizacoes().get(mapa.getIndice(strUt)), mapa.getLocalizacoes().get(mapa.getIndice(item)));
                         for(Localizacao loc: locs)
                            model2.addElement(loc.getNome());
                    }
                  }
              }
           
            });
   
        box.add(scrollingList);
        box.add(scrollingList2);
       
        conteudo.add(box, BorderLayout.CENTER);
      
    }
    
}