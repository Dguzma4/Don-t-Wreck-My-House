package mastery.House.data;

import mastery.House.models.Guest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GuestFileRepository {

    private static final String Header = "guest_id,first_name,last_name,email,state";
    private final String filePath;

    public GuestFileRepository(String filePath) {
        this.filePath = filePath;
    }

    public List<Guest> findAll(){
        ArrayList<Guest> result = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader((new FileReader(filePath)))){
            reader.readLine();

            for(String line = reader.readLine(); line != null; line = reader.readLine()){


                String[] fields = line.split(",", -1);
                if(fields.length == 5){

                }
            }





        }
    }







    private Guest deserialize(String [] fields ){
        Guest result = new Guest();

        result.setGuestId(fields[0]);
        result.setFirstName(fields[1]);
        result.setLastName(fields[2]);
        result.setEmail(fields[3]);
        result.setPhoneNumber(fields[4]);
        result.setState(fields[5]);
    }


}