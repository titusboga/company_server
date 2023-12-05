package company.mariadb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MariaDBController {

    String dockerComposeFilePath = "/Users/titusboga/Desktop/Development/server/backend/src/main/java/com/lab4/demo/mariadb/docker-compose-mariadb.yml";
    String dockerPath = "/usr/local/bin";

    public void up()
    {
        System.out.println("let go 1");
        String[] commandUp = {dockerPath + "/docker-compose", "-f", dockerComposeFilePath, "up", "-d"};
        run(commandUp);
        System.out.println("let go");
    }

    public void down()
    {
        String[] commandDown = {dockerPath + "/docker-compose", "-f", dockerComposeFilePath, "down"};
        run(commandDown);

    }

    public  void run(String[] command) {


        ProcessBuilder processBuilder = new ProcessBuilder(command);
        // Set custom PATH
        Map<String, String> env = processBuilder.environment();
        String path = env.get("PATH");
        env.put("PATH", dockerPath + ":" + path);

        // Redirect error stream to the output stream
        processBuilder.redirectErrorStream(true);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Read the combined output and error stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to exit
            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
