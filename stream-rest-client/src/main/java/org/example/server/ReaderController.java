package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.models.UserModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ReaderController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(path = "/reader", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity stream() {
        log.info("stream()");
        List<UserModel> userModelList = new ArrayList<>();
        restTemplate.execute("http://localhost:3000/stream", HttpMethod.GET, null, clientHttpResponse -> {
            InputStream inputStream = clientHttpResponse.getBody();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            ObjectMapper objectMapper = new ObjectMapper();

            while ((line = bufferedReader.readLine()) != null) {
                UserModel userDto = objectMapper.readValue(line, UserModel.class);
                userModelList.add(userDto);
            }

            return null;
        });

        log.info("userModelList.size() = " + userModelList.size());
        return ResponseEntity.ok(userModelList.size());
    }

}
