package br.com.zupacademy.breno.mercadolivre.authentications;

public class LoginUserResponse {

    private String token;
    private String type;

    public LoginUserResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
