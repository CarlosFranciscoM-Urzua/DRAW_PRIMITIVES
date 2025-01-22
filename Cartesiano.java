package draw_primitives;

import java.awt.*;

public class Cartesiano extends Pixel{
   int x0;
   int y0;
   int cuadrante;
   
   public Cartesiano (Graphics g){
      super(g);
      cuadrante = -1;
   }
   
   public void setX0(int x0){
      this.x0 = x0;
      cuadrante = -1;
   }
   
   public void setY0(int y0){
      this.y0 = y0;
      cuadrante = -1;
   }
   
   public void setCuadrante (int cuadrante){
      this.cuadrante = 3 - (cuadrante % 4);
   }
   
   
   
   public int getX(int x, int w, int cuadrante){
      if(cuadrante > 0 && cuadrante < 3){
         x = x0 - x - w;
      }  else x = x0 + x;
      
      x %= (2*x0);
      return x;
   }
   
   public int getY(int y, int l, int cuadrante){
      cuadrante %= 4;
      if (cuadrante < 2){
         y = y + y0;
      } else y = y0 - y - l;
            
      y %= (2*y0);
      return y;
   }
   
   public void drawPixel(int x, int y, int grosor, Color c, int cuadrante){
      super.getG().setColor(c);
      x = getX(x,grosor,cuadrante);
      y = getY(y,grosor,cuadrante);
      super.getG().fillRect(x,y,grosor,grosor);
   }
   
   public void drawPixel(int x, int y, int grosor, Color c){
      super.getG().setColor(c);
      int x1,y1;
      if(cuadrante != -1){
         x1 = getX(x,grosor,cuadrante);
         y1 = getY(y,grosor,cuadrante);
         super.getG().fillRect(x1,y1,grosor,grosor);

      } else {
         for(int i = 0; i < 4; i++){
            x1 = getX(x,grosor,i);
            y1 = getY(y,grosor,i);
            super.getG().fillRect(x1,y1,grosor,grosor);
         }
      }            
   }
   
   public void drawPixel(int x, int y, int grosor){
      int x1,y1;
      if(cuadrante != -1){
         x1 = getX(x,grosor,cuadrante);
         y1 = getY(y,grosor,cuadrante);
         super.getG().fillRect(x1,y1,grosor,grosor);

      } else {
         for(int i = 0; i < 4; i++){
            x1 = getX(x,grosor,i);
            y1 = getY(y,grosor,i);
            super.getG().fillRect(x1,y1,grosor,grosor);
         }
      }      
   }
}