package ua.lviv.iot.parking.dataaccess;

import org.springframework.stereotype.Repository;
import ua.lviv.iot.parking.model.ParkingUserCard;
import ua.lviv.iot.parking.reader.ParkingUserCardReader;
import ua.lviv.iot.parking.writer.ParkingUserCardWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ParkingUserCardRepository {
    private Map<Integer, ParkingUserCard> parkingUserCards = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();
    private final String csvFilePath = "parkingUserCard-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".csv";
    private ParkingUserCardWriter parkingUserCardWriter = new ParkingUserCardWriter();
    private ParkingUserCardReader parkingUserCardReader = new ParkingUserCardReader();



    public List<ParkingUserCard> getParkingUserCards() {
        return new LinkedList<>(parkingUserCards.values());
    }

    public ParkingUserCard getParkingUserCard(Integer parkingUserCardId) {
        return parkingUserCards.get(parkingUserCardId);
    }

    public ParkingUserCard createParkingUserCard(ParkingUserCard parkingUserCard) {
        parkingUserCard.setId(idCounter.incrementAndGet());
        parkingUserCards.put(parkingUserCard.getId(), parkingUserCard);
        saveDataToCsv();
        return parkingUserCard;
    }

    public boolean deleteParkingUserCard(Integer parkingUserCardId) {
        boolean removed = parkingUserCards.remove(parkingUserCardId) != null;
        if (removed) {
            saveDataToCsv();
        }
        return removed;
    }

    public ParkingUserCard updateParkingUserCard(Integer parkingUserCardId, ParkingUserCard updatedParkingUserCard) {
        if (parkingUserCards.containsKey(parkingUserCardId)) {
            updatedParkingUserCard.setId(parkingUserCardId);
            parkingUserCards.put(parkingUserCardId, updatedParkingUserCard);
            saveDataToCsv();
            return updatedParkingUserCard;
        }
        return null;
    }
    public void loadDataFromCsv() {
        List<ParkingUserCard> parkingUserCards = parkingUserCardReader.readDataFromCsv(csvFilePath);
        for (ParkingUserCard parkingUserCard : parkingUserCards) {
            createParkingUserCard(parkingUserCard);
        }
    }

    private void saveDataToCsv() {
        parkingUserCardWriter.writeDataToCsv(new LinkedList<>(parkingUserCards.values()), csvFilePath);
    }
}
