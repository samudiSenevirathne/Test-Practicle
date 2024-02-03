// Import necessary libraries


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weavy-api/users")
public class WeavyUserController {

    private final String WEAVY_API_BASE_URL = "https://api.weavycloud.com/v1/";

    private final OkHttpClient client = new OkHttpClient();

    // Create User
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody String userData) {
        String apiUrl = WEAVY_API_BASE_URL + "users/create";

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), userData);

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return ResponseEntity.status(response.code()).body(response.body().string());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
