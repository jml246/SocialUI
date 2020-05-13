/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetme;

import java.awt.image.BufferedImage;

/**
 *
 * @author jose
 */
public class ImageCropper {
    public ImageCropper(BufferedImage b, int x, int y){
        this.cropImage(b, x, y);
    }


    
    public BufferedImage cropImage(BufferedImage b,int x,int y){
        return  b.getSubimage(0, 0, x, y);
    }
}
