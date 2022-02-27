package autocompleter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

public class LocationReader {

    public LocationReader() {}

    public static List<String> getLocations(ServletContext context) throws IOException {
        InputStream inputStream = context.getResourceAsStream("/WEB-INF/locationlist.txt");
        List<String> locations = new ArrayList();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String location = "";

            while((location = bufferedReader.readLine()) != null) {
                locations.add(location);
            }

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }

        return locations;
    }
}
