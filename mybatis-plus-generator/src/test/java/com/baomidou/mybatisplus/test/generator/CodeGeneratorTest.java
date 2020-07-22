/*
 * Copyright (c) 2011-2019, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.test.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器 示例
 *
 * @author K神
 * @since 2017/12/29
 */
class CodeGeneratorTest {



    /**
     * 是否强制带上注解
     */
    private boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    private IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    private String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * <p>默认是以I开头</p>
     * <p>user表 -> IUserService, UserServiceImpl</p>
     */
    private boolean serviceClassNameStartWithI = true;

    private String targetJavaProject = "";
    private String baseJavaProject = "";
    private String targetResourcesProject = "";

    private String packageName = "";

    private String dbUrl = "";
    private String username = "";
    private String passWord = "";

    //    @Test
    public void generateCode() {
        targetJavaProject = "/src/main/java/";
        targetResourcesProject = "/src/main/resources/mapper/";
        serviceClassNameStartWithI = false;
        packageName = "com.demo.dal";
        dbUrl = "jdbc:mysql://127.0.0.1:3306/demo";
        username = "root";
        passWord = "123456";
        baseJavaProject = "/demo-dal";
        generateByTables(packageName, "t_demo");
    }

    public static void main(String[] args) {

        CodeGeneratorTest codeGeneratorTest = new CodeGeneratorTest();
        codeGeneratorTest.generateCode();
    }

    private void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
            .setUrl(dbUrl)
            .setUsername(username)
            .setPassword(passWord)
            .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
            .setCapitalMode(true)
            .setEntityLombokModel(true)
            // .setDbColumnUnderline(true) 改为如下 2 个配置
            .setNaming(NamingStrategy.underline_to_camel)
            .setColumnNaming(NamingStrategy.underline_to_camel)
            .setEntityTableFieldAnnotationEnable(enableTableFieldAnnotation)
            .setFieldPrefix(fieldPrefix).setTablePrefix("")//test_id -> id, test_type -> type

            .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

//        String projectPath = System.getProperty("user.dir");
//        String projectPath = System.getProperty("user.dir")+baseJavaProject;

        String projectPath = "/Users/pingan.cui/cpa/workspace/sourceCode/mybatis-plus"+baseJavaProject;


        config.setActiveRecord(false)// 开启 activeRecord 模式
            .setBaseResultMap(true)// XML ResultMap
            .setBaseColumnList(true)// XML columList
            .setSwagger2(false) // 是否生成 Swagger2 注解
            .setIdType(tableIdType)
            .setAuthor("pingan.cui")
            .setOutputDir(projectPath+targetJavaProject)
            .setOutputDir(projectPath+targetJavaProject)
            .setOutputServiceDir(projectPath+targetJavaProject)
            .setOutputServiceImplDir(projectPath+targetJavaProject)
//            .setOutputControllerDir(projectAppPath+targetJavaProject)
            .setFileOverride(true)
            .setCreateController(false)//
            .setCreateDal(true)//
            .setCreateService(true);//
        if (!serviceClassNameStartWithI) {
//            config.setServiceName("%sService");
            config.setEntityName("%sModel");
//            config.setControllerName("%sApp");
        }


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(packageName);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String moduleName = pc.getModuleName();
                if(StringUtils.isEmpty(moduleName)){
                    return projectPath + targetResourcesProject + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }else{
                    return projectPath + targetResourcesProject + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }


            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);

        new AutoGenerator().setGlobalConfig(config)
            .setDataSource(dataSourceConfig)
            .setStrategy(strategyConfig)
            .setPackageInfo(pc)
            .setCfg(cfg)
            .setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
            )
//            .setPackageInfo(
//                new PackageConfig()
//                    .setParent(packageName)
//                    .setController("controller")
//                    .setEntity("model")
//                    .setXml("/Users/pingan.cui/cpa/workspace/sourceCode/ddd-spread-activity/src/main/resources")
//            )
            .execute();
    }
}
