import br.com.agendaquiro.controller.professional.request.ProfessionalRequest;
import org.json.JSONException;
import org.junit.Test;


public class ProfessionalIntegrationTest {

    private static final IntegrationTestClient client =
            new IntegrationTestClient("professionals", "professional");

    private ProfessionalRequest professionalRequest = ProfessionalRequest.builder()
            .name("Test Professional")
            .build();

    @Test
    public void crudTest() throws JSONException {
        long id = client.create(professionalRequest);
        client.listPagination("");
        ProfessionalRequest professionalResponse = client.get(id, ProfessionalRequest.class);
        client.delete(id);
    }
}
