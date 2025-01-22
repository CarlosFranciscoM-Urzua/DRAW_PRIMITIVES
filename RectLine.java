package draw_primitives;

import java.awt.*;

public class RectLine {
   private Pixel px;
   private Graphics g;
   public int gap;
   
   public RectLine(Pixel px){
      this.px = px;
      this.g = px.getG();
      this.gap = 3;
   }
   
   public RectLine(Graphics g){
      this.px = new Pixel(g);
      this.g = g;
      this.gap = 3;
   }
   
   public Graphics getG(){ return g;}
      
   public void horizontal (int x0,int y0,int lenghtRight, Color c){
      if(lenghtRight < 0){
         x0 += lenghtRight;
         lenghtRight *= -1;
      }
      
      for (int x = x0; x <= x0 + lenghtRight; x++){
         px.drawPixel(x,y0,gap,c);
      }
   }
   
   public void vertical (int x0,int y0,int lenghtUp, Color c){
      if(lenghtUp < 0){
         y0 += lenghtUp;
         lenghtUp *= -1;
      }
      
      for (int y = y0; y <= y0 + lenghtUp; y++){
         px.drawPixel(x0,y,gap,c);
      }

   }
   
   public void degrees45 (int x0,int y0,int lenghtX, Color c){
      if(lenghtX < 0){
         x0 += lenghtX;
         lenghtX *= -1;
      }
      
      for (int i = 0; i <= lenghtX; i++){
         px.drawPixel(x0 + i,y0 + i,gap,c);
      }
   }
   
   public void generalLine (int x0,int y0,int x1,int y1, Color c){
      if (x0 == x1){
         if(y0 > y1){
            int aux = y0;
            y0 = y1;
            y1 = aux;
         }
         for (int y = y0; y <= y1; y++){
            px.drawPixel(x0,y,gap,c);
         }
         return;
      } else if (y0 == y1){
         if(x0 > x1){
            int aux = x0;
            x0 = x1;
            x1 = aux;
         }
         
         for (int x = x0; x <= x1; x++){
            px.drawPixel(x,y0,gap,c);
         }
         return;      
      }else {
         double m;
         int xi = 0,yi = 0,xf = 0,yf = 0;
         if (x0 > x1){
            xf = x0;
            yf = y0;
            xi = x1;
            yi = y1;
         } else {
            xi = x0;
            yi = y0;
            xf = x1;
            yf = y1;
         }
         
         m = (double)(yf-yi)/(xf-xi);
         
         for (int x = xi; x<= xf; x++){
            px.drawPixel(x,yi + (int)Math.round(m * (x - xi)),gap,c);
         }
      }
   }//algoritmo general
   
   public void lineDDA1 (int x0,int y0,int x1,int y1, Color c) {
      int dx, dy, aux, k;
      float x,y,xi,yi;
      
      dx = x1 - x0;
      dy = y1 - y0;
      
      if (Math.abs(dx) > Math.abs(dy)){
         aux = Math.abs(dx);
      } else {
         aux = Math.abs(dy);
      }
      
      xi = dx/aux;
      yi = dy/aux;
      x = x1;
      y = y1;
      
      px.drawPixel((int)x, (int)y,gap,c);
      
      for (k = 1; k<= aux; k++){
         x = x + xi;
         y = y + yi;
         
         px.drawPixel((int)x, (int)y,gap,c);
      }      
   }//dda1
   
   public void lineDDA2 (int x0,int y0,int x1,int y1, Color c){
      int x,y,t;
      float m,m1,t1;
      
      px.drawPixel(x0, y0,gap,c);
      
      if (x0 == x1) {//PENDIENTE INFINITA
         for (y = y0 + 1; y <= y1; y++){
            px.drawPixel(x0, y,gap,c);
         }
      } else if (y0 == y1) {//PENDIENTE NULA
         for (x = x0 + 1; x <= x1; x++){
            px.drawPixel(x, y0,gap,c);
         }
      } else {
         m = (float)(y1 - y0)/(x1 - x0);
         if ((Math.abs(m) < 1 && (x0 > x1)) || (Math.abs(m) > 1 && (y0 > y1))){
            t = x0;
            x0 = x1;
            x1 = t;
            
            t = y0;
            y0 = y1;
            y1 = t;
         }
         
         if (Math.abs(m) < 1) {
            t1 = y1;
            
            for (x = x0; x <=x1; x++) {
               t1 = t1 + m;
               px.drawPixel(x, (int) t1,gap,c);               
            }
         } else {
            m1 = 1/m;
            t1 = x0;
            
            for (y = y0 + 1; y <= y1; y++){
               t1 = t1 + m1;
               px.drawPixel((int)t1, y,gap,c);
            }
         }
      }
   }//dda2
   
   public void bresenhamLine (int x0,int y0,int x1,int y1, Color c){
      int dx, dy, x, y, xf, p, c0, c1, in;
      
      dx = Math.abs (x1 - x0);
      dy = Math.abs (y1 - y0);
      p = 2 * dy - dx;
      c0 = 2 * dy;
      c1 = 2 * (dy - dx);
      
      if (x0 > x1){
         x = x1;
         y = y1;
         xf = x0;
      } else {
         x = x0;
         y = y0;
         xf = x1;
      }
      
      if (y1 < y0){
         in = -1;
      } else {
         in = 1;
      }
      
      px.drawPixel(x, y,gap,c);
      
      while (x <= xf){
         x = x + 1;
         
         if(p < 0){
            p = p + c0;
         } else {
            y = y + in;
            p = p + c1;
         }
         
         px.drawPixel(x, y,gap,c);
      }
   } 
}//class0