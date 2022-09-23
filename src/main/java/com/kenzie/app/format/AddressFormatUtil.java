package com.kenzie.app.format;

import java.util.HashMap;

public class AddressFormatUtil {
    private static HashMap<String, String> codeTable = new HashMap<>();
    private static HashMap<String, String> cityCodeTable = new HashMap<>();

    // runs automatically when class is loaded
    static {
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("RD", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("Ave.", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("AVE", "AVENUE");

        //city code table
        cityCodeTable.put("ST", "SAINT");
        cityCodeTable.put("St.", "SAINT");
        cityCodeTable.put("St", "SAINT");
        cityCodeTable.put("ST.", "SAINT");
    }
    public static void initCodeTable() {
        //enter values into hashmap
        codeTable.put("ST", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("RD", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("Ave.", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("AVE", "AVENUE");
    }

    //123 Main St. (input)
    //123 Main STREET (output)
    public static String replaceAbbreviaton(String input) {
        String result = input;

        for (String currentKey : codeTable.keySet()) {
            if (input.contains(currentKey)) {
                result = input.replace(currentKey, codeTable.get(currentKey));
            }
        }
        return result;
    }
    public static String formatAddress(String input) {
        return input.toUpperCase();
    }
    public static String formatStreetAddress(String input) {
        return formatAddress(replaceAbbreviaton(input));
    }

//    public static void main(String[] args) {
//        initCodeTable();
//        System.out.println(replaceAbbreviaton("123 Main St."));
//        System.out.println(replaceAbbreviaton("12345 Something Rd."));
//    }
}
