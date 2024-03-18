package ru.cherkashin.test.services;

import org.springframework.stereotype.Service;
import ru.cherkashin.test.dto.FieldDTO;
import ru.cherkashin.test.repository.TestFieldRepository;

import java.util.List;

@Service
public class TestFieldService {

    private final TestFieldRepository testFieldRepository;

    public TestFieldService(TestFieldRepository testFieldRepository) {
        this.testFieldRepository = testFieldRepository;
    }

    public void addValueOnCount(int count) {
        for (int i = 1; i <= count; i++) {
            testFieldRepository.addNewValue(i);
        }
    }

    public List<FieldDTO> getAllFields(){
        return testFieldRepository.getAllFields();
    }
}
