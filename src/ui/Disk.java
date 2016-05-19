package ui;

/**
 * Created by anatol on 18.03.16.
 */
public class Disk {
    private String name;
    private String type;
    private Long totalSpace;
    private Long freeSpace;
    private Long usableSpace;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTotalSpace() {
        return this.totalSpace / 1024 / 1024;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long getFreeSpace() {
        return freeSpace / 1024 / 1024;
    }

    public void setFreeSpace(Long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public Long getUsableSpace() {
        return usableSpace / 1024 / 1024;
    }

    public void setUsableSpace(Long usableSpace) {
        this.usableSpace = usableSpace;
    }
}
