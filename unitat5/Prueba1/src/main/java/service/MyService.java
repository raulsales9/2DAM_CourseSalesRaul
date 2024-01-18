package service;

import com.example.prueba1.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private final AppProperties appProperties;

    @Autowired
    public MyService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public void printProperties() {
        String appName = appProperties.getName();
        String devName = appProperties.getDeveloperName();

        System.out.println("App Name: " + appName);
        System.out.println("Developer Name: " + devName);
    }
}
