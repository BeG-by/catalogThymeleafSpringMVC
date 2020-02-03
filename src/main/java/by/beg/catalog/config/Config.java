package by.beg.catalog.config;


import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.ProductTypeEnum;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


import java.util.ArrayList;


@Configuration
@EnableWebMvc
@ComponentScan("by.beg.catalog")
public class Config implements WebMvcConfigurer {


    @Bean("productList")
    public ArrayList<Product> productList() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Xiaomi Redmi 3", ProductTypeEnum.PHONE, "Отличный телефон", 200));
        products.add(new Product("Iphone 6", ProductTypeEnum.PHONE, "Отличный телефон", 400));
        products.add(new Product("ASUS 13p", ProductTypeEnum.LAPTOP, "Отличный ноут", 600));
        products.add(new Product("Dell Inspirion  1215n", ProductTypeEnum.LAPTOP, "Отличный ноут", 500));
        products.add(new Product("Samsung Tab", ProductTypeEnum.TABLET, "Отличный планшет", 600));
        products.add(new Product("Apple Tab 4", ProductTypeEnum.TABLET, "Отличный планшет", 550));
        products.add(new Product("PC 5050", ProductTypeEnum.PC, "Отличный компьютер", 900));
        products.add(new Product("Xiaomi Redmi 4", ProductTypeEnum.PHONE, "Отличный телефон", 220));
        products.add(new Product("Iphone 5", ProductTypeEnum.PHONE, "Отличный телефон", 300));
        products.add(new Product("ASUS 15p", ProductTypeEnum.LAPTOP, "Отличный ноут", 750));
        products.add(new Product("Dell Inspirion  1216n", ProductTypeEnum.LAPTOP, "Отличный ноут", 550));
        products.add(new Product("Samsung Tab 2", ProductTypeEnum.TABLET, "Отличный планшет", 690));
        products.add(new Product("Apple Tab 6", ProductTypeEnum.TABLET, "Отличный планшет", 770));
        products.add(new Product("PC 5022", ProductTypeEnum.PC, "Отличный компьютер", 850));

        return products;
    }


    @Bean
    public SpringResourceTemplateResolver getTemplateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }


}