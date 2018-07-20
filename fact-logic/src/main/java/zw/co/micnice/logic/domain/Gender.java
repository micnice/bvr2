/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.domain;

/**
 *
 * @author micnice
 */
public enum Gender {

    MALE("Male"), FEMALE("Female");

    private Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }

    @Override
    public String toString() {
        return genderName;
    }

    private final String genderName;

}
