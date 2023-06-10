package util;

public enum PostType {
    NORMAL("NORMAL"), STORE("STORE"), FOOD("FOOD"), SHOP("SHOP"), CAR("CAR");

    private String value;

    PostType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
