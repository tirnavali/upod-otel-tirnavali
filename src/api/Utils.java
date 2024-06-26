package api;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Utils {
    public static Map<String ,String> tableConverter = new HashMap<>();
    static{
        tableConverter.put("otel","otels");
        tableConverter.put("user", "users");
        tableConverter.put("room", "rooms");
        tableConverter.put("customer", "customers");
        tableConverter.put("commonroomspeciality", "common_room_specialities");
        tableConverter.put("roomservice", "room_services");
        tableConverter.put("reservation", "reservations");
        tableConverter.put("reservationroomservice", "reservations_room_services");
//        System.out.println("utils static loaded");
    }

    public static String generateMethodNameFromColName(String colName){

        var firstLetter = colName.substring(0,1).toUpperCase(Locale.ENGLISH);
        var otherLetters = colName.substring(1);
        var metName = "set"+firstLetter + otherLetters;
        return metName;
//        System.out.println("Met name: " + metName);
    }

    public static Class generateTypeFromString(String input) {
        switch (input){
            case "INT" : return  int.class;
            case "VARCHAR" : return String.class;
            case "DOUBLE" : return double.class;
            case "DATETIME" : return java.sql.Date.class;
        }
        return null;
    }
}