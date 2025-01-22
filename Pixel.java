package draw_primitives;

import java.awt.*;

public class Pixel {
   private Graphics g;
   
   //public Pixel(){}
   
   public Pixel(Graphics g){
      this.g = g;
   }
   
   public Graphics getG(){
      return g;
   }
   
   /*public void drawPixel(int x, int y, Graphics g){
      g.setColor(Color.black);
      g.fillRect(x,y,1,1);
   }
   
   public void drawPixel(int x, int y, Color c, Graphics g){
      g.setColor(c);
      g.fillRect(x,y,1,1);
   }
   
   public void drawPixel(int x, int y, int grosor, Graphics g){
      g.setColor(Color.black);
      g.fillRect(x,y,grosor,grosor);
   }
   
    public void drawPixel(int x, int y, int grosor, Color c, Graphics g){
      g.setColor(c);
      g.fillRect(x,y,grosor,grosor);
   }
   
   public void drawPixel(int x, int y, int ancho, int alto, Color c, Graphics g){
      g.setColor(c);
      g.fillRect(x,y,ancho,alto);
   }*/
   
   /*public void drawPixel(int x, int y){
      //g.setColor(Color.black);
      g.fillRect(x,y,1,1);
   }
   
   public void drawPixel(int x, int y, Color c){
      g.setColor(c);
      g.fillRect(x,y,1,1);
   }*/
   
   public void drawPixel(int x, int y, int grosor){
      //g.setColor(Color.black);
      g.fillRect(x,y,grosor,grosor);
   }
   
    public void drawPixel(int x, int y, int grosor, Color c){
      g.setColor(c);
      g.fillRect(x,y,grosor,grosor);
   }
   
   /*public void drawPixel(int x, int y, int ancho, int alto, Color c){
      g.setColor(c);
      g.fillRect(x,y,ancho,alto);
   }*/
}