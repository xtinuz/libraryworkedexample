/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.domain;

import edu.avans.library.domain.Member;
import edu.avans.library.domain.Loan;
import edu.avans.library.domain.Reservation;
import edu.avans.library.domain.Member;
import java.util.ArrayList;

/**
 * This is a stub for Member. Purpose of the stub is to simulate the error
 * situation the Member.remove() fails. This situation cannot be reproduced with
 * the production code.
 *
 * @author Erco
 */
public class Member {

    private int membershipNumber;
    private String firstname;
    private String lastname;
    private String street;
    private String houseNumber;
    private String city;
    private String phoneNumber;
    private String emailaddress;
    private double fine;

    private final ArrayList<Loan> loans;
    private final ArrayList<Reservation> reservations;

    public Member(int membershipNumber, String firstname, String lastname) {
        this.membershipNumber = membershipNumber;
        this.firstname = firstname;
        this.lastname = lastname;

        street = "";
        houseNumber = "";
        city = "";
        phoneNumber = "";
        emailaddress = "";
        fine = 0.00;

        loans = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailaddress;
    }

    public void setEmailAddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public void setLoans(Loan[] loans) {
        removeAllLoans();

        for (Loan theLoan : loans) {
            addLoan(theLoan);
        }
    }

    public void addLoan(Loan newLoan) {
        loans.add(newLoan);
    }

    public void removeAllLoans() {
        loans.clear();
    }

    public void addReservation(Reservation newReservation) {
        reservations.add(newReservation);
    }

    public boolean remove() {
        System.out.println("Member.remove() stub");
        // Result is always true. If we later on use a database from which
        // the member needs to be removed as well, we can return a more
        // meaningfull value.
        boolean result = true;

        removeAllReservations();

        // ***** begin of difference between stub and production oode *****
        if (membershipNumber == 1002) {
            return false;
        }
        // ***** end of difference between stub and production oode *****

        return result;
    }

    private void removeAllReservations() {
        while (!reservations.isEmpty()) {
            Reservation reservation = reservations.get(0);
            reservation.remove();
        }

        // Alternatives for this construction with the temporary copy of the
        // ArrayList field are for example:
        //
        // -- 1 --
        // Start removing the reservations starting from the END OF the list
        // moving backwards until the list is empty. Starting from the first
        // element towards the end of the list will not succeed since the
        // index values are messed up.
        //    int index = reservations.size() - 1;
        //    
        //    while(index >= 0)
        //    {
        //        reservations.r
        //        Reservation reservation = reservations.get(index);
        //        reservation.remove();
        //        
        //        index--;
        //    }
        //
        // -- 2 --
        //
        //    ArrayList<Reservation> tempList = new ArrayList<>(reservations);
        //      
        //    for(Reservation r: reservations)
        //    {
        //        r.remove();
        //    }
        //
        // This defenitely needs clarification. Using a for each or iterator
        // on a list, has the limitation that deletion of elements from that
        // list is not allowed. Therefore a new ArrayList object is created,
        // referring to the same Reservation objects as the reservations field.
        // The local variable tempList is not modified during execution of the
        // loop. The reservations field on the other hand is.
    }

    public boolean hasLoans() {
        return !loans.isEmpty();
    }

    public boolean hasFine() {
        return fine > 0;
    }

    public boolean hasReservations() {
        return !reservations.isEmpty();
    }

    public boolean isRemovable() {
        return !hasLoans() && !hasFine();
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;

        if (o == this) {
            // Dezelfde instantie van de klasse, dus per definitie hetzelfde.
            equal = true;
        } else {
            if (o instanceof Member) {
                Member l = (Member) o;

                // Boek wordt geidentificeerd door ISBN, dus alleen hierop
                // controlleren is voldoend.
                equal = this.membershipNumber == l.membershipNumber;
            }
        }

        return equal;
    }

    @Override
    public int hashCode() {
        // Deze implementatie is gebaseerd op de best practice zoals beschreven
        // in Effective Java, 2nd edition, Joshua Bloch.

        // membershipNumber is uniek, dus voldoende als hashcode.
        return membershipNumber;
    }
}
