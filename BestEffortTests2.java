package aed;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BestEffortTests2 {

  int cantCiudades;
  Traslado[] listaTraslados;
  ArrayList<Integer> actual;


  @BeforeEach
  void init(){
      //Reiniciamos los valores de las ciudades y traslados antes de cada test
      cantCiudades = 6;
      listaTraslados = new Traslado[] {           
                                          new Traslado(7578, 4, 1, 910, 100),
                                          new Traslado(8564, 3, 5, 909, 90),
                                          new Traslado(1854, 2, 0, 908, 80),
                                          
                                          new Traslado(113, 1, 0, 907, 70),
                                          
                                          
                                          new Traslado(7894, 2, 3, 888, 60),
                                          new Traslado(156, 5, 0, 499, 50),
                                          new Traslado(417, 5, 3, 498, 40),
                                          
                                          
                                          new Traslado(7451, 1, 4, 450, 30),
                                          new Traslado(417, 2, 3, 140, 20),
                                          new Traslado(7451, 3, 4, 100, 10)
                                      };
  }

  void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
      assertEquals(s1.size(), s2.size());
      for (int e1 : s1) {
          boolean encontrado = false;
          for (int e2 : s2) {
              if (e1 == e2) encontrado = true;
          }
          assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
      }
  }

  @Test
  void despachar_con_mas_ganancia_de_a_varios(){
      BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

      sis.despacharMasRedituables(4);

      assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorPerdida());

      sis.despacharMasRedituables(3);

      assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorPerdida());

  }
  
  
  @Test
  void despachar_mas_viejo_de_a_varios(){
      BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
      
      sis.despacharMasAntiguos(4);
      assertSetEquals(new ArrayList<>(Arrays.asList(5)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

      sis.despacharMasAntiguos(3);
      assertSetEquals(new ArrayList<>(Arrays.asList(5)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
      
  }
  
  @Test
  void despachar_mixtos(){
      BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

      sis.despacharMasRedituables(3);
      sis.despacharMasAntiguos(3);
      assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());

      sis.despacharMasAntiguos(1);
      assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());
      
  }
   
  @Test
  void agregar_traslados(){
      BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

      Traslado[] nuevos = new Traslado[] {
          new Traslado(11, 4, 2, 1000, 49),
          new Traslado(12, 0, 1, 400, 39),
          new Traslado(13, 0, 1, 500, 29),
          new Traslado(14, 5, 1, 150, 19),
          new Traslado(15, 1, 0, 150, 9)
      };

      sis.registrarTraslados(nuevos);

      sis.despacharMasAntiguos(4);
      assertSetEquals(new ArrayList<>(Arrays.asList(1,5)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(1,0)), sis.ciudadesConMayorPerdida());

      sis.despacharMasRedituables(1);
      assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
      assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());

  }
  

  @Test
  void promedio_por_traslado(){
      BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

      sis.despacharMasAntiguos(3);
      assertEquals(230, sis.gananciaPromedioPorTraslado());

      sis.despacharMasRedituables(3);
      assertEquals(569, sis.gananciaPromedioPorTraslado());

      Traslado[] nuevos = new Traslado[] {
          new Traslado(8, 1, 2, 202, 5),
          new Traslado(12, 2, 1, 100000, 1)
      };

      sis.registrarTraslados(nuevos);
      sis.despacharMasRedituables(5);

      assertEquals(9655, sis.gananciaPromedioPorTraslado());
      

  }
      
  @Test
  void mayor_superavit(){
      Traslado[] nuevos = new Traslado[] {
          new Traslado(111, 3, 4, 1, 107),
          
          new Traslado(777, 4, 5, 40, 106),
          new Traslado(666, 5, 2, 3, 105),
          new Traslado(6564, 1, 5, 120, 104),
          
          new Traslado(333, 3, 4, 60, 103),
          new Traslado(444, 1, 2, 30, 102),
          new Traslado(555, 2, 1, 90, 101)
      };
      BestEffort sis = new BestEffort(this.cantCiudades, nuevos);

      sis.despacharMasAntiguos(1);
      assertEquals(2, sis.ciudadConMayorSuperavit());

      sis.despacharMasAntiguos(2);
      assertEquals(2, sis.ciudadConMayorSuperavit());
      
      sis.despacharMasAntiguos(3);
      assertEquals(1, sis.ciudadConMayorSuperavit());

      sis.despacharMasAntiguos(1);
      assertEquals(3, sis.ciudadConMayorSuperavit());

  }
}
