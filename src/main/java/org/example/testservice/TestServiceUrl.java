package org.example.testservice;
import org.aeonbits.owner.ConfigFactory;
import org.example.utils.config.ServiceConfig;
import org.aeonbits.owner.Config;

public class TestServiceUrl1 {

    ServiceConfig serviceConfig = ConfigFactory.create(ServiceConfig.class);

    String url1 = ServiceConfig.apiUrl1();
    String url2 = ServiceConfig.apiUrl2();


}
