package fr.istic.vv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DateTest {
    // public static void main(String[] args) {
    //     Date d1 = new Date(28, 2, 2012);
    //     Date d2 = d1.nextDate();
    //     System.out.println("d1 : "+d1.getDay()+"/"+d1.getMonth()+ "/"+d1.getYear());
    //     System.out.println("d2 : "+d2.getDay()+"/"+d2.getMonth()+ "/"+d2.getYear());
    //     System.out.println(d1.compareTo(d2));
    // }

    @Test
    public void testConstructorValidDate() {
        Date date = new Date(15, 8, 2021);
        assertEquals(15, date.getDay());
        assertEquals(8, date.getMonth());
        assertEquals(2021, date.getYear());
    }

    @Test
    public void testConstructorInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> {new Date(-1, 5, 2021);});
        assertThrows(IllegalArgumentException.class, () -> {new Date(5, 0, 2021);});
        assertThrows(IllegalArgumentException.class, () -> {new Date(32, 1, 2021);});
        assertThrows(IllegalArgumentException.class, () -> {new Date(1, 13, 2021);});
        assertThrows(IllegalArgumentException.class, () -> {new Date(31, 4, 2021);});
    }



    @Test
    public void testConstructorFevrier() {
        Date date = new Date(29, 2, 2020); // 2020 est une année bissextile
        assertEquals(29, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertThrows(IllegalArgumentException.class, () -> {new Date(29, 2, 2021);});
        assertThrows(IllegalArgumentException.class, () -> {new Date(30, 2, 2020);});
    }

    @Test
    public void testIsLeapYear(){
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(1, 1, 2023);
        assertTrue(Date.isLeapYear(date1.getYear()));
        assertFalse(Date.isLeapYear(date2.getYear()));
    }

    @Test
    public void testNextDate() {
        Date date1 = new Date(28, 2, 2021); 
        Date nextDate1 = date1.nextDate();
        assertEquals(1, nextDate1.getDay());
        assertEquals(3, nextDate1.getMonth());
        assertEquals(2021, nextDate1.getYear());

        Date date2 = new Date(29, 2, 2020); 
        Date nextDate2 = date2.nextDate();
        assertEquals(1, nextDate2.getDay());
        assertEquals(3, nextDate2.getMonth());
        assertEquals(2020, nextDate2.getYear());

        Date date3 = new Date(31, 12, 2021); 
        Date nextDate3 = date3.nextDate();
        assertEquals(1, nextDate3.getDay());
        assertEquals(1, nextDate3.getMonth());
        assertEquals(2022, nextDate3.getYear());

        Date date4 = new Date(30, 7, 2024); 
        Date nextDate4 = date4.nextDate();
        assertEquals(31, nextDate4.getDay());
        assertEquals(7, nextDate4.getMonth());
        assertEquals(2024, nextDate4.getYear());
    }

    @Test
    public void testPreviousDate() {
        Date date1 = new Date(1, 3, 2021);
        Date previousDate1 = date1.previousDate();
        assertEquals(28, previousDate1.getDay());
        assertEquals(2, previousDate1.getMonth());
        assertEquals(2021, previousDate1.getYear());

        Date date2 = new Date(1, 3, 2020);
        Date previousDate2 = date2.previousDate();
        assertEquals(29, previousDate2.getDay());
        assertEquals(2, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        Date date3 = new Date(1, 1, 2022);
        Date previousDate3 = date3.previousDate();
        assertEquals(31, previousDate3.getDay());
        assertEquals(12, previousDate3.getMonth());
        assertEquals(2021, previousDate3.getYear());

        Date date4 = new Date(1, 8, 2024);
        Date previousDate4 = date4.previousDate();
        assertEquals(31, previousDate4.getDay());
        assertEquals(7, previousDate4.getMonth());
        assertEquals(2024, previousDate4.getYear());
    }

    @Test
    public void testCompareTo() {
        Date date1 = new Date(5, 5, 2022);
        Date date2 = new Date(4, 5, 2022);
        assertEquals(1, date1.compareTo(date2));

        date1 = new Date(4, 5, 2022);
        date2 = new Date(4, 5, 2022);
        assertEquals(0, date1.compareTo(date2));

        date1 = new Date(3, 5, 2022);
        date2 = new Date(4, 5, 2022);
        assertEquals(-1, date1.compareTo(date2));
    }

    @Test
    public void testPourAmeliorerLaCouverture(){
        Date date1 = new Date(30, 4, 2022);
        Date nextDate1 = date1.nextDate();
        assertEquals(1, nextDate1.getDay());
        assertEquals(5, nextDate1.getMonth());
        assertEquals(2022, nextDate1.getYear());

        date1 = new Date(15, 4, 2022);
        nextDate1 = date1.nextDate();
        assertEquals(16, nextDate1.getDay());
        assertEquals(4, nextDate1.getMonth());
        assertEquals(2022, nextDate1.getYear());

        date1 = new Date(31, 10, 2021); 
        nextDate1 = date1.nextDate();
        assertEquals(1, nextDate1.getDay());
        assertEquals(11, nextDate1.getMonth());
        assertEquals(2021, nextDate1.getYear());

        date1 = new Date(13, 2, 2020); 
        nextDate1 = date1.nextDate();
        assertEquals(14, nextDate1.getDay());
        assertEquals(2, nextDate1.getMonth());
        assertEquals(2020, nextDate1.getYear());

        
        date1 = new Date(13, 2, 2021); 
        nextDate1 = date1.nextDate();
        assertEquals(14, nextDate1.getDay());
        assertEquals(2, nextDate1.getMonth());
        assertEquals(2021, nextDate1.getYear());


        Date date2 = new Date(1, 6, 2020);
        Date previousDate2 = date2.previousDate();
        assertEquals(31, previousDate2.getDay());
        assertEquals(5, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        date2 = new Date(15, 6, 2020);
        previousDate2 = date2.previousDate();
        assertEquals(14, previousDate2.getDay());
        assertEquals(6, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        date2 = new Date(1, 7, 2020);
        previousDate2 = date2.previousDate();
        assertEquals(30, previousDate2.getDay());
        assertEquals(6, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        date2 = new Date(15, 7, 2020);
        previousDate2 = date2.previousDate();
        assertEquals(14, previousDate2.getDay());
        assertEquals(7, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        date2 = new Date(1, 2, 2020);
        previousDate2 = date2.previousDate();
        assertEquals(31, previousDate2.getDay());
        assertEquals(1, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        date2 = new Date(8, 2, 2020);
        previousDate2 = date2.previousDate();
        assertEquals(7, previousDate2.getDay());
        assertEquals(2, previousDate2.getMonth());
        assertEquals(2020, previousDate2.getYear());

        
        final Date date99 = new Date(1, 1, 2022);
        assertThrows(NullPointerException.class, () -> date99.compareTo(null));
        
        date1 = new Date(2, 2, 2022);
        date2 = new Date(2, 2, 2022);
        assertEquals(0, date1.compareTo(date2));

        Date date3 = new Date(2, 2, 2023);
        assertEquals(1, date3.compareTo(date2));

        Date date4 = new Date(2, 2, 2021);
        assertEquals(-1, date4.compareTo(date2));

        Date date5 = new Date(1, 3, 2022);
        assertEquals(1, date5.compareTo(date2));

        Date date6 = new Date(2, 1, 2022);
        assertEquals(-1, date6.compareTo(date2));

        Date date7 = new Date(3, 2, 2022);
        assertEquals(1, date7.compareTo(date2));

        Date date8 = new Date(1, 2, 2022);
        assertEquals(-1, date8.compareTo(date2));

    }

}