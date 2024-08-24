package org.gorohoroh.eventregistration.registrations;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/registrations")
public class RegistrationController {

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationRepository registrationRepository;
    private final AttendeeRepository attendeeRepository;

    public RegistrationController(RegistrationRepository registrationRepository, AttendeeRepository attendeeRepository) {
        this.registrationRepository = registrationRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @PostMapping
    public Registration create(@RequestBody @Valid Registration registration) {
        String ticketCode = UUID.randomUUID().toString();
        registration.setTicketCode(ticketCode);
        logger.info("Created a new registration to event {} for {} with ticket code {}", registration.getProductId(), registration.getAttendee(), ticketCode);
        logger.info(String.valueOf(registration));
        return registrationRepository.save(registration);
    }

    @GetMapping(path = "/{ticketCode}")
    public Registration get(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
    }

    @PutMapping
    public Registration update(@RequestBody Registration registration) {
        String ticketCode = registration.getTicketCode();
        var existing = registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));

        return registrationRepository.save(new Registration(existing.getId(), existing.getProductId(), ticketCode, registration.getAttendee()));
    }

    @DeleteMapping(path = "/{ticketCode}")
    public void delete(@PathVariable("ticketCode") String ticketCode) {
        registrationRepository.deleteByTicketCode(ticketCode);
    }

}
