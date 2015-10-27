package com.benscribner.beercraft.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * A class representing an authority
 */
public interface Role extends GrantedAuthority, Identifiable, Named {

}