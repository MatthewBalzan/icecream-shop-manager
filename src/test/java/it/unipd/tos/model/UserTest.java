////////////////////////////////////////////////////////////////////
// [Matthew] [Balzan] [1193093]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.Period;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setup(){
        user = new User("Matthew", "Balzan", LocalDate.of(1999,9,7));
    }

    @Test
    public void getFirstNameTest() {
        assertEquals("Matthew", user.getFname());
    }

    @Test
    public void getSurnameTest() {
        assertEquals("Balzan", user.getSname());
    }

    @Test
    public void getBirthdateTest() {
        assertEquals(LocalDate.of(1999,9,7), user.getBirthDate());
    }

    @Test
    public void getAgeTest() {
        int age = Period.between(LocalDate.of(1999,9,7), LocalDate.now()).getYears();
        assertEquals(age, user.age());
    }

    @Test
    public void userCreationWithNullBirthDateTest(){
        User user = new User("Matthew", "Balzan", null);

        assertEquals(0, user.age());
    }

}
