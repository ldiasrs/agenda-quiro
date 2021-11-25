import br.com.agendaquiro.controller.MessageHttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTestClient {

    private String listPath;
    private String operationPath;

    public static String URL = "http://localhost:8080";

    public IntegrationTestClient(String listPath, String operationPath) {
        this.listPath = listPath;
        this.operationPath = operationPath;
    }

    public <T> void listWihFilterAndRemoveFoundEntities(String verifyFieldName) throws JSONException {
        ResponseEntity<String> response = listPagination("Another");
        assertThat(response.getBody()).isNotEmpty();
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray jsonCustomers = (JSONArray) jsonObject.get("content");
        for (int i = 0; i < jsonCustomers.length(); i++) {
            JSONObject jsonCustomer = (JSONObject) jsonCustomers.get(i);
            assertThat(jsonCustomer.get(verifyFieldName).toString().toLowerCase()).contains("another");
            Long id = Long.valueOf(jsonCustomer.get("id").toString());
            delete(id);
        }
    }

    public ResponseEntity<String> listPagination(String searchTerm) throws JSONException {
        String endpoint = URL+listPath;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("searchTerm", searchTerm)
                .queryParam("page", 0)
                .queryParam("size", 10);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray jsonCustomers = (JSONArray) jsonObject.get("content");
        assertThat(jsonCustomers.length()).isGreaterThanOrEqualTo(1);
        return response;
    }

    public void delete(Long id) {
        String endpoint = builOperationPath(id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MessageHttpResponse> response =
                restTemplate.exchange(endpoint, HttpMethod.DELETE, null, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    public  <T> T edit(T editEntity, Long id) {
        String endpoint = builOperationPath(id);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> request = new HttpEntity<>(editEntity);
        ResponseEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.PUT, request, (Class<T>) editEntity.getClass());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        return response.getBody();
    }

    public  <T> T get(long id, Class<T> classType) {
        String endpoint = builOperationPath(id);
        RestTemplate restTemplate = new RestTemplate();
        T responseEntity = restTemplate.getForObject(endpoint, classType);
        return responseEntity;
    }

    public <T> Long create(T entity) {
        String endpoint = buildListPath();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> request = new HttpEntity<>(entity);
        ResponseEntity<MessageHttpResponse> response
                = restTemplate.postForEntity(endpoint, request, MessageHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String id = response.getBody().getValue("id");
        assertThat(id).isNotBlank();
        return Long.valueOf(id);
    }

    public String buildListPath() {
        String endpoint = URL+"/"+listPath;
        return endpoint;
    }

    private String builOperationPath(long id) {
        return URL + "/" + operationPath + "/" + id;
    }
}
