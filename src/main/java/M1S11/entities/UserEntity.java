package M1S11.entities;

import M1S11.enums.UserStatus;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Builder
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private UserStatus profile = UserStatus.USER;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> profile.name());
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}