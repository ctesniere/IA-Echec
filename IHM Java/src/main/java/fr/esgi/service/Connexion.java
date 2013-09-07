package fr.esgi.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Connexion {

    // constructors
    // --------------------------------------------------------------------------------------------

    public Connexion() {
    }

    // instance methods
    // --------------------------------------------------------------------------------------------

    public String connexion(String stringUrl) {
        String response = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200)
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());

            response = this.getInputStreamAsString(connection.getInputStream());

            connection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Convertit le flux sous forme de chaînes de caractères.
     * 
     * @param input
     *            Le flux
     * 
     * @throws IOException
     *             si une erreur I/O intervient
     */
    private String getInputStreamAsString(InputStream input) {
        Scanner scanner = new Scanner(input);
        String response = scanner.next();
        scanner.close();

        return response;
    }
}