package com.mysoft.smart.message;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.Mapping;
import org.junit.Test;

import java.util.Date;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/13/2018 4:08 PM
 * @Version 1.0
 */
public class TestDozer {

    @Test
    public void testDozer() {
        Mapper mapper = new DozerBeanMapper();
        User user = new User();
        user.setId("12345678");
        user.setMobile(1389845562L);
        user.setDate(System.currentTimeMillis() + "");
        UserDto dto = mapper.map(user, UserDto.class);
        System.out.println(dto);
    }

    @Data
    public static class User {
        private String id;
        @Mapping("telphone")
        private Long mobile;
        private String date;
    }

    @Data
    public static class UserDto {
        private Long id;
        private String telphone;
        private Date date;
    }
}
