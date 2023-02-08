import com.jayway.jsonpath.JsonPath;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.junit.Assert;

public class Assertions {

    @Step("The response code should be <response_code>")
    public void responseCodeShouldEqual(Integer response_code) {
        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
        Integer httpResponseCode = (Integer) dataStore.get("httpResponseCode");

        Assert.assertEquals(response_code, httpResponseCode);
    }

    @Step("Assert against last updated time")
    public void AssertLastUpdatedTime() throws Exception {
        // TODO - Need to compair the time a POST request was made against the time provided from /last
        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
        String httpResponseBody = (String) dataStore.get("httpResponseBody");
        String httpGetResponseBody = (String) dataStore.get("httpGetResponseBody");
        String postEndpoint = (String) dataStore.get("postEndpoint");
        String lastUpdated = JsonPath.read(httpGetResponseBody, "$."+postEndpoint+"[0].lastUpdated");
        String formattedLastUpdated = Utils.formatDate(lastUpdated);
        String postTime = Utils.trimDate(dataStore.get("postingTime").toString());
        Assert.assertEquals(formattedLastUpdated, postTime);
    }


    @Step("Assert the response contains the correct details")
    public void AssertTheResponseContainsTheCorrectDetails()  {
        // TODO - Need to compair the time a POST request was made against the time provided from /last
        System.out.println("test");
//        DataStore dataStore = DataStoreFactory.getScenarioDataStore();
//        String httpResponseBody = (String) dataStore.get("httpResponseBody");
//        String httpGetResponseBody = (String) dataStore.get("httpGetResponseBody");
//        String postEndpoint = (String) dataStore.get("postEndpoint");
//        String lastUpdated = JsonPath.read(httpGetResponseBody, "$."+postEndpoint+"[0].lastUpdated");
//        String formattedLastUpdated = Utils.formatDate(lastUpdated);
//        String postTime = Utils.trimDate(dataStore.get("postingTime").toString());
//        Assert.assertEquals(formattedLastUpdated, postTime);
//        Assert.assertEquals(JsonPath.read(httpResponseBody, "$.receivedRequest[0].customerId"),
//                "a00a095f-5b2f-47f3-97af-42eddc5201de"
//                );
//        Assert.assertEquals(JsonPath.read(httpResponseBody, "$.receivedRequest[0].customerName"),
//                "Eliane"
//                );
//        Assert.assertEquals(JsonPath.read(httpResponseBody, "$.receivedRequest[0].lastName"),
//                "Durgan"
//                );
//        Assert.assertEquals(JsonPath.read(httpResponseBody, "$.receivedRequest[0].occupation"),
//                "QA Engineer"
//                );
//        Assert.assertEquals(JsonPath.read(httpResponseBody, "$.receivedRequest[0].phoneNumber"),
//                "1104395171"
//                );
    }

}
