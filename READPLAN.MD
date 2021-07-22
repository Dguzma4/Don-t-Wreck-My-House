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
* [x ] Create project DontWreckMyHouse
* [ x] add jUnit to pom.xml
* [ x] add package for models
* [ x] Create Host,Guest,Reservation class's
* [ x] Create GuestFileRepository,HostFileRepository, ReservationFileRepository


####ReservationFileRepository 
* [x] import the directory
* [ ] FindReservationByStartDate
* [x] DeleteReservation
* [ ] findReservationByHostEmail
* [x] CreateReservation
* [ ] UpdateReservation
* [x] WriteAll
* [x] serialize
* [x] deserialize
* [x] getFilePath perhaps it has an input of HostID find a easy way for them to get this
* um how do i get the file..... the names of the file reservations seem to just be the host id????
#### GuestFileRepository

* [x] findAll 
* [x] findByiD
* [x] findByState
* [x] findByEmail


#### HostFileRepository
* [x] findAll
* [x] findById
* [x] findByPostalCode


##### Potentially make a interface of People that contain both Guest and Hosts

##DomainLayer
###Domain Rules
* Both guest and host emails are required
* Start Date and End Date
* Start Date should be in future as well as end date,
  no overlapping reservation dates per location, Start date must before end date

### Domain Steps
* [x] Make ReservationService
* [x] Make *PersonService 

####ReservationService
* [x] FindByHostId
* [x] FindByHostEmail
* [ ] add
* [ ] validates
* [ ] delete 

#### PersonService
* [x] FindByEmail
* [ ] findByState
* [x] findByPostalCode


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
