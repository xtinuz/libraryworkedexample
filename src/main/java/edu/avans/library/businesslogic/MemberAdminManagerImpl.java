/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.businesslogic;

import java.util.HashMap;
import edu.avans.library.datastorage.LoanDAO;
import edu.avans.library.datastorage.MemberDAO;
import edu.avans.library.datastorage.ReservationDAO;
import edu.avans.library.domain.Member;
import edu.avans.library.domain.Loan;
import edu.avans.library.domain.Member;
import edu.avans.library.domain.Reservation;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ppthgast
 */
public class MemberAdminManagerImpl implements MemberAdminManager {

    private final Map<Integer, Member> members;

    public MemberAdminManagerImpl() {
        members = new HashMap<>();

//        fillTestData();
    }

//    private void fillTestData() {
//        members.put(1000, new Member(1000, "Pascal", "van Gastel"));
//        members.put(1001, new Member(1001, "Erco", "Argante"));
//        members.put(1002, new Member(1002, "Marice", "Bastiaense"));
//        members.put(1004, new Member(1004, "Floor", "van Gastel"));
//        members.put(1005, new Member(1005, "Jet", "van Gastel"));
//        members.put(1006, new Member(1006, "Marin", "van Gastel"));
//    }

    @Override
    public Member findMember(int membershipNumber) {
        Member member = members.get(membershipNumber);

        if (member == null) {
            // Member may not have been loaded from the database yet. Try to
            // do so.
            MemberDAO memberDAO = new MemberDAO();
            member = memberDAO.findMember(membershipNumber);

            if (member != null) {
                // Member successfully read. Now read its loans.
                LoanDAO loanDAO = new LoanDAO();
                List<Loan> loans = loanDAO.findLoans(member);

                for (Loan loan : loans) {
                    member.addLoan(loan);
                }

                // And read the reserverations from the database.
                ReservationDAO reservationDAO = new ReservationDAO();
                List<Reservation> reservations = reservationDAO.findReservations(member);

                for (Reservation reservation : reservations) {
                    member.addReservation(reservation);
                }

                // Cache the member that has been read from the database, to
                // avoid querying the database each time a member is needed.
                members.put(membershipNumber, member);
            }
        }

        return member;
    }

    @Override
    public boolean removeMember(Member memberToRemove) {
        boolean result = false;

        if (memberToRemove.isRemovable()) {

            int membershipNumber = memberToRemove.getMembershipNumber();

            // Let the member remove itself as a domain object. Do this before
            // removing from the database. In case something goes wrong, we
            // still have the data in the database. If first the member was
            // removed from the database, we might end up in an inconsistent
            // state.
            result = memberToRemove.remove();

            if (result) {
                // Let the member remove itself from the database.
                MemberDAO memberDAO = new MemberDAO();
                result = memberDAO.removeMember(memberToRemove);

                // In case something goes wrong here, we need to roll back.
                // But that's too much for this version of the POC.
            }

            // Finally, remove the member from the map in this manager.
            members.remove(membershipNumber);
        } else {
            result = false;
        }

        return result;
    }
}
