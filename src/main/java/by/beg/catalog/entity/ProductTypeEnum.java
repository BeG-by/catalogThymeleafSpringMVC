package by.beg.catalog.entity;

import java.io.Serializable;

public enum ProductTypeEnum implements Serializable {

    PC("Компютер"), LAPTOP("Ноутбук"), PHONE("Телефон"), TABLET("Планшет");

    private String name;

    ProductTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
