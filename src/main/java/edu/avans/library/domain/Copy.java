/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.domain;

/**
 *
 * @author ppthgast
 */
public class Copy {

    private final int copyID;
    private final int lendingPeriod;

    // null, if this copy has not been lent.
    private Loan loan;

    private final Book book;

    public Copy(int copyID, int lendingPeriod, Book book) {
        this.copyID = copyID;
        this.lendingPeriod = lendingPeriod;
        this.book = book;

        loan = null;
    }

    public int getCopyID() {
        return copyID;
    }

    public int getLendingPeriod() {
        return lendingPeriod;
    }

    public Book getBook() {
        return book;
    }

    public void setLoan(Loan newLoan) {
        loan = newLoan;
    }

    public Loan getLoan() {
        return loan;
    }

    public boolean isLent() {
        return loan != null;
    }
}
