/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.datastorage;

import java.util.ArrayList;
import edu.avans.library.domain.Member;
import edu.avans.library.domain.Reservation;
import java.util.List;

/**
 * This is a stub for ReservationDAO. Purpose of this stub is to make the unit
 * test independent of the database.
 *
 * @author Erco
 */
public class ReservationDAO {

    public List<Reservation> findReservations(Member member) {
        System.out.println("ReservationDAO.findReservations() stub");
        return new ArrayList<>();
    }

}
