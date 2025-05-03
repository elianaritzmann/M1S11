package M1S11.configs;

import M1S11.enums.UserStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
/*
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        String password = encoder.encode("pass");
        UserDetails userDetails = User.withUsername("user")
                .password(password)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
*/



//
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((s) ->
                        s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/users/**").hasAnyAuthority(UserStatus.ADMIN.name())
                        .requestMatchers(HttpMethod.POST,"/organizations/**").hasAuthority(UserStatus.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE,"/organizations/**").hasAuthority(UserStatus.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT,"/organizations/**").hasAuthority(UserStatus.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/organizations/**").hasAnyAuthority(
                                UserStatus.ADMIN.name(),
                                UserStatus.USER.name() )
                        .anyRequest().authenticated()
                )
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}
