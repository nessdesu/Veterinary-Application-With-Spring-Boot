package com.example.nesdeneme;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "doctors")
public class Doctors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String vetname;

    @Column(nullable = false, unique = true)
    private String extensionNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVetname() {
        return vetname;
    }

    public void setVetname(String vetname) {
        this.vetname = vetname;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }
}
