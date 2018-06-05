package it.db.test.miu;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class TestDateFormat {

    @Test
    void test() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

        System.out.println(fmt.format(new Date()));
        System.out.println(fmt.format(new Date()));
        System.out.println(fmt.format(new Date()));
        System.out.println(fmt.format(new Date()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fmt.format(new Date()));
        System.out.println(fmt.format(new Date()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fmt.format(new Date()));
        
        assert(true);
    }

}
