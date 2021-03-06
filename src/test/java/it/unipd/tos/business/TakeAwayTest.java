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
import it.unipd.tos.model.BillTotal;

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

        lista.add(new MenuItem(MenuItem.item.Bevanda, "Cola", 3.00));
        lista.add(new MenuItem(MenuItem.item.Budino, "Pinguino", 1.50));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 6.00));

        totalPrice = manager.getOrderPrice(lista,user);

        assertEquals(10.5,totalPrice,0.01);
    }

    @Test(expected = TakeAwayBillException.class) 
    public void nullListTest() throws TakeAwayBillException{
        lista = null;

        totalPrice = manager.getOrderPrice(lista,user);
    }

    @Test(expected = TakeAwayBillException.class) 
    public void nullItemInListTest() throws TakeAwayBillException{

        lista.add(null);
        lista.add(new MenuItem(MenuItem.item.Budino, "Pinguino", 1.50));

        totalPrice = manager.getOrderPrice(lista,user);
    }

    @Test
    public void scontoDiPiuDi5GelatiTest() throws TakeAwayBillException{

        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 4.00));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 4.00));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Cioccolata", 2.00));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 4.00));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 4.00));
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 4.00));

        totalPrice = manager.getOrderPrice(lista,user);

        assertEquals(21.00,totalPrice,0.01);
    }

    @Test
    public void scontoDi10PerCentoTest() throws TakeAwayBillException{

        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 30.00));
        lista.add(new MenuItem(MenuItem.item.Budino, "Pinguino", 30.00));
        lista.add(new MenuItem(MenuItem.item.Bevanda, "Cola", 10.00));

        totalPrice = manager.getOrderPrice(lista,user);

        assertEquals(63.00,totalPrice,0.01);
    }

    @Test
    public void limit30itemsTest() throws TakeAwayBillException{

        for (int i = 0; i < 31; i++) {
            lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 1.00));
        }
        try{
            totalPrice = manager.getOrderPrice(lista,user);
        }catch(TakeAwayBillException e){
            assertEquals("La lista contiene piu' di 30 elementi", e.getMessage());
        }
        
    }

    @Test
    public void commissione050Test() throws TakeAwayBillException{

        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 1.10));
        
        totalPrice = manager.getOrderPrice(lista,user);

        assertEquals(1.60,totalPrice,0.01);
    }

    @Test
    public void ordiniGratisMinorennitra18e19Test() throws TakeAwayBillException{
        List<BillTotal> ordinazioni = new ArrayList<BillTotal>();
        lista.add(new MenuItem(MenuItem.item.Gelato, "Coppa Nafta", 1.00));

        User user = null;
        for (int i = 0; i < 12; i++) {
            user = new User("Matthew", "Balzan", LocalDate.of(2006,1,i+1));
            ordinazioni.add(new BillTotal(lista, user,  66600, manager.getOrderPrice(lista, user)));
        }

        List<BillTotal> ordinazioniFree = manager.getFreeBills(ordinazioni);

        assertEquals(10, ordinazioniFree.size());
        
        for (BillTotal i : ordinazioniFree) {
            assertEquals(0.0, i.getPrice(),0.01);
        }
    }

} 