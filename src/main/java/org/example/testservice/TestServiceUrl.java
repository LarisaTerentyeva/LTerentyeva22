package org.example.testservice;
import org.aeonbits.owner.ConfigFactory;
import org.example.utils.config.ServiceConfig;

public class TestServiceUrl {

    ServiceConfig serviceConfig = ConfigFactory.create(ServiceConfig.class);

    public String url1 = serviceConfig.apiUrl1();
    public String url2 = serviceConfig.apiUrl2();


}
