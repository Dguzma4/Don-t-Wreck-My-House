package mastery.House.data;

import mastery.House.models.Host;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostFileRepository implements HostRepository {

    private static final String Header = "id,last_name,email,phone address, city,state,postal_code,standard_rate, weekend_rate ";
    private final String filePath;


    public HostFileRepository(String filePath) { this.filePath = filePath; }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 10) {
                    result.add(deserialize(fields));
                }
            }

        } catch (IOException ex) {
            //dont thorw on read
        }
    return result;
    }

    @Override
    public Host findId(String id){
        return findAll().stream()
                .filter(host ->host.getId().equalsIgnoreCase(id) )
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<Host> findbyPostalCode(String postal){
        List<Host> pew =  findAll().stream()
                .filter(host -> host.getPostalCode().equalsIgnoreCase(postal))
                .collect(Collectors.toList());
        return pew;
    }




    private Host deserialize(String [] fields){
        Host result = new Host();

        result.setId(fields[0]);
        result.setLastName(fields[1]);
        result.setEmail(fields[2]);
        result.setPhoneNumber(fields[3]);
        result.setAddress(fields[4]);
        result.setCity(fields[5]);
        result.setState(fields[6]);
        result.setPostalCode(fields[7]);
        result.setStandardRate(new BigDecimal(fields[8]));
        result.setWeekendRate(new BigDecimal(fields[9]));
        return result;
    }


}
