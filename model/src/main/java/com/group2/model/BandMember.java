package com.group2.model;

import org.jetbrains.annotations.Contract;

/**
 * @author Timothy
 */
class BandMember {
    String memberName;
    int bandID;

    /**
     * empty constructor
     */
    @Contract(pure = true)
    public BandMember() {
    }

    /**
     * constructor with all parameters
     * @param memberName the name of this particular band member
     * @param bandID the band's unique integer id
     */
    @Contract(pure = true)
    public BandMember(String memberName, int bandID) {
        this.memberName = memberName;
        this.bandID = bandID;
    }

    /**
     * getter for member name
     * @return the name of this band member
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * setter for member name
     * @param memberName the name of this band member
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * getter for band_id the unique int which serves as the band's id number
     * @return the unique int which serves as the band's id number
     */
    public int getBandID() {
        return bandID;
    }

    /**
     * setter for band_id
     * @param bandID the unique int which serves as the band's id number
     */
    public void setBandID(int bandID) {
        this.bandID = bandID;
    }
}
