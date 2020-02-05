package by.beg.catalog.entity;

public enum ProductTypeEnum {
    PC("Компютер"), LAPTOP("Ноутбук"), PHONE("Телефон"), TABLET("Планшет");

    private String name;

    ProductTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
