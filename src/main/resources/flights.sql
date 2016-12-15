USE FlightsReservations;

drop table if exists SeatReservation;
drop table if exists FlightSchedule;
drop table if exists Seat;
drop table if exists Flight;
drop table if exists Airport;

CREATE TABLE Airport (
airportId int NOT NULL AUTO_INCREMENT,
airportCity varchar(255),
PRIMARY KEY (airportId),
UNIQUE KEY (airportCity)
);
INSERT INTO Airport (airportCity) VALUES
('Warsaw'), ('London'), ('Frankfurt'), ('Vienna'), ('Paris'), ('Berlin');

CREATE TABLE Flight (
flightId int NOT NULL AUTO_INCREMENT,
departureTime time,
arrivalTime time,
departureCity int NOT NULL,
arrivalCity int NOT NULL,
PRIMARY KEY (flightId),
UNIQUE KEY (departureTime, departureCity, arrivalTime, arrivalCity),
FOREIGN KEY (departureCity) REFERENCES Airport(airportId),
FOREIGN KEY (arrivalCity) REFERENCES Airport(airportId)
);
INSERT INTO Flight (departureTime, arrivalTime, departureCity, arrivalCity) VALUES
	('11:30:00', '12:20:00', 1, 2), ('15:15:00', '16:05:00', 2, 1),
	('09:20:00', '11:25:00', 3, 4), ('14:45:00', '16:50:00', 4, 3),
	('15:00:00', '17:05:00', 1, 5), ('19:10:00', '21:15:00', 5, 1),
	('12:30:00', '14:00:00', 6, 2), ('13:50:00', '15:20:00', 2, 6);


CREATE TABLE Seat (
seatId int NOT NULL AUTO_INCREMENT,
seatNo VARCHAR(10) NOT NULL,
PRIMARY KEY (seatId),
UNIQUE KEY (seatNo)
);
INSERT INTO Seat (seatNo) VALUES
('A01'), ('A02'), ('A03'), ('A04'),
('B01'), ('B02'), ('B03'), ('B04'),
('C01'), ('C02'), ('C03'), ('C04'),
('D01'), ('D02'), ('D03'), ('D04'),
('E01'), ('E02'), ('E03'), ('E04');


-- drop table if exists FlightDays;
-- CREATE TABLE FlightDays (
-- dayId int NOT NULL AUTO_INCREMENT,
-- dayOfWeek VARCHAR(5),
-- PRIMARY KEY (dayId)
-- );
-- INSERT INTO FlightDays (dayOfWeek) VALUES ('MONDAY'), ('TUESDAY'), ('WEDNESDAY'), ('THURSDAY'),
-- 							  ('FRIDAY'), ('SATURDAY'), ('SUNDAY');


CREATE TABLE FlightSchedule (
	scheduleId int NOT NULL AUTO_INCREMENT,
    dayOfWeek Varchar(10) NOT NULL,
	idFlight int NOT NULL,
    PRIMARY KEY (scheduleId),
    UNIQUE KEY (idFlight, dayOfWeek),
    FOREIGN KEY (idFlight) REFERENCES Flight(flightId)
);
INSERT INTO FlightSchedule(dayOfWeek, idFlight) VALUES
	('MONDAY', 1), ('MONDAY', 2), ('MONDAY', 3), ('MONDAY', 4),
	('TUESDAY', 5), ('TUESDAY', 6), ('TUESDAY', 3), ('TUESDAY', 4), ('TUESDAY', 7), ('TUESDAY', 8),
	('WEDNESDAY', 1), ('WEDNESDAY', 2), ('WEDNESDAY', 3), ('WEDNESDAY', 4), ('WEDNESDAY', 7), ('WEDNESDAY', 8),
	('THURSDAY', 1), ('THURSDAY', 2), ('THURSDAY', 3), ('THURSDAY', 4), ('THURSDAY', 5), ('THURSDAY', 6),
	('FRIDAY', 1), ('FRIDAY', 2), ('FRIDAY', 3), ('FRIDAY', 4),
	('SATURDAY', 1), ('SATURDAY', 2), ('SATURDAY', 5), ('SATURDAY', 6),
	('SUNDAY', 1), ('SUNDAY', 2), ('SUNDAY', 3), ('SUNDAY', 4), ('SUNDAY', 7), ('SUNDAY', 8);

CREATE TABLE SeatReservation (
	reservationId int NOT NULL AUTO_INCREMENT,
    idSchedule int NOT NULL,
    idSeat int NOT NULL REFERENCES Seat(seatId),
    PRIMARY KEY (reservationId),
    UNIQUE KEY (idSchedule, idSeat),
    constraint FOREIGN KEY (idSchedule) REFERENCES FlightSchedule(scheduleId)
);


INSERT INTO SeatReservation (idSchedule, idSeat) VALUES
	(1, 1), (1, 4), (18, 5);




delete from Airport where airportId=1;


-- display availiable airports
Select * from Airport ORDER BY airportId;

-- display all flights from selected airport
SELECT flightId, dayOfWeek, departureTime, a.airportCity as departure, b.airportCity as arrival, arrivalTime
FROM Airport a
JOIN Flight ON (a.airportId = departureCity)
JOIN Airport b ON (b.airportId = arrivalCity)
JOIN FlightSchedule ON (flightId=idFlight)
JOIN FlightDays ON (dayId = IdDay)
WHERE a.airportCity='Warsaw'
ORDER BY dayId;


SELECT * FROM Flight order by flightId;

-- display all flights from selected day and airport v1
SELECT scheduleId, dayOfWeek, idFlight, departureTime, a.airportCity, b.airportCity, arrivalTime FROM FlightSchedule 
JOIN Flight ON (idFlight=flightId)
JOIN Airport a ON (departureCity = a.airportId)
JOIN Airport b ON (arrivalCity = b.airportId)
WHERE dayOfWeek = 'MONDAY'order by scheduleId;

-- display all flights from selected day and airport v2
SELECT dayOfWeek, departureTime, a.airportCity, b.airportCity, arrivalTime
FROM Airport a
JOIN Flight ON (a.airportId = departureCity)
JOIN Airport b ON (b.airportId = arrivalCity)
JOIN FlightSchedule ON (flightId=idFlight)
WHERE dayOfWeek = 'WEDNESDAY' AND a.airportCity='London';

-- display flifhts from airport
SELECT departureTime, departureCity, arrivalTime, arrivalCity FROM Flight
WHERE departureCity=(SELECT airportId FROM Airport WHERE airportId=1);

SELECT airportId FROM Airport WHERE airportId=1;

-- display all flights from selected city and day 
SELECT dayOfWeek, flightId, departureTime, departureCity, arrivalTime, arrivalCity FROM Flight f
JOIN FlightSchedule s on f.flightId=s.idFlight
JOIN FlightDays d on s.idDay=d.dayId
WHERE dayOfWeek='MONDAY' AND departureCity='Warsaw' ORDER BY departureTime;

-- select available seats for selected flight
drop table if exists tempTab;
CREATE TABLE tempTab (
idSchedule int,
noSeat varchar(10)
);

INSERT INTO tempTab (SELECT scheduleId, seatNo FROM Flight f
JOIN FlightSchedule fs on f.flightId=fs.idFlight
JOIN FlightDays d on fs.idDay=d.dayId
JOIN SeatReservation sr on scheduleId = sr.idSchedule
JOIN Seat s on sr.idSeat = s.seatId
WHERE dayOfWeek='MONDAY'
AND departureCity = 'Warsaw'
ORDER BY departureTime);
SELECT * FROM Seat WHERE seatNo NOT IN (SELECT noSeat FROM tempTab);

drop table tempTab;
