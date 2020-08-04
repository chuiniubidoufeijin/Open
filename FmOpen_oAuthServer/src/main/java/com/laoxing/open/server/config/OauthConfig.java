package com.laoxing.open.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-04 14:11
 */
@Configuration
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    //实现客户端（第三方应用信息）的配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("app1001").secret("{bcrypt}654321"). //设置客户端的id和对应的秘钥
                redirectUris("http://localhost:8080/api/hello.html").
                autoApprove(true).scopes("all")
                .refreshTokenValiditySeconds(1800).accessTokenValiditySeconds(600).
                authorizedGrantTypes("authorization_code");
    }

    //设置权限接口信息
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("isAuthenticated()").
//                checkTokenAccess("isAuthenticated()").
//                allowFormAuthenticationForClients();
        ClientDetails details;
        security.tokenKeyAccess("permitAll()").
                checkTokenAccess("permitAll()").
                allowFormAuthenticationForClients();
    }
    //授权服务的认证
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(createTS());
    }
    @Bean
    public TokenStore createTS(){
        //new RedisTokenStore() 存储到Redis
        //new JdbcTokenStore(); 存储到数据库
//        JwtTokenStore
        return new InMemoryTokenStore(); //存储到内存中
    }
}
