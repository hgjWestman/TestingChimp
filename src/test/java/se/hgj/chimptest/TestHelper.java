package se.hgj.chimptest;

import java.text.SimpleDateFormat;

import static org.apache.commons.lang3.StringUtils.replace;

public class TestHelper {

    // använd timestamp för att få till unika användarnamn och mailadresser
    static String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());


    public static String uniqify(String input) {
        String output = replace(input, "*u*", timeStamp);
        return output;
    }
}
