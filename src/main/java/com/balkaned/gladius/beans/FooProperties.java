package com.balkaned.gladius.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.foo")
public class FooProperties {

    /**
     * IP of foo service used to blah.
     */
    private String url = "jdbc:postgresql://ec2-18-191-189-102.us-east-2.compute.amazonaws.com:5432/gladius_autenticacion";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}