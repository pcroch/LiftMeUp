package api.liftMeUp.commun.annotations;

import org.springframework.security.access.prepost.PreAuthorize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import api.liftMeUp.commun.constants.RolesConstant;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME) //todo retention
@PreAuthorize("hasAuthority(\"" + RolesConstant.ROLE_FIREMAN + "\")")
public @interface isFireman {
}
