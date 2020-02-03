package by.beg.catalog.entity;

public enum ProductTypeEnum {
    PHONE("Телефон"), LAPTOP("Ноутбук"), PC("Компютер"), TABLET("Планшет");

    private String name;

    ProductTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
