package com.example.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Service
public class MermaidService {

    private static Logger logger = LoggerFactory.getLogger(MermaidService.class);
    private RestClient restClient;

    public MermaidService(RestClient restClient){
        this.restClient = restClient;
    }

    public void generate(String graph){
        byte[] graphBytes = graph.getBytes(StandardCharsets.UTF_8);
        String base64String = Base64.getEncoder().encodeToString(graphBytes);

        try {
            ResponseEntity<Resource> response = restClient.get().uri(base64String).retrieve().toEntity(Resource.class);
            File targetFile = new File("src/main/resources/image.jpeg");

            java.nio.file.Files.copy(
                    response.getBody().getInputStream(),
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch(Exception e){
            logger.error("Unknown error", e);
        }
    }
}
