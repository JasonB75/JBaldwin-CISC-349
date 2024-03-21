package edu.harrisburgu.jasonb75.dynamiclist;

public class HolidaySongs {
    private String album_img;
    private String playlist_img;
    private String album_name;
    private String artist_name;
    private Double danceability;
    private Integer duration_ms;

    public String getAlbum_img() {
        return album_img;
    }

    public void setAlbum_img(String album_img) {
        this.album_img = album_img;
    }
    public void setDuration_ms(Integer duration_ms) {
        this.duration_ms = duration_ms;
    }
    public void setPlaylist_img(String playlist_img) {
        this.playlist_img = playlist_img;
    }
    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
    
    public String getPlaylist_img() {
        return playlist_img;
    }



    public String getAlbum_name() {
        return album_name;
    }



    public String getArtist_name() {
        return artist_name;
    }



    public Double getDanceability() {
        return danceability;
    }

    public void setDanceability(Double danceability) {
        this.danceability = danceability;
    }

    public Integer getDuration_ms() {
        return duration_ms;
    }

}
