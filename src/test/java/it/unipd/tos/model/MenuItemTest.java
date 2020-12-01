////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {
    private MenuItem item;

    @Before
    public void setup(){
        item = new MenuItem(MenuItem.item.Bevanda, "Cola", 2.00);
    }

    @Test
    public void getNameTest() {
        assertEquals("Cola", item.getName());
    }

    @Test
    public void getPriceTest() {
        assertEquals(2.00, item.getPrice(),0.01);
    }

    @Test
    public void getItemTypeTest() {
        assertEquals(MenuItem.item.Bevanda, item.getItemType());
    }
}
