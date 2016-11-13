package com.ground0.model;

/**
 * Created by zer0 on 11/11/16.
 */

public class Coordinate {
  String id;
  Long epoch;
  Double lon;
  Double lat;

  public Coordinate(String id, Long epoch, Double lat, Double lon) {
    this.id = id;
    this.epoch = epoch;
    this.lon = lon;
    this.lat = lat;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getEpoch() {
    return epoch;
  }

  public void setEpoch(Long epoch) {
    this.epoch = epoch;
  }

  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }
}
