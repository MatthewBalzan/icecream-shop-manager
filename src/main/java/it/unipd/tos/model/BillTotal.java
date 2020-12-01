////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.util.List;

public class BillTotal {
    private List<MenuItem> lista;
    private User user;
    private int timeInSeconds;
    private double price;

    public BillTotal(List<MenuItem> lista, User user, int timeInSeconds, double price) {
        this.lista = lista;
        this.user = user;
        this.timeInSeconds = timeInSeconds;
        this.price = price;
    }

    public List<MenuItem> getLista() {
        return lista;
    }

    public User getUser() {
        return user;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double p){
        price = p;
    }

}
