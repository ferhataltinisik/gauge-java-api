import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

public class GenericGet {

    @Step("Retrieve the last updated time from the <endpoint> endpoint")
    public void GetEndPoint(String endpoint) {
        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
        HttpResponse<JsonNode> httpResponse;
        String url = "https://reference-tryout-api.herokuapp.com/" + endpoint;
        try {
            httpResponse = Unirest.get(url)
                    .header("content-type", "application/json")
                    .header("Accept", "*/*")
                    .asJson();
            dataStore.put("httpResponse", httpResponse);
            String httpGetResponseBody = httpResponse.getBody().toString();
            dataStore.put("httpGetResponseBody", httpGetResponseBody);
            String httpResponseStatusText = httpResponse.getStatusText();
            dataStore.put("httpResponseStatusText", httpResponseStatusText);
            Gauge.writeMessage(httpResponseStatusText);
            Gauge.writeMessage(httpResponse.getBody().toString());
            String postEndpoint = (String) dataStore.get("postEndpoint");
            String updatedTime = httpResponse.getBody().getObject().getJSONArray(postEndpoint).getJSONObject(0).get("lastUpdated").toString();
            dataStore.put("updatedTime", updatedTime);
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    //Get to the "unauthorized" endpoint
    @Step("Get to the <endpoint> endpoint")
    public void justGetEndPoint(String endpoint) {
        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
        HttpResponse<String> httpResponse;
        String url = "https://reference-tryout-api.herokuapp.com/" + endpoint;
        System.out.println(url);
        Gauge.writeMessage(url);
        try {
            httpResponse = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Accept", "*/*")
                    .asString();
            dataStore.put("httpResponse", httpResponse);
            Integer httpResponseCode = httpResponse.getStatus();
            dataStore.put("httpResponseCode", httpResponseCode);
            String httpResponseStatusText = httpResponse.getStatusText();
            dataStore.put("httpResponseStatusText", httpResponseStatusText);
            Gauge.writeMessage(httpResponse.getBody());
            Gauge.writeMessage(httpResponseStatusText);
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }


}
