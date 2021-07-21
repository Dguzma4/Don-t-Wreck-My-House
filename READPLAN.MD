#Don't Wreck My House

## High Level Requirments
* The administrator may view existing reservations for a host
* The administat may create reservation for a guest with a host
* The administrator may edit exisiting reservations.
* The administrator may cancel a furture reservation.

## Models
### Reservation
* ID
* startDate
* endDate
* guest
* total 
* ist

####Potentially make a person inferface that wraps both guests and hosts maybe....

### Guest
* GuestID
* FirstName
* LastName
* Email
* Phone #
* State (abrv)

### Host
* ID
* LastName 
* Email
* PhoneNumber
* address
* city
* state
* postal_code
* standardRate
* WeekendRate

## Data Layer

###Data Classes
* DataException

* GuestFileRepository
* HostFileRepository 
* ReservationFileRepository

###DataSteps
* [ ] Create project DontWreckMyHouse
* [ ] add jUnit to pom.xml
* [ ] add package for models
* [ ] Create Host,Guest,Reservation class's
* [ ] Create GuestFileRepository,HostFileRepository, ReservationFileRepository


####ReservationFileRepository 
* [ ] import the directory
* [ ] FindReservationByStartDate
* [ ] DeleteReservation
* [ ] findReservationByHostEmail
* [ ] CreateReservation
* [ ] UpdateReservation
* [ ] WriteAll
* [ ] serialize
* [ ] deserialize
* [ ] getFilePath perhaps it has an input of HostID find a easy way for them to get this
* um how do i get the file..... the names of the file reservations seem to just be the host id????
#### GuestFileRepository

* [ ] findAll 
* [ ] findByiD
* [ ] findByState
* [ ] findByEmail


#### HostFileRepository
* [ ] findAll
* [ ] findById
* [ ] findByPostalCode


##### Potentially make a interface of People that contain both Guest and Hosts

##DomainLayer
###Domain Rules
* Both guest and host emails are required
* Start Date and End Date
* Start Date should be in future as well as end date,
  no overlapping reservation dates per location, Start date must before end date

### Domain Steps
* [ ] Make ReservationService
* [ ] Make *PersonService 

####ReservationService
* [ ] FindByHostId
* [ ] FindByHostEmail
* [ ] add
* [ ] validates
* [ ] delete 

#### PersonService
* [ ] FindByEmail
* [ ] findByState
* [ ] findByPostalCode


##UI Layer
###UI Classes
* console IO
* controller
* MainMenuOptions *enum
* view 

####ConsoleIO
* [ ] readRequiredString
* [ ] readString
* [ ] readBoolean
* [ ] readLocalDate
* [ ] readBigDecimal


####Controller
* [ ] run
* [ ] run loop
* [ ] ViewExistingReservationsForHost
* [ ] CreateReservation
* [ ] UpdateReservation
* [ ] CancelReservation


##Test
- Test everything you make
*  [ ] Make test for each file repo
* [ ] MakeDoubles 
* [ ] Make double test to test service stuff



Steal get value from forages to get total -.-
