////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String fname;
    private String sname;
    private LocalDate birthDate;

    public User(String fname, String sname, LocalDate birthdDate) {
        this.fname = fname;
        this.sname = sname;
        this.birthDate = birthdDate;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public int age() {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }
    
}
