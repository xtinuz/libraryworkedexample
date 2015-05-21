/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.domain;

import java.util.Date;

/**
 *
 * @author ppthgast
 */
public class Loan {

    private final Date returnDate;

    private final Member member;
    private final Copy copy;

    /**
     * Constructor for Loan. Because a Loan is associated with a Member and a Copy,
     * Member and Copy are constructor parameters.
     *
     * @param member the Member who has the Loan.
     * @param copy the Copy that the Member has lent.
     * @param returnDate date when the Copy needs to be returned.
     */
    public Loan(Date returnDate, Member member, Copy copy) {
        this.returnDate = returnDate;
        this.member = member;
        this.copy = copy;
    }

    /**
     * Accessor method to get the Member, associated with the loan.
     *
     * @return the Member
     */
    public Member getMember() {
        return member;
    }

    /**
     * Accessor method to get the Copy, associated with the loan.
     *
     * @return the Copy
     */
    public Copy getCopy() {
        return copy;
    }

    /**
     * Accessor method to get the return date for the Loan.
     *
     * @return de return date
     */
    public Date getReturnDate() {
        return returnDate;
    }
}
