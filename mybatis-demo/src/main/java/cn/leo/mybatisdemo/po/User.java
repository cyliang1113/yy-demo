package cn.leo.mybatisdemo.po;

import lombok.Data;

import java.util.Date;

/**
 * 用户
 */

@Data
public class User {

    private Long id; //ID

    private String name; //姓名

    private Integer age;

    private String address;

    private String memo; //备注

    private Date createTime; //创建时间

}
