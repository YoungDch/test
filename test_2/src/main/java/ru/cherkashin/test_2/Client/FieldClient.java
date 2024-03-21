package ru.cherkashin.test_2.Client;

import feign.Param;
import feign.RequestLine;
import ru.cherkashin.test_2.dto.FieldDTO;

import java.util.List;

public interface FieldClient {

    @RequestLine("POST /post/{count}")
    void postFields(@Param("count") int count);

    @RequestLine("GET /get")
    List<FieldDTO> getFields();


}
