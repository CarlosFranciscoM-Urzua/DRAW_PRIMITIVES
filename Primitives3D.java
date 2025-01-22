package draw_primitives;

import java.awt.*;
import javax.swing.*;

public class Primitives3D {
   private int xPoint;
   private int yPoint;
   TransformacionesMatriciales t;
   Graphics g;
   Pixel px;
   RectLine r;
   draw_primitives.Polygon p;
   
   public Primitives3D(Graphics g){
      px = new Pixel(g);
      r = new RectLine(px);
      p = new Polygon(r);
      t = new TransformacionesMatriciales(1);
   }
   
   public Primitives3D(Pixel px){
      this.px = px;
      g = px.getG();
      r = new RectLine(px);
      p = new Polygon(r);
      t = new TransformacionesMatriciales(1);
   }
   
   public void drawCylinder(int xp, int yp, int h, int a, int partitions){
      drawCylinder(xp, yp, h, a, partitions, Math.PI/3, Math.sqrt(8));
   }
      
   public void drawCylinder(int xp, int yp, int h, int a, int partitions, double phase, double perspectiveRelation){
      int x = 0;
      int y = 0;
      double b = a / perspectiveRelation;
      double theta = 0;
      if(partitions < 1) partitions = 0;
      partitions += 2;
      
      for (int i = 0; i < 360; i++){
         theta = Math.toRadians(i);
         x = xp + (int)(a * Math.cos(theta));
         y = yp + (int)(b * Math.sin(theta));         
         
         px.drawPixel(x,y,2);
         px.drawPixel(x,y + h,2);
         
         if(theta == 0 || theta == Math.PI || (i + (int)Math.toDegrees(phase)) % (360 / partitions) == 0){
            r.generalLine(x,y,x,h + y, g.getColor());
         }
      }
   }
   
   public void drawSphera(int xp, int yp, int radius, int partitions){
      int x = 0;
      int y = 0;
      int d = radius / partitions;
      
      for (int i = 0; i < 360; i++){
         x = xp + (int)(radius * Math.cos(i));
         y = yp + (int)(radius * Math.sin(i));
         
         px.drawPixel(x,y,2);
         
         for(int j = d; j <= radius; j += d){
            x = (int)((radius - j) * Math.cos(i));
            y = (int)(radius * Math.sin(i));
            
            px.drawPixel(xp + x,yp + y,2);            
            px.drawPixel(xp + y,yp + x,2);
         }         
      }
   }
   
   public void drawCone(int xp, int yp, int h, int a){
      int x = 0;
      int y = 0;
      double b = a / Math.sqrt(8);
      
      for (int i = 0; i < 360; i++){
         x = xp + (int)(a * Math.cos(i));
         y = yp + (int)(b * Math.sin(i));
         
         px.drawPixel(x,y,2);
         
         if(i % 10 == 0 && i < 180){
            r.generalLine(x,y,xp,h + yp, g.getColor());
         }
      }
   }
   
   public void drawPyramid(int xp, int yp, int facesOfBase, int apothema, int h){
      drawPyramid(xp,yp,facesOfBase,apothema,h,Math.PI/2,3);
   }
   
   public void drawPyramid(int xp, int yp, int facesOfBase, int apothema, int h, double phase, double perspectiveRelation){
      int[] x = new int[facesOfBase];
      int[] y = new int[facesOfBase];
      double b = apothema / perspectiveRelation;
      double theta = (2 * Math.PI) / facesOfBase;
      
      for (int i = 0; i < facesOfBase; i++){
         x[i] = xp + (int)(apothema * Math.cos(i * theta + phase));
         y[i] = yp + (int)(b * Math.sin(i * theta + phase));
         
         r.generalLine(x[i],y[i],xp,h + yp, g.getColor());         
      }
      
      p.drawPolygon(x,y);
   }
   
   public void drawTetrahedron(int xp, int yp, int apothema, double phase, double perspectiveRelation){
      int h = (int)(apothema * Math.sqrt(13) / 4);
      drawPyramid(xp,yp,3,apothema,h,phase,perspectiveRelation);
   }
   
   public void drawOctahedron(int xp, int yp, int apothema, double perspectiveRelation, double phase){
      int h = (int)(apothema / Math.sqrt(2));
      /*drawPyramid(g,xp,yp,4,apothema, h);
      drawPyramid(g,xp,yp,4,apothema, -h);*/
      
      int[] x = new int[4];
      int[] y = new int[4];
      double b = apothema / perspectiveRelation;
      double theta = Math.PI / 2;
      
      for (int i = 0; i < 4; i++){
         x[i] = xp + (int)(apothema * Math.cos(i * theta + phase));
         y[i] = yp + (int)(b * Math.sin(i * theta + phase));
         
         r.generalLine(x[i],y[i],xp,yp + h, g.getColor());    
         r.generalLine(x[i],y[i],xp,yp - h, g.getColor());       
      }
      
      p.drawPolygon(x,y);

   }
      
   public void drawOctahedron(int xp, int yp, int apothema){
      drawOctahedron(xp, yp, apothema, 4, Math.PI/7);
   }
   
   public void drawPrism(int xp, int yp, int facesOfBase, int apothema, int h, double phase, double perspectiveRelation){
      int[] x = new int[facesOfBase];
      int[] y = new int[facesOfBase];
      int[] y1 = new int[facesOfBase];
      //double dephase = Math.PI/3;
      
      double b = apothema / perspectiveRelation;
      double theta = (2 * Math.PI) / facesOfBase;
      
      for (int i = 0; i < facesOfBase; i++){
         x[i] = xp + (int)(apothema * Math.cos(i * theta + phase));
         y[i] = yp + (int)(b * Math.sin(i * theta + phase));
         y1[i] = y[i] + h;
         
         r.generalLine(x[i],y[i],x[i],h + y[i], g.getColor());         
      }
      
      p.drawPolygon(x,y);
      p.drawPolygon(x,y1);
   }
   
   public void drawPrism(int xp, int yp, int facesOfBase, int apothema, int h){
       drawPrism(xp, yp, facesOfBase, apothema, h,  Math.PI/3,3);
   }
   
   public void drawCube(int x0, int y0, int length, double a){
      double [][] cuadro = new double[][]{
         {x0,x0+length,x0+length,x0},
         {y0,y0,y0+length,y0+length},
         {1,1,1,1}
      };
      
      drawCube(cuadro, a);
   }

   
   public void drawCube (double [][] cuadro, double a){
      int[] x = t.actualizarPuntos(cuadro,0);
      int[] y = t.actualizarPuntos(cuadro,1);
      p.drawPolygon(x,y);
      
      double[][] p1 = t.multiplicar(t.CX(a),cuadro);
      double[][] cx = t.multiplicar(t.CX(a),cuadro);
      p1 = t.multiplicar(t.S(1,a),p1);
      double[][] T1 = t.T((int)(cuadro[0][2] - p1[0][2]),(int)(cuadro[1][2] - p1[1][2]));
      p1 = t.multiplicar(T1,p1);  
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
      
      T1 = t.T((int)(cuadro[0][1] - p1[0][1]),(int)(cuadro[1][1] - p1[1][1]));
      p1 = t.multiplicar(T1,p1); 
      double xp = cuadro[0][0] - p1[0][0], yp = cuadro[1][0] - p1[1][0];
      p1 = t.multiplicar(t.T(-xp, -yp),p1);
      p1 = t.multiplicar(t.R(180),p1);
      p1 = t.multiplicar(t.T(cuadro[0][0] - p1[0][1], cuadro[1][0] - p1[1][1]),p1);
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
      
      g.setColor(Color.black);
      p1 = t.multiplicar(t.CY(a),cuadro);  
      p1 = t.multiplicar(t.S(a,1),p1);
      double[][] T2 = t.T((int)(cuadro[0][2] - p1[0][2]),(int)(cuadro[1][2] - p1[1][2]));
      p1 = t.multiplicar(T2,p1);
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
      
      p1 = t.multiplicar(t.T((int)(1 * (cuadro[0][0] - cx[0][3])),(int)(a * (cuadro[1][0] - cx[1][3]))), cuadro );
      x = t.actualizarPuntos(p1,0);
      y = t.actualizarPuntos(p1,1);
      p.drawPolygon(x,y); 
   }
         
   public void drawScenario(int width, int n){
      g.drawLine(0,yPoint,width,yPoint);
      
      for (int i = 0; i < n; i++){
         g.drawLine(xPoint, yPoint, (int)(xPoint * (1 + 2 * Math.cos(Math.PI * i / -10))), (int)(yPoint * (1 - 2 * Math.sin(Math.PI * i / -10))));
      }
   }          
}