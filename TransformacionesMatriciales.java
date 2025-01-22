package draw_primitives;

/**Actividad. Implementación de transformaciones compuestas 
 
1. Hacer una clase que contenga los métodos necesarios para generar la composición de transformaciones de un objeto bidimensional. 
 
Entre los métodos a diseñar están:  
 
(opcional, se puede utilizar el método toRadians() de la clase Math)
 public double radianes(double g);	 
 Método que convierte grados a radianes.	  
*/
public class TransformacionesMatriciales{
   public double h;
   
   public TransformacionesMatriciales(){
      h = 1;
   }
   
   public TransformacionesMatriciales(double h){
      this.h = h;
   }
   
   public void printMatrix(double[][] x){
      for (int i = 0; i  < x.length; i++){
         System.out.print("|   ");
         for (int j = 0; j  < x[0].length; j++){
            System.out.print(x[i][j] + "   ");
         }
         System.out.print("   |\n");
      }
   }
   public double[][] multiplicar(double[][] x, double[][] y){
      double[][] r = new double[x.length][y[0].length];
      
      if (x[0].length != y.length) return r;
      
      for(int i = 0; i < x.length; i++){
         for(int j = 0; j < y[0].length; j++){
            r[i][j] = 0;
            
            for(int k = 0; k < y.length; k++){
               r[i][j] += x[i][k] * y[k][j];
            }
         }
      }
      return r;
   }
   
   public double[] multiplicarMatrizPorVector(double[][] x, double[] y){
      double[] r = new double[x.length];
      
      if (x[0].length != y.length) return r;
      
      for(int i = 0; i < x.length; i++){
         r[i] = 0;
            
         for(int k = 0; k < y.length; k++){
            r[i] += x[i][k] * y[k];
         }
      }//for-i
      return r;
   }
   
   /*public double[] cartesianToHomogeneous(int x, int y, double h){
      return new double[]{x * h,y * h, h};
   }
   
   public int[] homogeneousToCartesian(double[] h){
      return new int[]{(int)(h[0] / h[2]), (int)(h[1] / h[2])};
   }*/
      
   public double[][] T(double tx, double ty){
      return new double[][]{
         {1,0,tx},
         {0,1,ty},
         {0,0,1}
      };
   }
   
   public double[][] TInv(double tx, double ty){
      return T(-1 * tx, -1 * ty);
   }
   
   public double[][] S(double sx, double sy){
      return new double[][]{
         {sx,0, 0},
         {0, sy,0},
         {0, 0, 1}
      };
   }
   
   public double[][] S(double xf, double yf, double sx, double sy){
      return new double[][]{
         {sx,0, sx * ( - xf) + xf},
         {0, sy, sy * (-yf) + yf},
         {0, 0, 1}
      };
   }

   
   public double[][] SInv(double sx, double sy){
      return S(1f/sx,1f/sy);
   }
   
   public double[][] R(double theta){
      theta = Math.toRadians(theta);
      return new double[][]{
         {Math.cos(theta),-1 * Math.sin(theta), 0},
         {Math.sin(theta),     Math.cos(theta), 0},
         {0,                          0,                      1}
      };
   }
   
   public double[][] R(double xp, double yp, double theta){
      theta = Math.toRadians(theta);
      return new double[][]{
         {Math.cos(theta),-1 * Math.sin(theta), xp*(1-Math.cos(theta)) + yp*Math.sin(theta)},
         {Math.sin(theta),     Math.cos(theta), yp*(1-Math.cos(theta)) - xp*Math.sin(theta)},
         {0,                          0,                      1                                          }
      };
   }

   
   public double[][] RInv(double theta){
      return R(-1 * theta);
   }
   
   public double [][] matrizPuntos(double x[],double y[]){
      int length = x.length;
      if(y.length < x.length) length = y.length;
      
      double[][] p = new double[3][length];
      
      for (int i = 0; i < length; i++){
         p[0][i] = x[i];
         p[1][i] = y[i];
         p[2][i] = h;
      }
      
      return p;
   }
   
   public double [][] matrizPuntosInt(int x[],int y[]){
      int length = x.length;
      if(y.length < x.length) length = y.length;
      
      double[][] p = new double[3][length];
      
      for (int i = 0; i < length; i++){
         p[0][i] = x[i] * h;
         p[1][i] = y[i] * h;
         p[2][i] = h;
      }
      
      return p;
   }

   
    /*Método que recibe la matriz de puntos homogénea y 
    el renglón de las coordenadas que se desea obtener
    (0 para x, 1 para y). Regresa un arreglo con las 
    coordenadas bien sea de x o de y según sea el caso,
    y el cual se utilizará para dibujar el objeto 
    gráfico.*/
   public int [] actualizarPuntos(double [][]puntos,int coord){
      int[] p = new int[puntos[0].length];
      coord = coord % 2;
      for(int i = 0; i < p.length; i++){
         p[i] = (int)Math.round(puntos[coord][i] / h);
      }
      
      return p;
   }
  

   
   private final double[][] MATRIX_REFLECTION_X = new double[][]{
      {1,  0, 0},
      {0, -1, 0},
      {0,  0, 1}
   };
   
   public double[][] RefX(){
      return MATRIX_REFLECTION_X;
   }
   
   private final double[][] MATRIX_REFLECTION_Y = new double[][]{
      {-1, 0, 0},
      { 0, 1, 0},
      { 0, 0, 1}
   };
   
   public double[][] RefY(){
      return MATRIX_REFLECTION_Y;
   }
   
   private final double[][] MATRIX_REFLECTION_XY = new double[][]{
      {-1,  0, 0},
      { 0, -1, 0},
      { 0,  0, 1}
   };
   
   public double[][] RefOrigen(){
      return MATRIX_REFLECTION_XY;
   }
   
   private final double[][] MATRIX_REFLECTION_45DEG = new double[][]{
      {0, 1, 0},
      {1, 0, 0},
      {0, 0, 1}
   };
   
   public double[][] RefYigualX(){
      return MATRIX_REFLECTION_45DEG;
   }
   
   private final double[][] MATRIX_REFLECTION_315DEG = new double[][]{
      { 0, -1, 0},
      {-1,  0, 0},
      { 0,  0, 1}
   };
   
   public double[][] RefYigualMenosX(){
      return MATRIX_REFLECTION_315DEG;
   }
   
   public double[][] CXLinea(double shx, int yRef){
      return new double[][]{
         {1,shx,-1 * shx * yRef},
         {0,  1,              0},
         {0,  0,              1}
      };
   }
   
   public double[][] CX(double shx){
      return CXLinea(shx,0);
   }
   
   public double[][] CYLinea(double shy, double xRef){
      return new double[][]{
         {  1,  0,               0},
         {shy,  1, -1 * shy * xRef},
         {  0,  0,               1}
      };
   }
   
   public double[][] CY(double shy){
      return CYLinea(shy,0);
   }
}//class