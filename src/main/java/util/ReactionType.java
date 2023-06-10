package util;

public enum ReactionType {
    VIEW("VIEW") , LIKE("LIKE");

    private final String value;

    ReactionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
