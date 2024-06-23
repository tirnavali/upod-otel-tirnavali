package api.dao.test;

import api.Utils;

public class UtilTest {
    public static void main(String[] args) {
        new Utils();
        System.out.println(Utils.tableConverter.get("otel"));
        System.out.println(Utils.tableConverter.get("user"));
    }
}
