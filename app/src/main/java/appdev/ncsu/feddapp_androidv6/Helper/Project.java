package appdev.ncsu.feddapp_androidv6.Helper;

/**
 * Created by Khanti Choksi on 11/18/2017.
 */

public class Project {
    private String name;
    private int thumbnailUrl;

    public Project() {
    }

    public Project(String name, int thumbnailUrl) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setNumOfSongs(int thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

}
