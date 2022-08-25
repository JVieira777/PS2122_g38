package pt.isel.WebApp.lib.midlewares.annotations

import pt.isel.WebApp.lib.midlewares.enums.ValidationType
import pt.isel.WebApp.lib.midlewares.enums.ValidationType.ANNONYMOUS

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME)
annotation class AllowAnnonymous(val validationType : ValidationType = ANNONYMOUS) {}