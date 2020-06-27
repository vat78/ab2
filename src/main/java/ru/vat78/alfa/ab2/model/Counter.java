package ru.vat78.alfa.ab2.model;

public class Counter {
    private Integer counter = Integer.valueOf(0);

    public Counter increase() {
        counter = Integer.valueOf(counter.intValue() + 1);
        return this;
    }

    public int getCounter() {
        return counter;
    }
}
