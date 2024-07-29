import java.util.Scanner;
class Program {

    public static String[] students = new String[10];
    String[] weekDays = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
    private static boolean spaceCheck(String string) {
        char[] temp = string.toCharArray();
        for (int i = 0; i < string.length();i++)
            if (temp[i] == ' ')
                return true;
        return false;
    }

    private static int registerStudent(Scanner in)
    {
        for (int i = 0; i < 10; i++) {
            String input =  in.nextLine();
            if (input.length() > 10 || spaceCheck(input))
                return -1;
                // throw new Exception("Student Name is over 10 chars or contains space");
            if (input.equals("."))
                break;
            students[i] = input;
        }
        return 0;
    }
    
    private static int registerSchedule(Scanner in)
    {
        for (int i = 0; i < 10; i++) {
            String input =  in.nextLine();
            if (input.length() > 10 || spaceCheck(input))
                return -1;
                // throw new Exception("Student Name is over 10 chars or contains space");
            if (input.equals("."))
                break;
            students[i] = input;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
       
        if (registerStudent(scanner) == -1)
            throw new Exception("student's Name is over 10 chars or contains space");

        if (registerSchedule(scanner) == -1)
            throw new Exception("Schedule Data is wrongly entred");
        for (String student : students) {
            System.out.println(student);
        }
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}