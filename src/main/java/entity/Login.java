package entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * author  luhongtao
 * 2022/7/21 09:52:15
 **/
@Data
public class Login implements Serializable {

    private String username;

    private Date lastlogoutime;

    private int onlinetime;

    private int logcount;

    private Date logintime;

    private Date logouttime;


}
