package com.buildledger.backend.service.sells;

import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.sos.ParkingPlace;

public class StaticService {
    public static String convertObjectsToString(Sell sell) {
        StringBuilder sb = new StringBuilder();
        if (!sell.getApartments().isEmpty()) {
            for (Apartment apartment : sell.getApartments()) {
                sb.append(apartment.getNumber());
                sb.append(" ");
            }
        }
        if (!sell.getGarages().isEmpty()) {
            for (Garage garage : sell.getGarages()) {
                sb.append(garage.getNumber());
                sb.append(" ");
            }
        }
        if (!sell.getParkingPlaces().isEmpty()) {
            for (ParkingPlace parkingPlace : sell.getParkingPlaces()) {
                sb.append(parkingPlace.getNumber());
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
