import br.com.agendaquiro.controller.auth.UserResponse;
import br.com.agendaquiro.controller.calendar.CalendarResponse;
import br.com.agendaquiro.domain.user.User;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthIntegrationTest {

    @Test
    public void shouldAuthenticateWithSuccess()  {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity(createHeaders());
        ResponseEntity<UserResponse> response = restTemplate.exchange(
                IntegrationTestClient.URL+"/auth/login", HttpMethod.GET, request, UserResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            String auth = "leo@quiroapp.com:cerveja";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

}
