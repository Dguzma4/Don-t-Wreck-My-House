package mastery.House.data;

import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import static java.lang.Integer.parseInt;

public class ReservationFileRepo implements ReservationRepo {

    private  static  final String Header = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepo(String directory) {

        this.directory = directory;
    }
    private  String getFilePath(String hostId){
        return  Paths.get(directory, hostId+".csv").toString();
    }


    @Override
    public List<Reservation> findbyHostId(String hostId){
        ArrayList<Reservation> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(hostId)))){
            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                String[] fields = line.split(",", -1);
                if(fields.length == 5){
                    result.add(deserialize(fields,hostId));
                }
            }
        } catch (IOException ex){
            //hhh
        }
        return  result;
    }



    @Override
    public List<Reservation> findResByHost(Host host){
        return findbyHostId(host.getId()).stream()
                .filter(reservation -> reservation.getHost().getId().equalsIgnoreCase(host.getId()))
                .collect(Collectors.toList());
    }

//    public Reservation findResByGuests(Guest guest){
//        return findbyHostId()
//    }

    @Override
    public Reservation createReservation(Reservation res) throws DataException {
        List<Reservation> all = findbyHostId(res.getHost().getId());
        res.setId(String.valueOf(getMaxId(all)));
        all.add(res);
        WriteAll(all, res.getHost().getId());
        return res;
    }

    @Override
    public boolean update(Reservation res) throws DataException {
        List<Reservation> all = findbyHostId(res.getHost().getId());
        for(int i = 0; i< all.size(); i++){
            if(all.get(i).getId().equals(res.getId())){
                all.set(i,res);
                WriteAll(all,res.getHost().getId());
                return  true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteResById(String resId, Host host) throws DataException {
        List<Reservation> all = findbyHostId(host.getId());
        for(int i =0; i< all.size(); i ++){
            if(all.get(i).getId().equals(resId)){
                all.remove(i);
                WriteAll(all,all.get(i).getHost().getId());
                return true;
            }
        }
        return false;
    }



    private int getMaxId(List<Reservation> all ) {
        int maxid = 0;
    for(Reservation r: all){
        if(parseInt(r.getId()) > maxid){
            maxid = parseInt(r.getId());
        }
    }
    return maxid +1;
    }

private void WriteAll(List<Reservation> reservations, String hostId) throws DataException {
        try(PrintWriter writer = new PrintWriter(getFilePath(hostId))){

            writer.println(Header);

            for(Reservation res : reservations) {
                writer.println(serialize(res));
            }
            } catch(FileNotFoundException ex){
            throw new DataException(ex);

            }


        }




private String serialize(Reservation person){
        return String.format("%s,%s,%s,%s,%s",
                person.getId(),
                person.getStartDate(),
                person.getEndDate(),
                person.getGuest().getGuestId(),
                person.getTotal());
}

    private Reservation deserialize(String [] fields, String hostId){
        Reservation result = new Reservation();
        result.setId(fields[0]);


        //look for defualt string representation
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        guest.setGuestId(fields[3]);
        result.setGuest(guest);

        result.setTotal(new BigDecimal(fields[4]));

        Host host = new Host();
        host.setId(hostId);
        result.setHost(host);



        return result;
    }
}
