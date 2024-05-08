package org.example.eurovision.game;

public class Question {
    private int id;
    private String song;
    private String artist;
    private String correctCountry;
    private String[] countryOptions;
    private String[] songOptions;
    private String[] artistOptions;

    public Question(int id, String song, String artist, String correctCountry, String[] countryOptions, String[] songOptions, String[] artistOptions) {
        this.id = id;
        this.song = song;
        this.artist = artist;
        this.correctCountry = correctCountry;
        this.countryOptions = countryOptions;
        this.songOptions = songOptions;
        this.artistOptions = artistOptions;
    }
    public String[] getOptions(QuestionType type) {
        return switch (type) {
            case SONG_ARTIST -> artistOptions;
            case SONG_COUNTRY -> countryOptions;
            case ARTIST_SONG -> songOptions;
            case ARTIST_COUNTRY -> countryOptions;
            case COUNTRY_ARTIST -> artistOptions;
            default -> throw new IllegalArgumentException("Unsupported question type");
        };
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCorrectCountry() {
        return correctCountry;
    }

    public void setCorrectCountry(String correctCountry) {
        this.correctCountry = correctCountry;
    }

    public String[] getCountryOptions() {
        return countryOptions;
    }

    public void setCountryOptions(String[] countryOptions) {
        this.countryOptions = countryOptions;
    }

    public String[] getSongOptions() {
        return songOptions;
    }

    public void setSongOptions(String[] songOptions) {
        this.songOptions = songOptions;
    }

    public String[] getArtistOptions() {
        return artistOptions;
    }

    public void setArtistOptions(String[] artistOptions) {
        this.artistOptions = artistOptions;
    }

    public String getCorrectAnswer(QuestionType type) {
        switch (type) {
            case SONG_ARTIST:
                return artist;
            case SONG_COUNTRY:
                return correctCountry;
            case ARTIST_SONG:
                return song;
            case ARTIST_COUNTRY:
                return correctCountry;
            case COUNTRY_ARTIST:
                return artist;
            default:
                return null;
        }

    }
}
