package util;

public enum ChatType {
    Group("Group") , Single("Single");

    private final String value;

    ChatType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
