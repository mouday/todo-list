package com.github.mouday.todolist.code;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class AutoGenerator {
    public static void main(String[] args) {

        String tableName = "tb_item";

        String author = "mouday";
        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + "/src/main/java";
        String packageParent = "com.github.mouday";
        String moduleName = "todolist";
        String url = "jdbc:mysql://127.0.0.1:3306/db_data?useSSL=false&characterEncoding=utf8";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "123456";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .outputDir(outputDir) // 指定输出目录
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent(packageParent) // 设置父包名
                            .moduleName(moduleName); // 设置父包模块名
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix("tb_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
