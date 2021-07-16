package com.example.nesdeneme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.ui.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String pettype;

    @Column(nullable = false, length = 30)
    private String doctorname;

    @Column(nullable = false, length = 10)
    private String appointdate;

    @Column(nullable = false, length = 10)
    private String appointstarthour;

    @Column(nullable = false, length = 10)
    private String appointendhour;

    @Column(nullable = false, length = 30)
    private String patientname;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 10)
    private String phonenumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPettype() {
        return pettype;
    }

    public void setPettype(String pettype) {
        this.pettype = pettype;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getAppointdate() {
        return appointdate;
    }

    public void setAppointdate(String appointdate) {
        this.appointdate = appointdate;
    }

    public String getAppointstarthour() {
        return appointstarthour;
    }

    public void setAppointstarthour(String appointstarthour) {
        this.appointstarthour = appointstarthour;
    }

    public String getAppointendhour() {
        return appointendhour;
    }

    public void setAppointendhour(String appointendhour) {
        this.appointendhour = appointendhour;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}
