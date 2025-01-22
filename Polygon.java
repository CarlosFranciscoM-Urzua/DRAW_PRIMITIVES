package draw_primitives;

import java.awt.*;

public class Polygon {

   Graphics g;
   RectLine r;
   
   public Polygon (Graphics g){
      this.g = g;
      this.r = new RectLine(g);
   }
   
   public Polygon(RectLine r){
      this.r = r;
      this.g = r.getG();
   }
   
   public void drawPolyline (int[] x, int[] y){
      for (int i = 0; i < x.length - 1; i++){
         r.generalLine(x[i], y[i],x[i+1], y[i+1], g.getColor());
      }   
   }
   
   public void drawPolygon (int[] x, int[] y){
      this.drawPolyline (x,y);
      r.generalLine(x[0], y[0],x[x.length - 1], y[x.length - 1],g.getColor());
   }
   
   public void trasladarPolygon (int[] x, int[] y, int tx, int ty){
      for (int i = 0; i < x.length; i++){
         x[i] = x[i] + tx;
         y[i] = y[i] + ty;
      } 
      
      this.drawPolygon(x,y);
   }
   
   public void escalarPolygon (int[] x, int[] y, float sx, float sy){
      for (int i = 0; i < x.length; i++){
         x[i] = (int)(x[i] * sx);
         y[i] = (int)(y[i] *sy);
      } 
      
      this.drawPolygon(x,y);
   }
   
   public void drawEscalarPolygonPuntoFijo (int[] x, int[] y, float sx, float sy){
      /*for (int i = 0; i < x.length; i++){
         //x[i] = (int)((x[i] - x[0]) * sx) + x[i];
         //y[i] = (int)((y[i] - y[0]) *sy) + y[i];
         
         x[i] = (int)((x[i] - x[0]) * sx) + x[0];
         y[i] = (int)((y[i] - y[0]) *sy) + y[0];
      }*/
      
      this.drawPolygon(escalarPuntoFijo(x,sx,x[0]),escalarPuntoFijo(y,sy,y[0]));
   }
   
    public void drawEscalarPolygonPuntoFijo (int[] x, int[] y, float sx, float sy, int x0, int y0){
      /*for (int i = 0; i < x.length; i++){
         x[i] = (int)((x[i] - x0) * sx) + x0;
         y[i] = (int)((y[i] - y0) *sy) + y0;
         
         //x[i] = (int)((x[i] - x[0]) * sx) + x[i];
         //y[i] = (int)((y[i] - y[0]) *sy) + y[i];
      } */
      
      this.drawPolygon(escalarPuntoFijo(x,sx,x0),escalarPuntoFijo(y,sy,y0));      
   }
   
   public int[] escalarPuntoFijo (int[] x, float sx, int x0){
      int u[] = new int[x.length];
      for (int i = 0; i < x.length; i++){
         u[i] = (int)((x[i] - x0) * sx) + x0;
      } 
      
      return u;
   }
   
   public int[] computeCenter(int[] x, int[] y){
      double lengths[] = new double [x.length];
      double lengthTotal = 0;
      double xC = 0, yC = 0;
      
      for (int i = 0; i < x.length; i++){
         lengths[i] = Math.sqrt(x[i]*x[i] + y[i]*y[i]);
         lengthTotal += lengths[i];
         xC += x[i] * lengths[i];
         yC += y[i] * lengths[i];

      }
      
      return new int[]{(int)(xC / lengthTotal), (int)(yC / lengthTotal)};      
   }
   
   public int[] rotatePointsX_grades(int[] x, int[] y, double theta){
      int[] x1 = new int[x.length];
      theta = Math.toRadians(theta);
      
      for (int i = 0; i < x.length; i++){
         x1[i] = (int)Math.round(x[i] * Math.cos(theta) - y[i] * Math.sin(theta));
      } 
      
      return x1;     
   }
   
   public int[] rotatePointsY_grades(int[] x, int[] y, double theta){
      int[] y1 = new int[y.length];
      theta = Math.toRadians(theta);
      
      for (int i = 0; i < x.length; i++){
         y1[i] = (int)Math.round(x[i] * Math.sin(theta) + y[i] * Math.cos(theta));
      }
      
      return y1;     
   }
   
   public int[] rotatePointsX_grades(int[] x, int[] y, int xr, int yr, double theta){
      int[] x1 = new int[x.length];
      theta = Math.toRadians(theta);
      
      for (int i = 0; i < x.length; i++){
         x1[i] = (int)Math.round((x[i] - xr) * Math.cos(theta) - (y[i] - yr) * Math.sin(theta) + xr);
      } 
      
      return x1;     
   }
   
   public int[] rotatePointsY_grades(int[] x, int[] y, int xr, int yr, double theta){
      int[] y1 = new int[y.length];
      theta = Math.toRadians(theta);
      
      for (int i = 0; i < x.length; i++){
         y1[i] = (int)Math.round((x[i] - xr) * Math.sin(theta) + (y[i] - yr) * Math.cos(theta) + yr);
      }
      
      return y1;     
   }

   
   public void drawRotatedPolygon(int[] x, int[] y, double theta){
      int[] x1 = rotatePointsX_grades(x,y,theta);
      int[] y1 = rotatePointsY_grades(x,y,theta);
      this.drawPolygon(x1,y1);
   }
   
   public void drawRotatedPolygon(int[] x, int[] y, int xr, int yr, double theta){
      int[] x1 = rotatePointsX_grades(x,y,xr,yr,theta);
      int[] y1 = rotatePointsY_grades(x,y,xr,yr,theta);
      this.drawPolygon(x1,y1);
   }
   
   public void drawRotatedPolygonIrreversible(int[] x, int[] y, int xr, int yr, double theta){
      int[] x1 = rotatePointsX_grades(x,y,xr,yr,theta);
      y = rotatePointsY_grades(x,y,xr,yr,theta);
      x = x1;
      this.drawPolygon(x,y);
   }

   
   public void drawRotatedPolygonCenter(int[] x, int[] y, double theta){
      int[] center = this.computeCenter(x,y);
      int[] x1 = rotatePointsX_grades(x,y,center[0],center[1],theta);
      int[] y1 = rotatePointsY_grades(x,y,center[0],center[1],theta);
      this.drawPolygon(x1,y1);
   }


   public int[] reflectPoints(int u[]){
      int r[] = new int[u.length];
      for (int i = 0; i < u.length; i++){
            r[i] = -1 * u[i];
      }
      
      return r;
   }
   
   public void drawPolygonReflectX(int[] x, int[] y){
      //y = this.reflectPoints(y);
      //this.drawPolygon(x,y);
      this.drawPolygon(x,this.reflectPoints(y));
   }
   
   public void drawPolygonReflectY(int[] x, int[] y){
      //x = this.reflectPoints(x);
      //this.drawPolygon(x,y);
      this.drawPolygon(this.reflectPoints(x),y);
   }
   
   public void drawPolygonReflectXY(int[] x, int[] y){
      /*x = this.reflectPoints(x);
      y = this.reflectPoints(y);
      this.drawPolygon(x,y);*/
      this.drawPolygon(this.reflectPoints(x),this.reflectPoints(y));
   }
   
   public void drawPolygonReflectDiagonal(int[] x, int[] y){
      this.drawPolygon(y,x);
   }
   
   public void drawPolygonReflectDiagonalInv(int[] x, int[] y){
      this.drawPolygon(this.reflectPoints(y),this.reflectPoints(x));
   }
   
   public int[] cutPoints(int u[], int v[], float sh, int vRef){
      int r[] = new int[u.length];
      for (int i = 0; i < u.length; i++){
            r[i] = u[i] + (int)(sh * (v[i] - vRef));
      }
      
      return r;
   }   
   
   public void drawPolygonCutX(int x[], int y[], float sh, int xRef){
      int[] x1 = cutPoints(x,y,sh,xRef);
      this.drawPolygon(x1,y);
   }
   
   public void drawPolygonCutX(int x[], int y[], float sh){
      drawPolygonCutX(x,y,sh,0);
   }
   
   public void drawPolygonCutY(int x[], int y[], float sh, int yRef){
      int[] y1 = cutPoints(y,x,sh,yRef);
      this.drawPolygon(x,y1);
   }
   
   public void drawPolygonCutY(int x[], int y[], float sh){
      int[] y1 = cutPoints(y,x,sh,0);
      this.drawPolygon(x,y1);
   }
}//class