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
        final int TWIDTHP = 1200;
        final int THEIGHTP = 675;
        final int IWIDTHL = 1080;
        final int IHEIGHTL = 608;
        final int IWIDTHP = 1080;
        final int IHEIGHTP = 1350;
        boolean isPortrait = false;
        int imgW = 0;
        int imgH = 0;
        double reductionPercentage = 0.0;
            
    public ConvertImage(File selectedFile) throws IOException
    {
        //get format
        String formatName = getFormat(selectedFile.toString());
        newFilePath = selectedFile.getParent() + "\\"+"social-ui-temp-" +  l + "." + formatName;
        this.copyImage(selectedFile.toString(), newFilePath);
        //no conversion needed for instagram
        
        
        if(newFilePath.contains(".jpg") || 
                (newFilePath.contains(".jpeg"))){
                //do nothing and draw preview on form
                this.drawImageScreenLabel(newFilePath);
        }
        
        
        //PNG
        //Do conversion from PNG to JPG
        if(newFilePath.contains(".png")){
            System.out.println("Converting png to jpg");
            System.out.println("Fetching absolute path: " + selectedFile.getAbsolutePath());
            Main.mainGui.updateFeedLabel("Reading png file...");
            try {
                bufferedImage = ImageIO.read(new File(selectedFile.toString()));
   
                newFilePath = selectedFile.getParent()+"\\"+"social-ui-temp-" +  l + ".jpg";
                // create a blank, RGB, same width and height, and a white background
                BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
			bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            // Write the jpeg file
                ImageIO.write(newBufferedImage, "jpg", new File(newFilePath));
                Main.mainGui.updateFeedLabel("Converting png file to jpg...");
                System.out.println("Writing to: " + newFilePath);
                Main.mainGui.updateFilePathField(newFilePath);
                Main.mainGui.updateFeedLabel("Converted png file to jpg completed...");
            } catch (IOException ex) {
                Logger.getLogger(SocialUI.class.getName()).log(Level.SEVERE, null, ex);
            }           
            this.drawImageScreenLabel(newFilePath);
        }   
            //Is it within proportions?
            
          
            //Do checks on image dimensions for Instagram
            if(bufferedImage.getWidth() > THEIGHTP || 
                    bufferedImage.getHeight() > TWIDTHP ||
                    bufferedImage.getWidth() > IHEIGHTP ||
                    bufferedImage.getWidth() > IWIDTHP)
            {
                if (JOptionPane.showConfirmDialog(null, "<html>Image is too large.<br/>"+
                        "Instagram: Max is 1080 x 1350 and 5MB limit<br/>" +
                            "Twitter: Max is 1024 x 512</br>" +
                        "Would you like to resize automatically?", "WARNING",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        // yes option
   

                //find proportional dimensions and resize
            
                //check width
               System.out.println("Bufferedimage width... "+bufferedImage.getWidth());
               
                //check if it is a landscape photo
               if(bufferedImage.getWidth() > bufferedImage.getHeight() &&
                       bufferedImage.getWidth() > IWIDTHP)
               {
                   
                   Main.mainGui.updateFeedLabel("File is too wide...resizing to " + IWIDTHP +" width pixels.");
                   reductionPercentage = (((double)IWIDTHL / (double)bufferedImage.getWidth()));
                   System.out.println("IWIDTHL... "+IWIDTHL); 
                   System.out.println("Red Perc... "+reductionPercentage);
                   ImageResizer.resize(newFilePath, newFilePath, IWIDTHL,IHEIGHTL);
                   
                   Main.mainGui.updateFeedLabel("Image file altered...");
               }
               // if it is a portrait
               if(bufferedImage.getHeight() > bufferedImage.getWidth() &&
                       bufferedImage.getHeight() > IHEIGHTP){
                   Main.mainGui.updateFeedLabel("File is too high...resizing to " + IHEIGHTP +" high pixels.");
                   reductionPercentage = (((double)IHEIGHTP / (double)bufferedImage.getHeight()));
                   System.out.println("THEIGHT... "+IHEIGHTP); 
                   System.out.println("Red Perc... "+reductionPercentage);
                   // Main.mainGui.updateFeedLabel("Reducing file by ... " + reductionPercentage + "%");
                   Main.mainGui.updateFeedLabel("Altering Image...");
                   ImageResizer.resize(newFilePath, newFilePath, IWIDTHP,IHEIGHTP);
                   
                   Main.mainGui.updateFeedLabel("Image file altered...");

               }
               
                }
                } 
                else 
                { 
                     //do nothing
                }
}
  
    public void copyImage(String source, String destination)
    {
        //make a working copy
        try {

            if(source.contains("jpg") || source.contains("jpeg")){
                Main.mainGui.updateFeedLabel("Reading image: " + source);
                bufferedImage = ImageIO.read(new File(source));  
                Main.mainGui.updateFeedLabel("Copying working JPG image to: " + destination);
                ImageIO.write(bufferedImage, "jpg", new File(destination));
            }
            
            if(source.contains("png"))
            {
               bufferedImage = ImageIO.read(new File(source)); 
                // create a blank, RGB, same width and height, and a white background
                BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
			bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

                // Write the jpeg file
                ImageIO.write(newBufferedImage, "jpg", new File(newFilePath));   
            }
            Main.mainGui.updateFeedLabel("Reading image: " + destination);   
            bufferedImage = ImageIO.read(new File(destination));    
            Main.mainGui.updateFilePathField("Copied working PNG image to..."+ destination);
        
        } catch (IOException ex) {
            Logger.getLogger(SocialUI.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public String getFormat(String file)
    {
        return file.substring(file.lastIndexOf(".") + 1);    
    }

   
    private void drawImageScreenLabel(String filePath){
            ImageIcon img = new ImageIcon(filePath);
            Image imgIcon = img.getImage();
            
            //
            int h = Main.mainGui.getImageScreenLabel().getHeight()-40;
            Image newImg = imgIcon.getScaledInstance(Main.mainGui.getImageScreenLabel().getWidth(), h, Image.SCALE_DEFAULT);
            Main.mainGui.getImageScreenLabel().setIcon(new ImageIcon(newImg));
    }
}
