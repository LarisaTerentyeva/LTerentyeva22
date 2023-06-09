//package org.example.utils.config;
//
//import org.aeonbits.owner.ConfigFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Config {
//    private Config() {
//        throw new IllegalStateException("Utility class");
//    }
//
//    public static Map<String, String> getEnvironment() {
//        String environment = System.getProperty("env");
//
//        if (environment == null) {
//            System.setProperty("env", "test");
//        }
//
//        Map<String, String> apiEnv = new HashMap<>();
//        apiEnv.put("env", System.getProperty("env"));
//
//        return apiEnv;
//    }
//
//    public static ServiceConfig getServiceConfig() {
//        return ConfigFactory.create(ServiceConfig.class, getEnvironment());
//    }
//
//}