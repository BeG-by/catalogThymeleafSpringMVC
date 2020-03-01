package by.beg.catalog.config;


import by.beg.catalog.entity.Order;
import by.beg.catalog.entity.Product;
import by.beg.catalog.entity.ProductTypeEnum;
import by.beg.catalog.entity.User;
import by.beg.catalog.interceptor.AdminInterceptor;
import by.beg.catalog.interceptor.DispatcherInterceptor;
import by.beg.catalog.interceptor.AuthorizationInterceptor;
import by.beg.catalog.interceptor.NonAuthorizationInterceptor;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


import java.util.ArrayList;


@Configuration
@EnableWebMvc
@ComponentScan("by.beg.catalog")
public class WebConfig implements WebMvcConfigurer {


//    @Bean("productList")
//    public ArrayList<Product> productList() {
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product("Xiaomi Redmi 3", ProductTypeEnum.PHONE, "Отличный телефон", 200));
//        products.add(new Product("Iphone 6", ProductTypeEnum.PHONE, "Отличный телефон", 400));
//        products.add(new Product("ASUS 13p", ProductTypeEnum.LAPTOP, "Отличный ноут", 600));
//        products.add(new Product("Dell Inspirion  1215n", ProductTypeEnum.LAPTOP, "Отличный ноут", 500));
//        products.add(new Product("Samsung Tab", ProductTypeEnum.TABLET, "Отличный планшет", 600));
//        products.add(new Product("Apple Tab 4", ProductTypeEnum.TABLET, "Отличный планшет", 550));
//        products.add(new Product("PC 5050", ProductTypeEnum.PC, "Отличный компьютер", 900));
//        products.add(new Product("Xiaomi Redmi 4", ProductTypeEnum.PHONE, "Отличный телефон", 220));
//        products.add(new Product("Iphone 5", ProductTypeEnum.PHONE, "Отличный телефон", 300));
//        products.add(new Product("ASUS 15p", ProductTypeEnum.LAPTOP, "Отличный ноут", 750));
//        products.add(new Product("Dell Inspirion  1216n", ProductTypeEnum.LAPTOP, "Отличный ноут", 550));
//        products.add(new Product("Samsung Tab 2", ProductTypeEnum.TABLET, "Отличный планшет", 690));
//        products.add(new Product("Apple Tab 6", ProductTypeEnum.TABLET, "Отличный планшет", 770));
//        products.add(new Product("PC 5022", ProductTypeEnum.PC, "Отличный компьютер", 850));
//
//        return products;
//    }


//    @Bean("userList")
//    public ArrayList<User> userList() {
//        ArrayList<User> users = new ArrayList<>();
//
//        users.add(new User("Admin", "+375291111111", "Чкалова 15", "admin@gmail.com", "admin", true, true));
//        users.add(new User("Dispatcher1", "+375291111111", "Чкалова 15", "dispatcher1@gmail.com", "dispatcher", false, true));
//        users.add(new User("Dispatcher2", "+375291111111", "Чкалова 15", "dispatcher2@gmail.com", "dispatcher", false, true));
//        users.add(new User("user1", "+375291111111", "Воронянского 17", "user1@gmail.com", "user"));
//        users.add(new User("user2", "+375291111111", "Ленина 2", "user2@gmail.com", "user"));
//        users.add(new User("user3", "+375291111111", "Немига 15", "user3@gmail.com", "user"));
//        users.add(new User("user4", "+375291111111", "Немига 17", "user4@gmail.com", "user"));
//        users.add(new User("user5", "+375291111111", "Тимерязева 42", "user5@gmail.com", "user"));
//
//        return users;
//
//    }

    @Bean("productBasket")
    @SessionScope
    public ArrayList<Product> basket() {
        return new ArrayList<>();
    }

    @Bean("orderList")
    public ArrayList<Order> orderList() {
        return new ArrayList<>();
    }

    @Bean("commentsList")
    public ArrayList<String> comments() {
        return new ArrayList<>();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/add", "/edit/**", "/remove/**");
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/user/reg , /user/auth");
        registry.addInterceptor(new NonAuthorizationInterceptor()).addPathPatterns("/basket", "/basket/**", "/out", "/comment");
        registry.addInterceptor(new DispatcherInterceptor()).addPathPatterns("/orders", "/orders/**");
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