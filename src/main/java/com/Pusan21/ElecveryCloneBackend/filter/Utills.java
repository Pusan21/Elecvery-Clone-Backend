package com.Pusan21.ElecveryCloneBackend.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class Utills {
    public static List<String> returnSecretToken (String _path){
        ClassPathResource resource = new ClassPathResource(_path);
        List<String> secretTokens = null;

        try {
            Path path = Paths.get(resource.getURI());
            secretTokens = Files.readAllLines(path);
        } catch (IOException e) {
            log.error("{}", e.getMessage(), e);
        }

        return secretTokens;
    }
}
