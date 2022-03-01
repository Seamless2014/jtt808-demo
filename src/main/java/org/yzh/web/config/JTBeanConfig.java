package org.yzh.web.config;

import io.github.yezhihao.netmc.core.HandlerMapping;
import io.github.yezhihao.netmc.core.SpringHandlerMapping;
import io.github.yezhihao.netmc.session.SessionListener;
import io.github.yezhihao.netmc.session.SessionManager;
import io.github.yezhihao.protostar.SchemaManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yzh.protocol.codec.JTMessageAdapter;
import org.yzh.protocol.codec.JTMessageDecoder;
import org.yzh.protocol.codec.JTMessageEncoder;
import org.yzh.web.endpoint.JTHandlerInterceptor;
import org.yzh.web.endpoint.JTSessionListener;
import org.yzh.web.model.enums.SessionKey;

@Configuration
public class JTBeanConfig {

    @Bean
    public HandlerMapping handlerMapping() {
        return new SpringHandlerMapping();
    }

    @Bean
    public JTHandlerInterceptor handlerInterceptor() {
        return new JTHandlerInterceptor();
    }

    @Bean
    public SessionListener sessionListener() {
        return new JTSessionListener();
    }

    @Bean
    public SessionManager sessionManager(SessionListener sessionListener) {
        return new SessionManager(SessionKey.class, sessionListener);
    }

    @Bean
    public SchemaManager schemaManager() {
        return new SchemaManager("org.yzh.protocol");
    }

    @Bean
    public JTMessageAdapter messageAdapter(SchemaManager schemaManager) {
        JTMessageEncoder encoder = new JTMessageEncoder(schemaManager);
        JTMessageDecoder decoder = new JTMessageDecoder(schemaManager);
        return new JTMessageAdapter(encoder, decoder);
    }
}