package util;

public enum Role {
    Business("Business"), Normal("Normal");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
