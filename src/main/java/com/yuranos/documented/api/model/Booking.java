
package com.yuranos.documented.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "destination"})
@AllArgsConstructor
public class Booking implements Serializable
{

    final static long serialVersionUID = 7422041217247416307L;
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String destination;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date departureDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date arrivalDate;
    @NotNull
    @Size(min = 3, max = 25)
    private String passengerName;
    @NotNull
    @Size(min = 3, max = 25)
    private String passengerSurname;
    @NotNull
    @DecimalMin("12.0")
    @DecimalMax("100.0")
    private Long age;

    /**
     * Returns the id.
     *
     * @return
     *     id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id
     *     the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the destination.
     *
     * @return
     *     destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Set the destination.
     *
     * @param destination
     *     the new destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns the departureDate.
     *
     * @return
     *     departureDate
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * Set the departureDate.
     *
     * @param departureDate
     *     the new departureDate
     */
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Returns the arrivalDate.
     *
     * @return
     *     arrivalDate
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Set the arrivalDate.
     *
     * @param arrivalDate
     *     the new arrivalDate
     */
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Returns the passengerName.
     *
     * @return
     *     passengerName
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Set the passengerName.
     *
     * @param passengerName
     *     the new passengerName
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * Returns the passengerSurname.
     *
     * @return
     *     passengerSurname
     */
    public String getPassengerSurname() {
        return passengerSurname;
    }

    /**
     * Set the passengerSurname.
     *
     * @param passengerSurname
     *     the new passengerSurname
     */
    public void setPassengerSurname(String passengerSurname) {
        this.passengerSurname = passengerSurname;
    }

    /**
     * Returns the age.
     *
     * @return
     *     age
     */
    public Long getAge() {
        return age;
    }

    /**
     * Set the age.
     *
     * @param age
     *     the new age
     */
    public void setAge(Long age) {
        this.age = age;
    }

}
