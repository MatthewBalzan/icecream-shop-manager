////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BillTotalTest {

    private User user;
    private List<MenuItem> lista;
    private BillTotal billTotal;

    @Before
    public void setup(){
        user = new User("Matthew", "Balzan", LocalDate.of(1999,9,7));
        lista = new ArrayList<MenuItem>();
        billTotal = new BillTotal(lista, user, 1234, 3.00);
        lista.add(new MenuItem(MenuItem.item.Bevanda, "Cola", 3.00));
    }

    @Test
    public void getListaTest() {
        assertEquals(lista, billTotal.getLista());
    }

    @Test
    public void getUserTest() {
        assertEquals(user, billTotal.getUser());
    }

    @Test
    public void getTimeTest() {
        assertEquals(1234,billTotal.getTimeInSeconds());
    }

    @Test
    public void getPriceTest() {
        assertEquals(3.00,billTotal.getPrice(),0.01);
    }

}
