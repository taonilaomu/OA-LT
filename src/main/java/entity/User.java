package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author  luhongtao
 * 2022/7/25 10:01:42
 **/
@AllArgsConstructor
@Data
public class User {

    private String username;

    private String password;
}
