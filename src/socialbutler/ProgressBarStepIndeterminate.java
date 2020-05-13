/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialbutler;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author jose
 */
public class ProgressBarStepIndeterminate extends Thread{
  private JProgressBar aJProgressBar ;
  JFrame frame = new JFrame("Please Wait...");
  public ProgressBarStepIndeterminate(){
     
  }
       
  public static void main(String args[]) {
      new ProgressBarStepIndeterminate();
  }
  public JProgressBar getProgressBar(){
      return aJProgressBar;
  }
  
  public void run(){
  
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    aJProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
    aJProgressBar.setStringPainted(true);
    aJProgressBar.setString("Please Wait...");
    aJProgressBar.setIndeterminate(true);

    frame.add(aJProgressBar, BorderLayout.NORTH);
    frame.setUndecorated(true);
    frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    frame.setSize(500, 500);
    frame.setAlwaysOnTop (true);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);  
     
    try {
        
        
          System.out.println("Progress Bar Sleeping...");
          Thread.sleep(1000);    
        
    } catch (InterruptedException ex) {
          Logger.getLogger(ProgressBarStepIndeterminate.class.getName()).log(Level.SEVERE, null, ex);
      }
    }   
  
}
