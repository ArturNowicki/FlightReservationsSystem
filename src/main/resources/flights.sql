USE FlightsReservations;

drop table if exists Airport;
CREATE TABLE Airport (
airportId int NOT NULL AUTO_INCREMENT,
airportCity varchar(255),
PRIMARY KEY (airportId),
UNIQUE KEY (airportCity)
);
INSERT INTO Airport (airportCity) VALUES
('Warsaw'), ('London'), ('Frankfurt'), ('Vienna'), ('Paris'), ('Berlin');


drop table if exists Flight;
CREATE TABLE Flight (
flightId int NOT NULL AUTO_INCREMENT,
departureTime time,
arrivalTime time,
departureCity int NOT NULL REFERENCES Airport(airportId),
arrivalCity int NOT NULL REFERENCES Airport(airportId),
PRIMARY KEY (flightId),
UNIQUE KEY (departureTime, departureCity, arrivalTime, arrivalCity)
);
INSERT INTO Flight (departureTime, arrivalTime, departureCity, arrivalCity) VALUES
	('11:30:00', '12:20:00', 1, 2), ('15:15:00', '16:05:00', 2, 1),
	('09:20:00', '11:25:00', 3, 4), ('14:45:00', '16:50:00', 4, 3),
	('15:00:00', '17:05:00', 1, 5), ('19:10:00', '21:15:00', 5, 1),
	('12:30:00', '14:00:00', 6, 2), ('13:50:00', '15:20:00', 2, 6);


drop table if exists Seat;
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


drop table if exists FlightDays;
CREATE TABLE FlightDays (
dayId int NOT NULL AUTO_INCREMENT,
dayOfWeek VARCHAR(5),
PRIMARY KEY (dayId)
);
INSERT INTO FlightDays (dayOfWeek) VALUES ('mon'), ('tue'), ('wed'), ('thu'),
							  ('fri'), ('sat'), ('sun');


drop table if exists FlightSchedule;
CREATE TABLE FlightSchedule (
	scheduleId int NOT NULL AUTO_INCREMENT,
	idFlight int NOT NULL REFERENCES Flight(flightId),
    idDay int NOT NULL REFERENCES FlightDays(dayId),
    PRIMARY KEY (scheduleId),
    UNIQUE KEY (idFlight, idDay)
);
INSERT INTO FlightSchedule(idFlight, idDay) VALUES
	(1, 1), (2, 1), (3, 1), (4, 1),
	(5, 2), (6, 2), (3, 2), (4, 2), (7, 2), (8, 2),
	(1, 3), (2, 3), (3, 3), (4, 3), (7, 3), (8, 3),
	(1, 4), (2, 4), (3, 4), (4, 4), (5, 4), (6, 4),
	(1, 5), (2, 5), (3, 5), (4, 5),
	(1, 6), (2, 6), (5, 6), (6, 6),
	(1, 7), (2, 7), (3, 7), (4, 7), (7, 7), (8, 7);


drop table if exists SeatReservation;
CREATE TABLE SeatReservation (
	reservationId int NOT NULL AUTO_INCREMENT,
    idSchedule int REFERENCES FlightSchedule(scheduleId),
    idSeat int REFERENCES Seat(seatId),
    PRIMARY KEY (reservationId),
    UNIQUE KEY (idSchedule, idSeat)
);

INSERT INTO SeatReservation (idSchedule, idSeat) VALUES
	(1, 1), (1, 4), (18, 5);

-- display availiable airports
Select * from Airport ORDER BY airportId;

-- display all flights from selected airport
SELECT dayOfWeek, departureTime, a.airportCity, b.airportCity, arrivalTime
FROM Airport a
JOIN Flight ON (a.airportId = departureCity)
JOIN Airport b ON (b.airportId = arrivalCity)
JOIN FlightSchedule ON (flightId=idFlight)
JOIN FlightDays ON (dayId = IdDay)
WHERE a.airportCity='London'
ORDER BY dayId;


-- display all flights from selected day and airport
SELECT dayOfWeek, departureTime, a.airportCity, b.airportCity, arrivalTime
FROM Airport a
JOIN Flight ON (a.airportId = departureCity)
JOIN Airport b ON (b.airportId = arrivalCity)
JOIN FlightSchedule ON (flightId=idFlight)
JOIN FlightDays ON (dayId = IdDay)
WHERE dayId=3 AND a.airportCity='London';

-- display flifhts from airport
SELECT departureTime, departureCity airportCity, arrivalTime, arrivalCity, airportCity FROM Flight
WHERE departureCity=(SELECT airportId FROM Airport WHERE airportId=1);

SELECT airportId FROM Airport WHERE airportId=1;

-- display all flights from selected city and day 
SELECT dayOfWeek, flightId, departureTime, departureCity, arrivalTime, arrivalCity FROM Flight f
JOIN FlightSchedule s on f.flightId=s.idFlight
JOIN FlightDays d on s.idDay=d.dayId
WHERE dayOfWeek='mon' AND departureCity='Warsaw' ORDER BY departureTime;

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
WHERE dayOfWeek='mon'
AND departureCity = 'Warsaw'
ORDER BY departureTime);
SELECT * FROM Seat WHERE seatNo NOT IN (SELECT noSeat FROM tempTab);

drop table tempTab;
