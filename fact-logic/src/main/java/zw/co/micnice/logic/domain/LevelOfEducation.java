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
public enum LevelOfEducation {

    PRIMARY("Primary"), SECONDARY("Secondary"), TERTIARY("Tertiary");

    private LevelOfEducation(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName() {
        return levelName;
    }

    @Override
    public String toString() {
        return levelName;
    }

    private final String levelName;

}
