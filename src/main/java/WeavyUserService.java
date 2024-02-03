import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class WeavyUserService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String WEAVY_API_BASE_URL = "https://api.weavycloud.com/v1/";

    public WeavyUser createUser(String userData) throws IOException {
        String apiUrl = WEAVY_API_BASE_URL + "users/create";

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), userData);

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return objectMapper.readValue(response.body().string(), WeavyUser.class);
            } else {
                throw new RuntimeException("Failed to create user. HTTP status code: " + response.code());
            }
        }
    }


}
