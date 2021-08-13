package id.universenetwork.utilities.Manager;

import id.universenetwork.utilities.Enums.Features.AddressWhitelister;

public class Proxy {
    private static String bungeeAddress;

    public static void setup() {
        setBungeeAddress(Config.AddressWhitelisterMessage(AddressWhitelister.ADDRESS));
    }

    public static void setBungeeAddress(String bungeeAddress) {
        Proxy.bungeeAddress = bungeeAddress;
    }

    public static String getBungeeAddress() {
        return bungeeAddress;
    }

    public static Boolean isValidAddress(String s) {
        if (!s.contains(".")) {
            return false;
        } else {
            String[] v1 = s.split(".");
            int v2 = v1.length;
            for (int v3 = 0; v3 < v2; ++v3) {
                String n = v1[v3];
                try {
                    Integer.valueOf(n);
                } catch (Exception v6) {
                    return false;
                }
            }
        }
        return true;
    }
}
