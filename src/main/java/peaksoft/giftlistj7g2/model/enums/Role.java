package peaksoft.giftlistj7g2.model.enums;

public enum Role {
    ADMIN,
    USER;

    public String getAuthority() {
        return name();
    }
}
