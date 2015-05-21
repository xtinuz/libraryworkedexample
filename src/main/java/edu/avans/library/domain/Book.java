/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ppthgast
 */
public class Book {

    private final int isbn;
    private final String title;
    private final String author;
    private final int edition;

    private final List<Copy> copies;
    private final List<Reservation> reservations;

    public Book(int isbn, String title, String author, int edition) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.edition = edition;

        copies = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public int getISBN() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getEdition() {
        return edition;
    }

    public String getTitle() {
        return title;
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;

        if (o == this) {
            // same instance of the class, so equal by definition
            equal = true;
        } else {
            if (o instanceof Book) {
                Book b = (Book) o;

                // Book is identified by its ISBN, so only checking ISBN is sufficient.
                equal = this.isbn == b.isbn;
            }
        }

        return equal;
    }

    @Override
    public int hashCode() {
        // This implementation is based on the best practice described 
        // in Effective Java, 2nd edition, Joshua Bloch.

        // ISBN is unique, so satisfying as hashCode.
        return isbn;
    }
}
