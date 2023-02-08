import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GenericPost {

    @Step("Post to the <endpoint> endpoint")
    public void PostEndPoint(String endpoint) throws Exception {
        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
        HttpResponse<String> httpResponse;
        String url = "https://reference-tryout-api.herokuapp.com/" + endpoint;
        System.out.println(url);
        Gauge.writeMessage(url);
        try {
            httpResponse = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .body("{\"customerId\":\"a00a095f-5b2f-47f3-97af-42eddc5201de\",\"customerName\":\"Eliane\"," +
                            "\"lastName\":\"Durgan\",\"occupation\":\"QA Engineer\",\"phoneNumber\":\"1104395171\"}")
                    .asString();
            dataStore.put("httpResponse", httpResponse);
            String httpResponseBody = httpResponse.getBody();
            dataStore.put("httpResponseBody", httpResponseBody);
            Integer httpResponseCode = httpResponse.getStatus();
            dataStore.put("httpResponseCode", httpResponseCode);
            String httpResponseStatusText = httpResponse.getStatusText();
            dataStore.put("httpResponseStatusText", httpResponseStatusText);
            dataStore.put("postEndpoint", endpoint);
            Gauge.writeMessage(httpResponse.getBody());
            Gauge.writeMessage(httpResponseStatusText);
            dataStore.put("postingTime", httpResponse.getHeaders().get("Date"));
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
