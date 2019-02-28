import com.squareup.okhttp.*;
import org.json.JSONObject;

import java.io.IOException;


/**
 * @author 212607214
 * @Date 9/20/2018
 */
public class MappingDemo {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"_source\": [],\r\n    \"query\": {\r\n        \"bool\" : { \r\n            \"must\" : [ \r\n               {\r\n                    \"match\" : { \"equipment_init\" : \"YVRR\" }\r\n                },{\r\n                \t\"match\" : { \"equipment_nbr\" : \"007083\" } \r\n                },{\r\n                \t\"match\" : { \"es_data_status\" : \"ACTIVE\" }\r\n                },\r\n                {\"bool\": {\"should\": [{\"match\": {\"_type\": \"hopper\"}},{\"match\": {\"_type\": \"boxcar\"}},{\"match\": {\"_type\": \"gondola\"}},{\"match\": {\"_type\": \"flat_car\"}},{\"match\": {\"_type\": \"tank_car \"}},{\"match\": {\"_type\": \"intermodal_flat\"}},{\"match\": {\"_type\": \"vehicular_flat\"}},{\"match\": {\"_type\": \"misc_railcar\"}},{\"match\": {\"_type\": \"passenger_car\"}} ]}}\r\n            ]\r\n        }\r\n    },\r\n    \"size\": 1,\r\n\t\"sort\": [\r\n    {\"der_update_ts\": {\"order\": \"desc\"}},\r\n    {\"umler_effective_date\": {\"order\": \"desc\"}}\r\n  ]\r\n} ");
        Request request = new Request.Builder()
                .url("https://4ceaf20a4136456c812f68eb6494ad6a.us-west-2.aws.found.io:9243/asset/_search?filter_path=hits.hits._source%2Chits.hits._type")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic Y2FyaGlyZTpNTGNoM1dlKzkkMGU=")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "666f43db-f63b-d3dc-36d4-d5d19c28f202")
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() == 200) {
            try {
                System.out.println(response.getClass());
                //GTW 504254, CTTX 694997
                //AGR 001461
                JSONObject obj = new JSONObject(response.body().string());
                if (obj.length() > 0) {
                    System.out.println("Obj: "+obj.length());
                    System.out.println("obj:"+obj.toString());
                }
            } catch (Exception ex) {
                System.out.println("Exception"+ex);
            }
        }
    }
}
