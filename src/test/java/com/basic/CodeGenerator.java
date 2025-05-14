package com.basic;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

/**
 * MybatisPlus代码生成器
 *
 * @author vains
 */
public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/basic-cloud-platform?serverTimezone=Asia/Shanghai&userUnicode=true&characterEncoding=utf-8", "root", "root")
                .globalConfig(builder -> builder
                        .author("vains")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                )
                .packageConfig(builder -> builder
                        .parent("com.basic")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .addInclude("sys_basic_user")
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
