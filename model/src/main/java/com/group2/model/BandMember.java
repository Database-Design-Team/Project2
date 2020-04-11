package com.group2.model;

/**
 * @author Timothy
 */
class BandMember {
    String member_name;
    int band_id;

    /**
     * empty constructor
     */
    public BandMember() {
    }

    /**
     * constructor with all parameters
     * @param member_name the name of this particular band member
     * @param band_id the band's unique integer id
     */
    public BandMember(String member_name, int band_id) {
        this.member_name = member_name;
        this.band_id = band_id;
    }

    /**
     * getter for member name
     * @return the name of this band member
     */
    public String getMember_name() {
        return member_name;
    }

    /**
     * setter for member name
     * @param member_name the name of this band member
     */
    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    /**
     * getter for band_id the unique int which serves as the band's id number
     * @return the unique int which serves as the band's id number
     */
    public int getBand_id() {
        return band_id;
    }

    /**
     * setter for band_id
     * @param band_id the unique int which serves as the band's id number
     */
    public void setBand_id(int band_id) {
        this.band_id = band_id;
    }
}
