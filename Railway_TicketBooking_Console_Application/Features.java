import java.util.*;
public class Features {

    static int availLowerBirth=21;
    static int availMiddleBirth=21;
    static int availUpperBirth=21;
    static int availRAC=18;
    static int availWaitList=10;

    static Queue<Integer> waiting_list = new LinkedList<>();
    static Queue<Integer> rac_list = new LinkedList<>();
    static List<Integer>bookedTicketList = new ArrayList<>();

    static List<Integer> lowerPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
    static List<Integer> middlePositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
    static List<Integer> upperPositions = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21));
    static List<Integer> racPosition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18));
    static List<Integer> waitingPosition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));

    static Map<Integer,Passenger> passengers = new HashMap<>();

    public void bookTicket(Passenger p,int berth_no,String alloted_Berth)
    {
        //assigning seat_no and alloted_type
        p.Seat_Num=berth_no;
        p.alloted_type=alloted_Berth;
        //add Passengers to the map
        passengers.put(p.passenger_id,p);
        //adding passengers to the bookedTicketList
        bookedTicketList.add(p.passenger_id);
        System.out.println("--------------Booked Successfully-------------");
    }
    public void bookRAC(Passenger p, int rac_no, String rac_Berth)
    {
        p.Seat_Num=rac_no;
        p.alloted_type=rac_Berth;

        passengers.put(p.passenger_id,p);
        //adding passengers  to rac list
        rac_list.add(p.passenger_id);
        //reducing one rac ticket from rac list
        Features.availRAC--;
        //Removing the allocated rac position.
        Features.racPosition.remove(0);

        System.out.println("--------Added to RAC successfully--------");
    }
    public void bookWaitingList(Passenger p,int w_no,String waiting_berth)
    {
        p.Seat_Num=w_no;
        p.alloted_type=waiting_berth;

        passengers.put(p.passenger_id,p);

        waiting_list.add(p.passenger_id);

        Features.availWaitList--;
        Features.waitingPosition.remove(0);
        System.out.println("--------Added to waitingList Successfully--------");
    }
    public void Cancellation(int n)
    {
        Passenger p = passengers.get(n);
        //Firstly I remove from the map
        passengers.remove(n);
        //and also I have removed from the booked list
        bookedTicketList.remove(n-1);

        //Take the booked position which is free right now
        int bookedPosition = p.passenger_id;
        System.out.println("Cancelled successfully");

        //Now add the free positions to the corresponding berths

        if(p.berthType.equals("L"))
        {
            availLowerBirth++;
            lowerPositions.add(bookedPosition);

        } else if (p.berthType.equals("M"))
        {
            availMiddleBirth++;
            middlePositions.add(bookedPosition);

        } else if (p.berthType.equals("U"))
        {
            availUpperBirth++;
            upperPositions.add(bookedPosition);
        }

        if(rac_list.size()>0)
        {
            Passenger passFromRac = passengers.get(rac_list.poll());
            int positionRAC = passFromRac.Seat_Num;
            racPosition.add(positionRAC);
            rac_list.remove(passFromRac.passenger_id);
            availRAC++;

            if(waiting_list.size()>0)
            {

                Passenger passengerFromWaitingList = passengers.get(waiting_list.poll());
                int positionWL = passengerFromWaitingList.Seat_Num;
                waiting_list. add (positionWL) ;
                waiting_list. remove (passengerFromWaitingList. passenger_id) ;
                passengerFromWaitingList.Seat_Num = racPosition.get(0);
                passengerFromWaitingList.alloted_type ="RAC";
                racPosition. remove (0) ;
                rac_list. add (passengerFromWaitingList. passenger_id) ;
                availWaitList++;
                availRAC--;

            }
            Main.Booking(passFromRac);
        }

    }

    public void printAvailabe()
    {
        System.out.println("Availabel LowerBirth:"+availLowerBirth);
        System.out.println("Available MiddleBirth:"+availMiddleBirth);
        System.out.println("Available UpperBirth:"+availUpperBirth);
        System.out.println("Available RAC:"+availRAC);
        System.out.println("Available WaitingLists:"+availWaitList);
        System.out.println("These are the available tickets");
    }

    public void printPassengers()
    {
          if(passengers.size()==0)
          {
              System.out.println("No details");
          }
          for(Passenger p : passengers.values())
          {
              System.out.println("PassengerId:"+p.passenger_id);
              System.out.println("Passenger Name:"+p.name);
              System.out.println("Passenger age:"+p.age);
              System.out.println("Passenger slot:"+p.Seat_Num+p.alloted_type);
              System.out.println("------------------------------------------");
          }
    }
}
