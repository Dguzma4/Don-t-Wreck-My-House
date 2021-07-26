package mastery.House.data;

import mastery.House.models.Guest;
import mastery.House.models.Host;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository implements GuestRepository {

    private static final String Header = "guest_id,first_name,last_name,email,state";
    private final String filePath;

    public GuestFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Guest> findAll(){
        ArrayList<Guest> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader((new FileReader(filePath)))){
            reader.readLine();

            for(String line = reader.readLine(); line != null; line = reader.readLine()){


                String[] fields = line.split(",", -1);
                if(fields.length == 6){
                    result.add(deserialize(fields));


                }
            }

        } catch (IOException ex){
            //ahh

        }
        return  result;
    }

    @Override
    public Guest findbyId(String id){
        return findAll().stream()
                .filter(guest -> guest.getGuestId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Guest findByEmail(String email){
        return findAll().stream()
                .filter(guest -> guest.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }


    private Guest deserialize(String [] fields ){
        Guest result = new Guest();

        result.setGuestId(fields[0]);
        result.setFirstName(fields[1]);
        result.setLastName(fields[2]);
        result.setEmail(fields[3]);
        result.setPhoneNumber(fields[4]);
        result.setState(fields[5]);
        return result;
    }



}
