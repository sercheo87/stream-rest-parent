package org.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.models.UserModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ObjectMapper objectMapper;


    @GetMapping(path = "/stream", produces = MediaType.APPLICATION_JSON_VALUE)
    public StreamingResponseBody stream() {

        List<UserModel> responseDTOList = new ArrayList<>();

        for (int i = 0; i < 500_000; i++) {
            responseDTOList.add(new UserModel(UUID.randomUUID(), "John"));
        }

        return outputStream -> {
            for (UserModel responseDTO : responseDTOList) {
                String json = objectMapper.writeValueAsString(responseDTO);
                outputStream.write(json.getBytes());
                outputStream.write('\n');
                outputStream.flush();
            }
        };
    }

}
