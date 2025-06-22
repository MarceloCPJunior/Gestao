package br.com.ngbilling.gestaobancaria.application;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JacksonConfig {
    
//    private final ObjectMapper objectMapper;
//    
//    public JacksonConfig() {
//        objectMapper = new ObjectMapper();
//        
//        // Configurações para melhor serialização
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.registerModule(new JavaTimeModule());
//        
//        // Configurações para Swagger
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//    }
//    
//    @Override
//    public ObjectMapper getContext(Class<?> type) {
//        return objectMapper;
//    }
} 