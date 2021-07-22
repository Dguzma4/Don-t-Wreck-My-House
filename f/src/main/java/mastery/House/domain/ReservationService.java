package mastery.House.domain;

import mastery.House.data.GuestRepository;
import mastery.House.data.HostRepository;
import mastery.House.data.ReservationRepo;
import mastery.House.models.Guest;
import mastery.House.models.Host;
import mastery.House.models.Reservation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {

    private final ReservationRepo reservationRepo;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;


    public ReservationService(ReservationRepo reservationRepo, GuestRepository guestRepository, HostRepository hostRepository) {
        this.reservationRepo = reservationRepo;
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }

    public List<Reservation> findByHostId(String hostId){

        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(host -> host.getId(), host -> host));

        Map<String, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(guest -> guest.getGuestId(),guest -> guest));

        List<Reservation> result = reservationRepo.findbyHostId(hostId);
        for (Reservation res : result){
            res.setHost(hostMap.get(res.getHost().getId()));
            res.setGuest(guestMap.get(res.getGuest().getGuestId()));
        }
        return result;
    }

    public Reservation findByEmails(String guestEmail, String hostEmail){
        Host host = new Host();
        host.setEmail(hostEmail);

       List<Reservation> pew = reservationRepo.findResByHost(host);

       return pew.stream()
               .filter(reservation -> reservation.getGuest().getEmail().equalsIgnoreCase(guestEmail))
               .findFirst()
               .orElse(null);
    }
    public Result<Reservation> add(Reservation reservation){
        Result<Reservation> result =
    }






    private Result<Reservation> validatesNulls(Reservation res){
        Result<Reservation> result = new Result<>();

        if(res == null){
            result.addErrorMessage("There is nothing here");
        }


        if(res.getHost().getEmail() ==  null){
            result.addErrorMessage("please put email of host");
        }

        if(res.getGuest().getEmail() == null){
            result.addErrorMessage("please put email of guests");
        }
    }
}
