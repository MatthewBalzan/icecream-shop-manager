////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayGelateria implements TakeAwayBill{
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user)
     throws TakeAwayBillException{
        Double totalPrice = 0.0;

        if(itemsOrdered == null) {
            throw new TakeAwayBillException("La lista non esiste"); 
        }

        if(itemsOrdered.contains(null)) {
            throw new TakeAwayBillException
                ("La lista contiene un elemento nullo"); 
        }

        for(MenuItem item : itemsOrdered) {
            totalPrice += item.getPrice(); 
        }

        return totalPrice;
    }
}