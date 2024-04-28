import java.util.*;
public class Main {
    public static void Booking(Passenger p)
    {
        Features f = new Features();
        if(Features.availWaitList==0)
        {
            System.out.println("No Tickets Available");
        }
        if(     (p.berthType.equalsIgnoreCase("L")&&Features.availLowerBirth>0) ||
                (p.berthType.equalsIgnoreCase("M")&&Features.availMiddleBirth>0)||
                (p.berthType.equalsIgnoreCase("U")&& Features.availUpperBirth>0))
        {
                System.out.println("Booking for Berth is Available");
                if(p.berthType.equalsIgnoreCase("L"))
                {
                    System.out.println("Lower birth is available");
                    f.bookTicket(p,(Features.lowerPositions.get(0)),"L");

                    Features.lowerPositions.remove(0);
                    Features.availLowerBirth--;
                } else if (p.berthType.equalsIgnoreCase("M"))
                {
                    System.out.println("Middle Birth is available");
                    f.bookTicket(p,Features.middlePositions.get(0),"M");

                    Features.middlePositions.remove(0);
                    Features.availMiddleBirth--;
                } else if (p.berthType.equalsIgnoreCase("U"))
                {
                    System.out.println("Upper birth is available");
                    f.bookTicket(p,Features.upperPositions.get(0),"U");

                    Features.upperPositions.remove(0);
                    Features.availUpperBirth--;
                }
        }
        else if (Features.availLowerBirth>0)
        {
            f.bookTicket(p,Features.lowerPositions.get(0),"L");

            Features.lowerPositions.remove(0);
            Features.availLowerBirth--;

        }
        else if (Features.availMiddleBirth>0)
        {
            f.bookTicket(p,(Features.middlePositions.get(0)),"M");

            Features.middlePositions.remove(0);
            Features.availMiddleBirth--;

        }
        else if (Features.availUpperBirth>0)
        {
            f.bookTicket(p,Features.upperPositions.get(0),"U");

            Features.upperPositions.remove(0);
            Features.availUpperBirth--;

        } else if (Features.availRAC>0)
        {
            f.bookRAC(p,Features.racPosition.get(0),"RAC");
            
        } else if (Features.availWaitList>0)
        {
            f.bookWaitingList(p,Features.waitingPosition.get(0),"WL");
        }
    }
    public static void cancel_Ticket(int id)
    {
        Features f = new Features();
        if(!f.passengers.containsKey(id))
        {
            System.out.println("Invalid id");
        }
        else
        {
            f.Cancellation(id);
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag)
        {
            System.out.println("1.Booking \n 2.Cancelling \n 3.Available Tickets \n 4.Booked Tickets \n 5.Exit \n");
            System.out.println("Enter yours choice:");
            int choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    //Booking Part
                    System.out.println("Enter your Name:");
                    String name = sc.next();
                    System.out.println("Enter Your Age:");
                    int age = sc.nextInt();
                    System.out.println("Enter Your Preference:");
                    String berthType = sc.next();
                    //Create a passenger class object
                    Passenger p = new Passenger(name, age, berthType);
                    Booking(p);
                    break;
                case 2:
                    //Cancellation Part
                    System.out.println("Enter the id to cancel");
                    int id = sc.nextInt();
                    cancel_Ticket(id);
                    break;
                case 3:
                    //Printing the availability
                    Features f = new Features();
                    f.printAvailabe();
                    break;
                case 4:
                    //Print the booked tickets
                    Features fs =new Features();
                    fs.printPassengers();
                    break;
                case 5:
                    //Exit
                     flag=false;
                     break;
                default:
                    break;
            }
        }
    }
}
