package cl.km.webController;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * configuracion de i18n es el listener para cambio de idioma
 *//**
 * Clase que se encarga de la configuración de i18n en la aplicación web.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Define el LocaleResolver utilizado para resolver el idioma preferido del usuario.
     *
     * @return el LocaleResolver configurado
     */
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    /**
     * Define el interceptor utilizado para cambiar el idioma de la aplicación.
     *
     * @return el LocaleChangeInterceptor configurado
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * Agrega el LocaleChangeInterceptor al registro de interceptores de la aplicación.
     *
     * @param registro el registro de interceptores
     */
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Agrega los controladores de vistas a la aplicación.
     *
     * @param registro el registro de controladores de vistas
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
}
