package ch.etmles.payroll.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
public class Department {

    private String name;

    public Department(){}

    public Department(String name){
        this.setName(name);
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.name);
    }

    @Override
    public String toString(){
        return "Department{"+ ", name='" + this.getName() + '}';
    }
}
