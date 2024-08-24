package org.gorohoroh.eventregistration.registrations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.gorohoroh.eventregistration.events.Organizer;

@Entity
@Table(name = "registrations")
public final class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Product ID is required")
    @Column(nullable = false)
    private Integer productId;

    @Column
    private String ticketCode;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "attendee_id", referencedColumnName = "id", nullable = false)
    private Attendee attendee;

    public Registration(int id, Integer productId, String ticketCode, Attendee attendee) {
        this.id = id;
        this.productId = productId;
        this.ticketCode = ticketCode;
        this.attendee = attendee;
    }

    public Registration() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }
}
