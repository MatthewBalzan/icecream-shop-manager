////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayTest {

    TakeAwayGelateria manager;
    double totalPrice;
    List<MenuItem> lista;
    User user;

    @Before
    public void setup() {
        manager = new TakeAwayGelateria();
        totalPrice = 0.0;
        lista = new ArrayList<MenuItem>();
        user = new User("Matthew", "Balzan", LocalDate.of(1998,5,5));
    }


     @Test
    public void simpleSumTest() throws TakeAwayBillException{
        List<MenuItem> lista = new ArrayList<MenuItem>();

        lista.add(new MenuItem(MenuItem.item.Bevanda, "Cola", 3.00));
        lista.add(new MenuItem(MenuItem.item.Budino, "Pinguino", 1.50));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 6.00));

        totalPrice = manager.getOrderPrice(lista,user);

        assertEquals(10.5,totalPrice,0.01);
    }

    @Test(expected = TakeAwayBillException.class) 
    public void nullListTest() throws TakeAwayBillException{
        List<MenuItem> lista = null;

        totalPrice = manager.getOrderPrice(lista,user);
    }

    @Test(expected = TakeAwayBillException.class) 
    public void nullItemInListTest() throws TakeAwayBillException{
        List<MenuItem> lista = new ArrayList<MenuItem>();

        lista.add(null);
        lista.add(new MenuItem(MenuItem.item.Budino, "Pinguino", 1.50));

        totalPrice = manager.getOrderPrice(lista,user);
    }

} 