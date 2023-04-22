/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import mainClasses.User;

/**
 *
 * @author Mike
 */
public class Doctor extends User {

    int doctor_id;

    public Doctor(String username, String email, String password) {
        super(username, email, password);
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
}
