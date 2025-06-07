import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws Exception {
        APIClient client = new APIClient();
        String jsonResponse = client.getExchangeRateData();

        if (jsonResponse != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);
            double eurRate = root.get("conversion_rates").get("EUR").asDouble();
            System.out.println("1 USD = " + eurRate + " EUR");
        } else {
            System.out.println("Impossible de récupérer les données.");
        }

    }
}