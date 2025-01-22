package draw_primitives;

import java.awt.*;

public class RegularStar extends Polygon {   
   public RegularStar(Graphics g){
      super(g);      
   }
   
   public RegularStar (RectLine r){
      super(r);
   }
   
   public void draw(int n, int x0, int y0, int length){
      int[] x = new int[2 * n];
      int[] y = new int[2 * n];
      double alpha = Math.PI/n;
      double radius = length / Math.cos(alpha);
      double theta = 0;
      
      for (int i = 0; i < 2 * n; i++){
         theta = i * alpha;
         if(i % 2 == 0){            
            x[i] = (int)(x0 + radius * Math.cos(theta));
            y[i] = (int)(y0 + radius * Math.sin(theta));
         } else {
            x[i] = (int)(x0 + 2 * length * Math.cos(theta));
            y[i] = (int)(y0 + 2 * length * Math.sin(theta));
         }//else
      }//for
      
      super.drawPolygon(x,y);
   }//draw 
   
   public void draw(int n, int x0, int y0, int length, int radius){
      int[] x = new int[2 * n];
      int[] y = new int[2 * n];
      double alpha = Math.PI/n;
      //double radius = length / Math.cos(alpha);
      double theta = 0;
      
      for (int i = 0; i < 2 * n; i++){
         theta = i * alpha;
         if(i % 2 == 0){            
            x[i] = (int)(x0 + radius * Math.cos(theta));
            y[i] = (int)(y0 + radius * Math.sin(theta));
         } else {
            /*x[i] = (int)(x0 + 2 * length * Math.cos(theta));
            y[i] = (int)(y0 + 2 * length * Math.sin(theta));*/
            x[i] = (int)(x0 + (length + radius) * Math.cos(theta));
            y[i] = (int)(y0 + (length + radius) * Math.sin(theta));
         }//else
      }//for
      
      super.drawPolygon(x,y);
   }//draw 
   
   public int[] coordinatesOnX(int n, int x0, int y0, int length, int radius){
      int[] x = new int[2 * n];
      double alpha = Math.PI/n;
      double theta = 0;
      
      for (int i = 0; i < 2 * n; i++){
         theta = i * alpha;
         if(i % 2 == 0){            
            x[i] = (int)(x0 + radius * Math.cos(theta));
         } else {
            x[i] = (int)(x0 + (length + radius) * Math.cos(theta));
         }//else
      }//for
      
      return x;
   }//coordinatesOnX  
   
   public int[] coordinatesOnY(int n, int x0, int y0, int length, int radius){
      int[] y = new int[2 * n];
      double alpha = Math.PI/n;
      double theta = 0;
      
      for (int i = 0; i < 2 * n; i++){
         theta = i * alpha;
         if(i % 2 == 0){            
            y[i] = (int)(y0 + radius * Math.sin(theta));
         } else {
            y[i] = (int)(y0 + (length + radius) * Math.sin(theta));
         }//else
      }//for
      
      return y;
   }//coordinatesOnX  
}//class