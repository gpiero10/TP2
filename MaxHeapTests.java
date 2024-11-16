package aed;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class MaxHeapTests {

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
    void testInsert() {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insertar_elemento(100);
        maxHeap.insertar_elemento(150);
        maxHeap.insertar_elemento(200);

        assertEquals(200, maxHeap.sacar_primero());
        assertEquals(150, maxHeap.sacar_primero());
        
        maxHeap.insertar_elemento(10);
        maxHeap.insertar_elemento(99);

        assertEquals(100, maxHeap.sacar_primero());

        maxHeap.insertar_elemento(400);
        maxHeap.insertar_elemento(500);
        maxHeap.insertar_elemento(550);
        maxHeap.insertar_elemento(124);
        maxHeap.insertar_elemento(76);
        maxHeap.insertar_elemento(17);
        maxHeap.insertar_elemento(14);
        maxHeap.insertar_elemento(999);
        maxHeap.insertar_elemento(756);

        assertEquals(999, maxHeap.sacar_primero());
    }



    @Test
    void test_to_array() {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insertar_elemento(400);
        maxHeap.insertar_elemento(500);
        maxHeap.insertar_elemento(550);
        maxHeap.insertar_elemento(124);
        maxHeap.insertar_elemento(76);
        maxHeap.insertar_elemento(17);
        maxHeap.insertar_elemento(14);
        maxHeap.insertar_elemento(999);
        maxHeap.insertar_elemento(756);

        assertSetEquals(new ArrayList<>(Arrays.asList(999, 756, 550, 500, 76, 17, 14, 124, 400)), maxHeap.to_array());
    }

}
