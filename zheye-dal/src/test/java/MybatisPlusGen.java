import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.io.File;

/**
 * MyBatisPlus 相关实体类生成器
 * <p>
 * 这个生成器最高支持到Mybatis-Plus 3.4.1版本，3.5.0开始需要重新适配代码
 *
 * @author wzy
 * @date 2022-09-19 10:47:29
 **/
public class MybatisPlusGen {

    private String author = "wzy";

    private String url = "jdbc:mysql://localhost:3306/zheye";

    private String driverName = "com.mysql.cj.jdbc.Driver";

    private String userName = "root";

    private String password = "root1234";

    private String parent = "com.imooc";

    private String[] includeTables = {"t_user", "t_column", "t_post", "t_image"};

    @Test
    public void generator() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(getProjectRootPath());
        globalConfig.setAuthor(author);
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setFileOverride(true);
        globalConfig.setEntityName("%s");
        globalConfig.setBaseColumnList(true);
        globalConfig.setBaseResultMap(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(parent);
        packageConfig.setXml("mapper");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setInclude(includeTables);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
        strategyConfig.setTablePrefix("t_");

        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig);
        generator.setDataSource(dataSourceConfig);
        generator.setPackageInfo(packageConfig);
        generator.setStrategy(strategyConfig);


        //自定义生成路径（过滤掉controller,service）
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");

        generator.setTemplate(templateConfig);

        generator.execute();
    }


    private String getProjectRootPath() {
        String testClassesPath = this.getClass().getClassLoader().getResource("").getPath();

        File file = new File(testClassesPath);
        File projectRootFile = file.getParentFile().getParentFile();

        return projectRootFile.getAbsolutePath() + File.separator + "src/test";
    }
}
