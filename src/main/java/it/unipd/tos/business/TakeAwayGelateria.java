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
        int nGelati = 0;
        MenuItem gelatoMenoCostoso = null;

        if(itemsOrdered == null) {
            throw new TakeAwayBillException("La lista non esiste"); 
        }

        if(itemsOrdered.contains(null)) {
            throw new TakeAwayBillException
                ("La lista contiene un elemento nullo"); 
        }

        for(MenuItem i : itemsOrdered) {
            totalPrice += i.getPrice();
            if(i.getItemType() == MenuItem.item.Gelato){
                nGelati++;
                if(gelatoMenoCostoso==null || gelatoMenoCostoso.getPrice()>i.getPrice()){
                    gelatoMenoCostoso = i;
                }
            } 
        }

        //sconto sul gelato meno costoso se presi +5 gelati
        if(nGelati>5){
            totalPrice -= 0.5 * gelatoMenoCostoso.getPrice();
        }

        return totalPrice;
    }
}