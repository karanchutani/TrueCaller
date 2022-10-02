package com.example.demo.configuration;

import com.example.demo.constant.ClientDetailsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     *  Authorization server
     */

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(jdbcTemplate.getDataSource());

        // this is a bug from spring security , now you have to encode the secret also by BcryptPasswordEncoder.
        clients.inMemory().withClient(ClientDetailsConstant.CLIENT_ID)
                .secret(new BCryptPasswordEncoder().encode(ClientDetailsConstant.CLIENT_SECRET))
                .authorizedGrantTypes(ClientDetailsConstant.AUTHORIZED_GRANT_TYPES)
                .scopes(ClientDetailsConstant.READ, ClientDetailsConstant.WRITE)
                .authorities(ClientDetailsConstant.AUTHORITIES).autoApprove(ClientDetailsConstant.AUTO_APPROVE)

                //Access token is only valid for 10 minutes.
                .accessTokenValiditySeconds(ClientDetailsConstant.ACCESS_TOKEN_VALIDITY.intValue())

                //Refresh token is only valid for 10 minutes.;
                .refreshTokenValiditySeconds(ClientDetailsConstant.REFRESH_TOKEN_VALIDITY.intValue());
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
                .accessTokenConverter(defaultAccessTokenConverter()).userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(defaultAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter defaultAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(ClientDetailsConstant.SIGNING_KEY);
        return converter;
    }
}