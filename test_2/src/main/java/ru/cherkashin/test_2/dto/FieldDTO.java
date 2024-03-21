package ru.cherkashin.test_2.dto;

public class FieldDTO {
    private int field;

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "FieldDTO{" +
                "field=" + field +
                '}';
    }
}
