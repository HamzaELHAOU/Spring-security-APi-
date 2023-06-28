package com.binga.binga.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //est utilisée pour générer automatiquement un constructeur avec des paramètres nommés, des méthodes setter et getter
public class User implements UserDetails {

    //UserDetails : pour fornir des informations sur l'étulisateur connecté

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Retourne la liste des autorités (rôles) de l'utilisateur.
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Indique si le compte de l'utilisateur a expiré.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //Indique si le compte de l'utilisateur est verrouillé ou non.
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        //Indique si les informations d'identification de l'utilisateur (mot de passe) ont expiré.
        return true;
    }

    @Override
    public boolean isEnabled() {
        //indique si le compte de l'utilisateur est activé ou désactivé.
        return true;
    }
}


