public class Passenger {
    static int id =1;
    String name;
    int age;
    String berthType; //L or M or U
    int passenger_id;
    String alloted_type;
    int Seat_Num;
    public Passenger(String name,int age,String berthType)
    {
        this.id=1;
        this.name=name;
        this.age=age;
        this.berthType=berthType;
        this.passenger_id=id++;
        alloted_type="";
        Seat_Num=-1;
    }
}
