package com.mysoft.smart.message;

import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.Mapping;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
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
        BeanMappingBuilder beanMappingBuilder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                String dateFormat = "yyyyMMddHHmmss";
                mapping(User.class,
                        UserDto.class,
                        TypeMappingOptions.wildcard(true),
                        TypeMappingOptions.dateFormat(dateFormat)).
                        fields("date", "date");
            }
        };
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(beanMappingBuilder);

        User user = new User();
        user.setId("12345678");
        user.setMobile(1389845562L);
        user.setDate("20180913165610");
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
