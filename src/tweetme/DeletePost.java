/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author jose
 */
public class DeletePost implements ActionListener{
    private DefaultListModel model ;
    private Map<String, ImageIcon> imageMap;
    private JList list;
    
    public DeletePost(){
    imageMap = new HashMap<>();
    
      model = new DefaultListModel();
     // list.setModel(model);
     int j = 1;
         for (Media m : Main.publishMan.media)
         {
             System.out.println(m.getFilePath()+" - "+m.getText());
             ImageIcon i = new ImageIcon(m.getFilePath());
             imageMap.put(m.getText(), new ImageIcon(this.getScaledImage(i.getImage(), 40, 40)));
             model.addElement(m.getText());
          }        
        
        JButton delete = new JButton("Delete Post");
        delete.addActionListener(this);
        JPanel panel = new JPanel();        
        list = new JList(imageMap.keySet().toArray());
        
        list.setCellRenderer(new ListRenderer());
        list.setModel(model);
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(300, 400));
        panel.add(scroll);
        panel.add(delete);
        
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        
     int selectedIndex = list.getSelectedIndex();
     if (selectedIndex != -1) {
        //list.remove(selectedIndex);//   model.remove(selectedIndex);
        model.remove(list.getSelectedIndex());
        Media m = Main.publishMan.media.get(selectedIndex);
        Main.publishMan.media.remove(m);
     }
    }
    private Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
}    
    public class ListRenderer extends DefaultListCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            label.setIcon(imageMap.get((String) value));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }
}

