package pt.isel.WebApp.lib.midlewares.annotations

import pt.isel.WebApp.lib.midlewares.enums.ValidationType
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target


@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME)
annotation class ValidateToken(val validationType : ValidationType = ValidationType.VERIFIED_TOKEN) {}