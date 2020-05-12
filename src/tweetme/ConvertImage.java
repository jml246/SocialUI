/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jose
 */
public class ConvertImage {
     
        String newFilePath = null;
        
        
        BufferedImage bufferedImage = null, newBufferedImage = null;
        

        long l = new Date().getTime();
        final int TWIDTH = 1024;
        final int THEIGHT = 512;
        final int IWIDTH = 1080;
        final int IHEIGHT = 1350;
        boolean isPortrait = false;
        int imgW = 0;
        int imgH = 0;
        double reductionPercentage = 0.0;
            
    public ConvertImage(File selectedFile)
    {
    newFilePath = selectedFile.getParent()+"\\"+"social-ui-temp-" +  l + ".jpg";
        try {
            //read file
            Main.mainGui.updateFeedlabel("Reading image: " + selectedFile.getAbsolutePath());
            bufferedImage = ImageIO.read(new File(selectedFile.getAbsolutePath()));
            
            
            Main.mainGui.updateFeedlabel("Copying image to: " + newFilePath);
            ImageIO.write(bufferedImage, "jpg", new File(newFilePath));
            Main.mainGui.updateFeedlabel("Reading image: " + newFilePath);
            
            bufferedImage = ImageIO.read(new File(newFilePath));
                
            Main.mainGui.updateFilePathField(newFilePath);
        
        } catch (IOException ex) {
            Logger.getLogger(SocialUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //no conversion needed for instagram
        if(newFilePath.contains(".jpg") || 
                (newFilePath.contains(".jpeg"))){
            
        
        ImageIcon img = new ImageIcon(newFilePath);
        Image imgIcon = img.getImage();
        int h = Main.mainGui.getImageScreen().getHeight()-40;
        Image newImg = imgIcon.getScaledInstance(Main.mainGui.getImageScreen().getWidth(), h, Image.SCALE_DEFAULT);
        Main.mainGui.getImageScreen().setIcon(new ImageIcon(newImg));    

        }
        

            
        //Do conversion from png to jpg
        if(selectedFile.getAbsolutePath().contains(".png")){
            System.out.println("Converting png to jpg");
            System.out.println("Fetching absolute path: "+selectedFile.getAbsolutePath());
            Main.mainGui.updateFeedlabel("Reading png file...");
            bufferedImage = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            try {
                ImageIO.write(bufferedImage, "jpg", new File(newFilePath));
                Main.mainGui.updateFeedlabel("Converting png file to jpg...");
                System.out.println("Writing to: "+newFilePath);
                Main.mainGui.updateFilePathField(newFilePath);
                Main.mainGui.updateFeedlabel("Converted png file to jpg completed...");
                
            } catch (IOException ex) {
                Logger.getLogger(SocialUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //calculate percentage decrease
            // Decrease = Decrease ÷ Original Number × 100
            
            ImageIcon img = new ImageIcon(newFilePath);
            Image imgIcon = img.getImage();
            Image newImg = imgIcon.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            Main.mainGui.getImageScreen().setIcon(new ImageIcon(newImg));
        }   
            //Is it within proportions?
            
            
            
            //Do checks on image dimensions for Instagram
            if(bufferedImage.getWidth() > THEIGHT || 
                    bufferedImage.getHeight() > TWIDTH ||
                    bufferedImage.getWidth() > IHEIGHT ||
                    bufferedImage.getWidth() > IWIDTH)
            {
                JOptionPane.showMessageDialog(null,"Image is too large.<br/>"+
                        "Instagram: Max is 1080 x 1350 and 5MB limit<br/>" +
                            "Twitter: Max is 1024 x 512");

            }
            //find proportional dimensions and resize
               System.out.println("Bufferedimage width... "+bufferedImage.getWidth());
               if(bufferedImage.getWidth() > TWIDTH){
                   Main.mainGui.updateFeedlabel("File is too large...resizing to " + TWIDTH +" wide pixels.");
                
                reductionPercentage = (((double)TWIDTH / (double)bufferedImage.getWidth()));
               System.out.println("TWIDTH... "+TWIDTH);
                
               System.out.println("Red Perc... "+reductionPercentage);
                   

                try {
                    Main.mainGui.updateFeedlabel("Reducing file by ... " + reductionPercentage + "%");
                    ImageResizer.resize(newFilePath, newFilePath, reductionPercentage);
                    
                Main.mainGui.updateFeedlabel("Image file resized...");
                } catch (IOException ex) {
                    Main.mainGui.updateFeedlabel("Resizing failed...");
                
                    Logger.getLogger(SocialUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
    }
}
