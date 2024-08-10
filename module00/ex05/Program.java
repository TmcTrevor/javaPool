
import java.util.Scanner;

class Program {

    // public static String[] students = {"John", "Mike"}; ///
    public static String[] students = new String[10];
    ///
    public static String[] schoolDays = new String[10]; // WE  MO FR MO SU
    // public static String[] schoolDays ={"WE", "MO"}; // WE  MO FR MO SU
    // public static int[] timeShift = {4, 2}; // 4 5 6 4 2
    public static int[] timeShift = new int[10]; // 4 5 6 4 2
    public static String[][] attendanceArray = new String[10000][4];
    public static String[] weekDays = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};

    public static String[][] cleanAndResizeArray(String[][] array) {
        // Count non-null entries
        int validCount = 0;
        for (String[] entry : array) {
            if (entry != null) {
                validCount++;
            }
        }

        // Create a new array with the size of valid entries
        String[][] cleanedArray = new String[validCount][];
        int index = 0;

        // Copy non-null entries to the new array
        for (String[] entry : array) {
            if (entry != null) {
                cleanedArray[index] = entry;
                index++;
            }
        }

        return cleanedArray;
    }

    public static void displayFirstLine() {
        for (int i = 0; i < 11; i++) {
            System.out.print(" ");
        }

        for (int i = 0; i < 31; i++) {
            int dayOfWeekIndex = (i + 1) % 7;
            String currentDay = weekDays[dayOfWeekIndex];
            for (int j = 0; j < schoolDays.length; j++) {

                if (schoolDays[j] != null && schoolDays[j].equals(currentDay)) {
                    System.out.format("%1d:00%3s%3d|" , timeShift[j], schoolDays[j], i + 1);
                }

            }
        }
        System.out.println("");
    }

    public static int checkAttendance(String[][] attendance, String name, String day, String hour) {
        for (String[] attendance1 : attendance) {
            if (attendance1[0].equals(name) && attendance1[2].equals(day) && attendance1[1].equals(hour)) {

                if (attendance1[3].equals("HERE")) {
                    return 1;
                }
                return -1;
            }
        }
        return 0;
    }

    public static void displayStudentData(String[][] attendance) {

        for (String student : students) {
            // System.err.println(" here 5");
            if (student != null) {
                // System.out.print(student);
                System.out.format("%-10s|",student);
                for (int day = 0; day <= 30; day++) {

                    int dayOfWeekIndex = (day + 1) % 7;
                    String currentDay = weekDays[dayOfWeekIndex];
                    for (int j = 0; j < schoolDays.length; j++) {
                        if (schoolDays[j] != null && schoolDays[j].equals(currentDay)) {

                            int attendancy = checkAttendance(attendance, student, (day + 1) + "", timeShift[j] + "");
                            switch (attendancy) {
                                case -1 -> System.out.format("%10d|", attendancy);
                                case 1 -> System.out.format("%10d|", attendancy);
                                default -> System.out.format("%10s|", "");
                            }

                        }
                    }
                }
                System.out.println("");
            }

        }
    }

    public static void displaySchedule(String[][] attendance) {
        displayFirstLine();
        displayStudentData(attendance);

    }

    public static void sortSchedule() {
        int n = schoolDays.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareDays(schoolDays[j], schoolDays[j + 1]) > 0
                        || (compareDays(schoolDays[j], schoolDays[j + 1]) == 0 && timeShift[j] > timeShift[j + 1])) {
                    // Swap days
                    String tempDay = schoolDays[j];
                    schoolDays[j] = schoolDays[j + 1];
                    schoolDays[j + 1] = tempDay;

                    // Swap times
                    int tempTime = timeShift[j];
                    timeShift[j] = timeShift[j + 1];
                    timeShift[j + 1] = tempTime;
                }
            }
        }
    }

    private static int compareDays(String day1, String day2) {
        int index1 = getDayIndex(day1);
        int index2 = getDayIndex(day2);
        return Integer.compare(index1, index2);
    }

    private static int getDayIndex(String day) {
        for (int i = 0; i < weekDays.length; i++) {
            if (weekDays[i].equals(day)) {
                return i;
            }
        }
        return -1; // Should never happen if data is valid
    }

    public static int dayTimeDuplicationCheck(int time, String day) {

        for (int i = 0; i < 10; i++) {
            if (timeShift[i] == time && schoolDays[i].equals(day)) {
                return -1;
            }
        }
        return 0;
    }

    private static boolean spaceCheck(String string) {
        char[] temp = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (temp[i] == ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean studentExist(String name) {
        for (String student : students) {
            if (name.equals(student)) {
                return true;
            }
        }
        return false;
    }

    public static String[][] registerPresence(Scanner in) {
        int row = 0;
        in.nextLine();
        while (true) {
            System.out.print("-> ");
            String line = in.nextLine();
            if (line.equals(".")) {
                break;
            }

            String name = "";
            String time = "";
            String day = "";
            String status = "";
            char[] chars = line.toCharArray();
            int index = 0;
            while (index < chars.length && chars[index] != ' ') {
                name += chars[index];
                index++;
            }
            index++;
            while (index < chars.length && chars[index] != ' ') {
                time += chars[index];
                index++;
            }
            index++;
            while (index < chars.length && chars[index] != ' ') {
                day += chars[index];
                index++;
            }
            index++;
            while (index < chars.length) {
                status += chars[index];
                index++;
            }
            // System.err.println(" here 10");
            attendanceArray[row][0] = name;
            attendanceArray[row][1] = time;
            attendanceArray[row][2] = day;
            attendanceArray[row][3] = status;
            if (!studentExist(attendanceArray[row][0])) {
                return null;
            }
            // System.err.println(" here 11");
            if (!attendanceArray[row][3].equals("HERE") && !attendanceArray[row][3].equals("NOT_HERE")) {
                return null;
            }
            row++;
        }
        String[][] cleanedArray = new String[row][];
        // int index = 0;

        // Copy non-null entries to the new array
        for (int index = 0; index < row; index++) {
            if (attendanceArray[index] != null) {
                cleanedArray[index] = attendanceArray[index];
                // index++;
            }
        }

        return cleanedArray;
    }

    private static int registerStudent(Scanner in) {
        for (int i = 0; i < 10; i++) {
            System.out.print("-> ");
            String input = in.nextLine();
            if (input.length() > 10 || spaceCheck(input)) {
                return -1;
            }
            // throw new Exception("Student Name is over 10 chars or contains space");
            if (input.equals(".")) {
                break;
            }
            students[i] = input;
        }
        return 0;
    }

    private static int registerSchedule(Scanner in) {
        for (int i = 0; i < 10; i++) {
            System.out.print("-> ");
            String line = in.next();
            if (line.equals(".")) {
                break;
            }
            int time;
            // System.out.println("|"+line+"|");
            try {
                time = Integer.parseInt(line);
                // System.out.println("|" + time + "|");
            } catch (NumberFormatException e) {
                return -1; // Invalid time input
            }
            if (time < 1 || time > 6) {
                return -1;
            }

            String day = in.next();
            boolean validDay = false;
            for (String weekDay : weekDays) {
                if (day.equals(weekDay)) {
                    validDay = true;

                    break;
                }
            }
            // System.out.println("|" + validDay + "|");

            if (!validDay || dayTimeDuplicationCheck(time, day) == -1) {
                return -1;
            }
            timeShift[i] = time;
            schoolDays[i] = day;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //    String[][] attendance = {
        //     // {"John", "5", "1", "HERE"},
        //     // {"John", "4", "24", "NOT_HERE"},
        //     // {"John", "4", "31", "HERE"},
        //     // {"Mike", "5", "1", "NOT_HERE"},
        //     // {"Mike", "5", "29", "HERE"},
        //     // {"Mike", "4", "31", "NOT_HERE"}
        //     {"John", "4", "9", "HERE"},
        //     {"Mike", "4", "9", "HERE"},
        //     {"Mike", "2", "28", "NOT_HERE"}
        // };
        String[][] attendance;
        // attendance = null;
        try {

            if (registerStudent(scanner) == -1) {
                throw new Exception("student's Name is over 10 chars or contains space");
            }

            if (registerSchedule(scanner) == -1) {
                throw new Exception("Schedule Data is wrongly entred");

            }
            if ((attendance = registerPresence(scanner)) == null) {
                throw new Exception("attendance Data is entred wrong");
            }
            // System.err.println(Arrays.toString(students));
            sortSchedule();
            // System.err.println(" here 1");
            displaySchedule(attendance);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
            scanner.close();
        }
    }
}
