////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.BillTotal;
import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayGelateria implements TakeAwayBill{
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user)
     throws TakeAwayBillException{
        Double totalPrice = 0.0;
        Double eatingPrice = 0.0;
        int nGelati = 0;
        MenuItem gelatoMenoCostoso = null;

        if(itemsOrdered == null) {
            throw new TakeAwayBillException("La lista non esiste"); 
        }

        if(itemsOrdered.contains(null)) {
            throw new TakeAwayBillException
                ("La lista contiene un elemento nullo"); 
        }

        if(itemsOrdered.size()>30){
            throw new TakeAwayBillException
                ("La lista contiene piu' di 30 elementi"); 
        }

        for(MenuItem i : itemsOrdered) {
            totalPrice += i.getPrice();
            if(i.getItemType() == MenuItem.item.Gelato){
                nGelati++;
                if(gelatoMenoCostoso==null || gelatoMenoCostoso.getPrice()>i.getPrice()){
                    gelatoMenoCostoso = i;
                }
            } 
            if(i.getItemType() != MenuItem.item.Bevanda){
                eatingPrice += i.getPrice();
            }
        }

        //sconto sul gelato meno costoso se presi +5 gelati
        if(nGelati>5){
            totalPrice -= 0.5 * gelatoMenoCostoso.getPrice();
        }

        //sconto 10% se le ordinazione di cibi e' > 50€
        if(eatingPrice>50){
            totalPrice -= 0.1 * totalPrice;
        }

        //commissione di 0.50€ per ordini < 10€
        if(totalPrice<10){
            totalPrice += 0.50;
        }

        return totalPrice;
    }

    public List<BillTotal> getFreeBills(List<BillTotal> bills){

        List<BillTotal> free = new ArrayList<BillTotal>();

        for (int i = 0; i < bills.size(); i++) {
            if(bills.get(i).getUser().age()<18 &&
             !free.contains(bills.get(i)) &&
             bills.get(i).getTimeInSeconds()> 64800 &&
             bills.get(i).getTimeInSeconds()< 68400)
            {
                free.add(bills.get(i));
            }
        }

        if(free.size()>10){
            long seed = System.nanoTime();
            Collections.shuffle(free, new Random(seed));

            free = free.subList(0, 10);
            for (BillTotal i : free) {
                i.setPrice(0.0);
            }
        }

        return free;
    }
}