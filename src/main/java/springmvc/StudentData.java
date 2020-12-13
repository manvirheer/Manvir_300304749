package springmvc;

public class StudentData {

    private String sname;
    private String snumber;
    private double gpa;

    public StudentData(String sname, String snumber, double gpa) {
        this.sname = sname;
        this.snumber = snumber;
        this.gpa = gpa;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
