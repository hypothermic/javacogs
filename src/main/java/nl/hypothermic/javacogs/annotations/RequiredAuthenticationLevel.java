package nl.hypothermic.javacogs.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import nl.hypothermic.javacogs.AuthenticationType;

@Retention(RUNTIME)
@Target(METHOD)
public @interface RequiredAuthenticationLevel {

	AuthenticationType authType() default AuthenticationType.PUBLIC;

}
