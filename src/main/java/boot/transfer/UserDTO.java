package boot.transfer;

import lombok.Data;

@Data
public class UserDTO {

    private int id;

    private String login;

    private String password;

    private String name;

    private String role;

    public UserDTO(int id, String login, String password, String name, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public UserDTO(){}
}
