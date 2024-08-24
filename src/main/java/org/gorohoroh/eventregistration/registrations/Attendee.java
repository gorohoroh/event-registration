package org.gorohoroh.eventregistration.registrations;

import jakarta.persistence.*;

@Entity
@Table(name = "attendees")
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(nullable = false)
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    @Column(nullable = false)
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Column(nullable = false)
    private String phone;
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone;}
}
