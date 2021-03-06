package com.demo.common.codeGenerator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator3 {

    private static String url = "jdbc:mysql://10.12.8.11:3306/SmartRentalHousing?serverTimezone=CTT";
    private static String user = "root";
    private static String password = "root";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String author = "com";
    private static String outputDir = "/src/main/java/";
    private static String packageName = "com.demo.project";//生成的东西放在这个包里
    private static String tablePrefix = ""; //表前缀
    private static String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {

        //数据库连接
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(url)
                .setUsername(user)
                .setPassword(password)
                .setDriverName(driverName);

        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)   //// 开启 activeRecord 模式
                .setEnableCache(false)  //二级缓存
                .setSwagger2(true) //实体属性 Swagger2 注解
                .setAuthor(author)  //作者
                .setOutputDir(projectPath + outputDir)  //输出目录
                .setFileOverride(false)  //覆盖文件
                .setServiceName("I%sService");  // 自定义文件命名，注意 %s 会自动填充表实体属性！
//                .setServiceImplName("%sServiceImpl")
//                .setMapperName("%sMapper")
//                .setXmlName("%sMapper");

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mybatis/com.demo.project.mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)   // 全局大写命名
//                .setDbColumnUnderline(true)   // 全局下划线命名
                .setEntityLombokModel(true)     //Lombok
                .setNaming(NamingStrategy.underline_to_camel)   // 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)     //字段名生成策略
                .setRestControllerStyle(true) //RestController注解
                .setControllerMappingHyphenStyle(true)          //驼峰转连字符
                .setTablePrefix(tablePrefix);   //表前缀
//                .setInclude("tb_user"); // 需要生成的表,默认全部
//                .setExclude(new String[]{"test"}) // 排除生成的表
//                .setSuperEntityClass("com.baomidou.test.base.BsBaseEntity") //自定义实体父类
//                .setSuperMapperClass("com.baomidou.test.base.BsBaseMapper")   // 自定义 com.demo.project.mapper 父类
//                .setSuperServiceClass("com.baomidou.test.base.BsBaseService") // 自定义 service 父类
//                .setSuperServiceImplClass("com.baomidou.test.base.BsBaseServiceImpl") // 自定义 service 实现类父类
//                .setSuperControllerClass("com.baomidou.test.TestController")// 自定义 com.demo.project.controller 父类
//                .setEntityColumnConstant(true)    //是否生成字段常量（默认 false）
//                .setEntityBooleanColumnRemoveIsPrefix(true);  // Boolean类型字段是否移除is前缀处理

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setCfg(new InjectionConfig() {
                            @Override
                            public void initMap() {
                            }
                        }.setFileOutConfigList(focList)
                )
                .setStrategy(strategyConfig)
                .setPackageInfo(new PackageConfig()
                        .setParent(packageName)
                )
                .setTemplate(new TemplateConfig().setXml(null)) //关闭默认的XML生成
                .setTemplateEngine(new FreemarkerTemplateEngine())  //模板引擎
                .execute(); //执行
    }
}
