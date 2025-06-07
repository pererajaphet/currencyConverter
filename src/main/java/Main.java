import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        APIClient client = new APIClient();
        String jsonResponse = client.getExchangeRateData();

        if (jsonResponse != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(jsonResponse);
                JsonNode rates = root.get("conversion_rates");

                if (rates == null) {
                    System.out.println("Erreur : impossible de lire les taux de change.");
                    return;
                }

                System.out.println("Voici les taux de change disponibles : \n" + rates);
                System.out.println("Souhaitez-vous convertir une devise ? (Oui / Non)");

                Scanner scanner = new Scanner(System.in);
                String entry = scanner.nextLine().trim();

                if (entry.equalsIgnoreCase("Oui")) {
                    System.out.print("Devise d'entrée (ex: USD) : ");
                    String entryCurrency = scanner.nextLine().trim().toUpperCase();

                    System.out.print("Montant à convertir : ");
                    double amount = Double.parseDouble(scanner.nextLine().trim());

                    System.out.print("Devise de sortie (ex: EUR) : ");
                    String outputCurrency = scanner.nextLine().trim().toUpperCase();

                    if (!rates.has(entryCurrency) || !rates.has(outputCurrency)) {
                        System.out.println("Devise non reconnue. Veuillez vérifier les codes ISO.");
                        return;
                    }

                    double rateIn = rates.get(entryCurrency).asDouble();
                    double rateOut = rates.get(outputCurrency).asDouble();

                    // Convertir via USD comme devise pivot
                    double usdAmount = amount / rateIn;
                    double converted = usdAmount * rateOut;

                    System.out.printf("%.2f %s = %.2f %s%n", amount, entryCurrency, converted, outputCurrency);
                } else {
                    System.out.println("Conversion annulée. À bientôt !");
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement : " + e.getMessage());
            }
        } else {
            System.out.println("Impossible de récupérer les données depuis l'API.");
        }
    }
}