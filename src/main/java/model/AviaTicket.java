package model;

public class AviaTicket {
    private int id;
    private String pointName;
    private int num;
    private String fio;
    private String date;

    public AviaTicket(int id, String pointName, int num, String fio, String date) {
        this.id = id;
        this.pointName = pointName;
        this.num = num;
        this.fio = fio;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "," + pointName + "," + num + "," + fio +"," + date;
    }
}
