package edu.ifrs.tpack.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
/**
 * @description da classe aqui
 */

public class MockTest {

    @Mock
    /**
     * valor p HashMap
     */
    HashMap<String, Integer> mockHashMap;

    @Test
    /**
     * comentario sobre o uso desta classe
     */


    public void saveTest() {

        mockHashMap.put("A", 1);

        Mockito.verify(mockHashMap, times(1)).put("A", 1);
        Mockito.verify(mockHashMap, times(0)).get("A");

        assertEquals(0, mockHashMap.size());
    }

}
