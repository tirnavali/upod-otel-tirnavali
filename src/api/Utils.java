package api;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String ,String> tableConverter = new HashMap<>();;
    static{
        tableConverter.put("otel","otels");
        tableConverter.put("user", "users");
        tableConverter.put("room", "rooms");
        tableConverter.put("customer", "customers");
        tableConverter.put("commonroomspeciality", "common_room_specialities");
        System.out.println("utils static loaded");

    }





    public static void main(String[] args) {
        ;
    }
}
