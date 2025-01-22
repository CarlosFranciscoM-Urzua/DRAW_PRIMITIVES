package draw_primitives;

import java.awt.*;

public class Circunferencia {

   private Pixel px;
   private Graphics g;
   
   public Circunferencia(Graphics g){
      this.px = new Pixel(g);
      this.g = g;
   }
   
   /*public Circunferencia(Pixel px, Graphics g){
      this.px = px;
      this.g = g;
   }*/
   
   public Circunferencia(Pixel px){
      this.px = px;
      this.g = px.getG();
   }

   //FORMULA GENERAL DE LA CIRCUNFERENCIA
   public void dibujarCirculo (int x0, int y0, int r, int gap, Color c){
      //Pixel p = new Pixel();
      int sqrt = 0;
      
      
      for (int x = x0 - r; x <= x0 + r; x++){
         sqrt = (int) Math.sqrt(r*r - Math.pow(x - x0, 2));
         px.drawPixel(x, y0 + sqrt, gap, c);
         px.drawPixel(x, y0 - sqrt, gap, c);
      }
   }
      
   public void circuloIncremental (int xc, int yc, int r, int gap, Color c){
      int tx, ty;
      double dt, cc,ss,x,y,aux;
      //Pixel p = new Pixel();
      dt = 1.0/r;
      cc = Math.cos(dt);
      ss = Math.sin(dt);
      x = 0;
      y = r;
      
      while (y >= Math.abs(x)){
         tx = (int)Math.round(x);
         ty = (int)Math.round(y);
         px.drawPixel(xc + tx, yc + ty, gap, c);
         px.drawPixel(xc + tx, yc - ty, gap, c);
         px.drawPixel(xc - tx, yc + ty, gap, c);
         px.drawPixel(xc - tx, yc - ty, gap, c);
         
         px.drawPixel(xc + ty, yc + tx, gap, c);
         px.drawPixel(xc + ty, yc - tx, gap, c);
         px.drawPixel(xc - ty, yc + tx, gap, c);
         px.drawPixel(xc - ty, yc - tx, gap, c);
         
         aux = x;
         x = x*cc - y*ss;
         y = y*cc + aux * ss;
         //y = y*cc - aux * ss;//HIPERBOLA
      }
   }
   
   public void circuloBresenham (int xc, int yc, int r, int gap, Color c){
      int p,x,y;
      x = 0;
      y = r;
      p = 3 - 2*r;
      //Pixel px = new Pixel();
      
      while (x <= y){
         px.drawPixel(xc + x, yc + y, gap, c);
         px.drawPixel(xc + x, yc - y, gap, c);
         px.drawPixel(xc - x, yc + y, gap, c);
         px.drawPixel(xc - x, yc - y, gap, c);
         
         px.drawPixel(xc + y, yc + x, gap, c);
         px.drawPixel(xc + y, yc - x, gap, c);
         px.drawPixel(xc - y, yc + x, gap, c);
         px.drawPixel(xc - y, yc - x, gap, c);
         

         if(p < 0){
            p = p + 4 * x + 6;
         } else {
            p = p + 4 * (x-y) + 10;
            y--;
         }
         
         x++;
      }
   }
}