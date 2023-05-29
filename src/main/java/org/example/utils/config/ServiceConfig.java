package org.example.utils.config;
import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface ServiceConfig extends Config {
    @Config.Key("api_url_1")
    String apiUrl1();

    @Config.Key("api_url_2")
    String apiUrl2();

}