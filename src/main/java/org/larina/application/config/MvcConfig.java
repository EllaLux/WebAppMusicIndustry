package org.larina.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //для того чтобы не просто загружать файл, а ещё и раздавать его
    @Value("${upload.path}")
    private String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //каждое обращение к серверу по пути img с след. доп. данными будет
        // перенаправлять все запросы по пути "file://"(протокол файл(место в файловой системе))
        // + uploadPath(путь) + "/"
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/");
        //мэпинг для раздачи стилей, чтобы статические ресурсы раздавались без авторизации
        registry.addResourceHandler("/static/**")//чтобы раздавать всю иерархию
                //при обращении по пути "/static/**", ресурсы будут искаться в дереве проекта "classpath:"
                .addResourceLocations("classpath:/static/");
    }

}
