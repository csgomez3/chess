package model;

public record AuthData(String authToken, String username) {
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        AuthData comp = (AuthData)obj;
        return this.username.equals(comp.username) && this.authToken.equals(comp.authToken);
    }
}
