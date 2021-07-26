package mastery.House.domain;

import mastery.House.data.DataException;
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

    public List<Reservation> findByHostId(String hostId) {
        //TODO: make host map into just a singular host getHostbyId
        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(host -> host.getId(), host -> host));
//        Host hostmap = hostRepository.findAll().stream()
//                .filter(host -> host.)
//

        Map<String, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(guest -> guest.getGuestId(), guest -> guest));

        List<Reservation> result = reservationRepo.findbyHostId(hostId);
        for (Reservation res : result) {
            res.setHost(hostMap.get(res.getHost().getId()));
            res.setGuest(guestMap.get(res.getGuest().getGuestId()));
        }
        return result;
    }

    public List<Reservation> findReservationListByHostByHostEmail(String hostEmail){
        Host host = hostRepository.findAll().stream()
                .filter(host1 -> host1.getEmail().equalsIgnoreCase(hostEmail))
                .findFirst()
                .orElse(null);

        return reservationRepo.findbyHostId(host.getId());
    }

    public Reservation findByEmails(String guestEmail, String hostEmail) {

        Host host = hostRepository.findByEmail(hostEmail);

        List<Reservation> pew = reservationRepo.findbyHostId(host.getId());


        //TODO: adjust collectToList
        return pew.stream()
                .filter(reservation -> reservation.getGuest().getEmail().equalsIgnoreCase(guestEmail))
                .findFirst()
                .orElse(null);
    }

    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(reservationRepo.createReservation(reservation));
        return result;
    }



    public Result<Reservation> update(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

      List<Reservation> pew = reservationRepo.findbyHostId(reservation.getHost().getId());

      Reservation existing = pew.stream().
              filter(reservation1 -> reservation1.getId().equalsIgnoreCase(reservation.getId()))
              .findFirst()
              .orElse(null);

      if(!existing.getId().equals(reservation.getId()) || existing.getHost() != reservation.getHost()
              || existing.getGuest() != reservation.getGuest() ){
          result.addErrorMessage("You can only change start date/end date");
      }

        boolean success = reservationRepo.update(reservation);
        if (!success) {
            result.addErrorMessage("Could not find reservaion id:" + reservation.getId());
        }
        return result;
    }

    public Result<Reservation> deleteResById(String resId, Host host) throws DataException {
        Result<Reservation> result = new Result<>();
        List<Reservation> reservations = reservationRepo.findbyHostId(host.getId());

        Reservation reservation =  reservations.stream()
                .filter(reservation1 -> reservation1.getId().equalsIgnoreCase(resId))
                .findFirst()
                .orElse(null);

        if(!reservationRepo.deleteResById(resId,host)){
            result.addErrorMessage("res Id not found ");
        }

        if(reservation == null){
            result.addErrorMessage("Nothing to delete");
            return result;
        }
        return result;
    }

    private Result<Reservation> validate(Reservation reservation) {

        Result<Reservation> result = validatesNulls(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        isOverLapping(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        return result;

    }


    private Result<Reservation> validatesNulls(Reservation res) {
        Result<Reservation> result = new Result<>();
// check the other nullcases
        if (res == null) {
            result.addErrorMessage("There is nothing here");
        }


        if (res.getHost() == null) {
            result.addErrorMessage("please put email of host");
        }

        if (res.getGuest() == null) {
            result.addErrorMessage("please put email of guests");
        }
        return result;
    }

    private Result<Reservation> isOverLapping(Reservation reservation, Result<Reservation> result) {


        List<Reservation> all = findByHostId(reservation.getHost().getId());
        for (Reservation value : all) {
            //TODO: remember the diagram/ build unit tests
            boolean totallyBefore = reservation.getEndDate().isBefore(value.getStartDate())
                    || reservation.getEndDate().equals(value.getStartDate());

            boolean totallyAfter = reservation.getStartDate().isAfter(value.getEndDate())
                    ||reservation.getStartDate().equals(value.getEndDate());

            if(!(totallyAfter & totallyBefore)){
                result.addErrorMessage("These are bad dates give me better ones");
            }

        }
        return result;
    }
}
