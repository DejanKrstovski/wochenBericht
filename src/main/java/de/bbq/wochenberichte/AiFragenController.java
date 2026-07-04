package de.bbq.wochenberichte;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class AiFragenController {

    @FXML
    TextArea prompt;

    @FXML
    public void absenden(ActionEvent e) throws MalformedURLException, IOException {
        String text = prompt.getText();

        // TODO: Change this URL to the new worker URL so that it works without .env
        // file
        String apiUrl = System.getenv("AI_WORKER_URL");
        if (apiUrl == null || apiUrl.isEmpty()) {
            apiUrl = "";
        }
        URL url = new URL(apiUrl);
        URLConnection con = url.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "text-plain");
        con.setRequestProperty("Content-Length", text.length() + "");

        try {
            // Daten "schreiben"
            DataOutputStream schreiber = new DataOutputStream(con.getOutputStream());
            schreiber.writeBytes(text);

            // Daten "lesen"
            InputStreamReader leser = new InputStreamReader(con.getInputStream());

            JAXBContext context = JAXBContext.newInstance(Wochenbericht.class);
            Unmarshaller unmar = context.createUnmarshaller();
            Wochenbericht bericht = (Wochenbericht) unmar.unmarshal(leser);

            bericht.setName(App.primary.name.getText());
            bericht.setDatum(App.primary.montag);
            bericht.setUmschulung(App.primary.umschulung.getText());

            App.primary.berichtLaden(bericht);
        } catch (Exception err) {
            err.printStackTrace();
        }

        PrimaryController.aiFenster.close();
    }
}
