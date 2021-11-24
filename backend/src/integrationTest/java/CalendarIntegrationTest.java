import br.com.agendaquiro.domain.servicetype.ServiceType;
import org.json.JSONException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CalendarIntegrationTest {

    private static final IntegrationTestClient client =
            new IntegrationTestClient("calendar", "calendar");

    @Test
    public void list() throws JSONException {

//        //LIST
//        client.listPagination("");
//        //GET
//        //ServiceType savedServiceType = client.get(id, ServiceType.class);
//        //EDIT
//        savedServiceType.setDurationInMinutes(45);
//        ServiceType editedServiceType = client.edit(savedServiceType, savedServiceType.getId());
//        assertThat(editedServiceType.getDurationInMinutes()).isEqualTo(savedServiceType.getDurationInMinutes());
//        //DELETE
//        client.delete(editedServiceType.getId());
    }

    @Test
    public void listWihFilter() throws JSONException {
        //CREATE
        ServiceType anotherServiceType = ServiceType.builder()
                .description("Another ServiceType")
                .durationInMinutes(60)
                .build();
        client.create(anotherServiceType);
        //LIST WITH FILTER
        client.listWihFilterAndRemoveFoundEntities("description");
    }
}
