package com.Aman.SpringSecurityProject.utils;

import com.Aman.SpringSecurityProject.entities.enums.Permission;
import com.Aman.SpringSecurityProject.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.Aman.SpringSecurityProject.entities.enums.Permission.*;
import static com.Aman.SpringSecurityProject.entities.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATER, Set.of(POST_VIEW, POST_CREATE, POST_UPDATE, POST_DELETE),
            ADMIN, Set.of(USER_VIEW, USER_CREATE, USER_UPDATE, USER_DELETE,
                    POST_VIEW, POST_CREATE, POST_UPDATE, POST_DELETE)
    );

    public static Set<SimpleGrantedAuthority> getAuthorities(Role role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
