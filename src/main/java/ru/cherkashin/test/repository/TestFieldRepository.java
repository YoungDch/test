package ru.cherkashin.test.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.cherkashin.test.dto.FieldDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TestFieldRepository {

    private final JdbcTemplate jdbc;

    public TestFieldRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public void addNewValue(int value){
        jdbc.update("INSERT INTO TEST VALUES(?)",value);
    }

    public List<FieldDTO> getAllFields(){
        return jdbc.query("SELECT * FROM test", new RowMapper<FieldDTO>() {
            @Override
            public FieldDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                FieldDTO fieldDTO = new FieldDTO();
                fieldDTO.setField(rs.getInt("field"));
                return fieldDTO;
            }
        });
    }



}
