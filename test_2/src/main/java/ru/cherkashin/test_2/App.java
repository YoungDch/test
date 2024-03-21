package ru.cherkashin.test_2;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.cherkashin.test_2.Client.FieldClient;
import ru.cherkashin.test_2.dto.FieldDTO;
import ru.cherkashin.test_2.services.FeignClientService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class App
{
    public static void main( String[] args ) throws IOException {
        FieldClient fieldClient = FeignClientService.getInstance();
        fieldClient.postFields(1000000);

        String path = "D:\\example.txt";
        ObjectMapper om = new ObjectMapper();

        writeFile(path, om.writeValueAsString(fieldClient.getFields()).getBytes());
        String valueJson = readFile(path);

        JsonFactory factory = new JsonFactory();
        JsonParser parser  = factory.createParser(valueJson);

        MappingIterator<FieldDTO[]> mappingIterator = om.readValues(parser, FieldDTO[].class);
        var list = mappingIterator.nextValue();

        AtomicInteger result = new AtomicInteger();
        Arrays.stream(list).forEach(fieldDTO -> result.addAndGet(fieldDTO.getField()));
        System.out.println(result.get());
    }

    public static void writeFile(String path, byte[] byteValue){
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)){
            fileOutputStream.write(byteValue);
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String path){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(path)){
            int i;
            while ((i = fileInputStream.read()) != -1){
                stringBuilder.append((char)i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }


}
