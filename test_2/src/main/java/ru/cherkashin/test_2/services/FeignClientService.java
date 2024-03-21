package ru.cherkashin.test_2.services;

import feign.Feign;
import feign.Request;
import feign.gson.GsonDecoder;
import ru.cherkashin.test_2.Client.FieldClient;

import java.util.concurrent.TimeUnit;

public class FeignClientService {

    private static FieldClient fieldClient;

    public static FieldClient getInstance(){
        if(fieldClient == null){
            fieldClient = Feign.builder()
                    .decoder(new GsonDecoder())
                    .options(new Request.Options(10, TimeUnit.SECONDS, 10000, TimeUnit.SECONDS, false))
                    .target(FieldClient.class, "http://localhost:8080");
        }
        return fieldClient;
    }

}
