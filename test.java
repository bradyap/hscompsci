public class test {
    public static void main(String[] args) {
        
    }

    /*public static void maxScorers(Student[] students) {
        int temp = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getScore() > temp) {
                temp = students[i].getScore()
            }
        }
        System.out.println("The max score of " + temp + " was achieved by: ");
        for (int i = 0; i < students.length; i++) {
            if (students[i].getScore() == temp) {
                System.out.print(students[i].getName() + " ");
            }
        }
    }*/

    /*public static int findAverageAge (int[] nums) {
        int temp = 0;
        for (int x : nums) {
            temp += x;
        }
        return (temp / nums.length);
    }*/

    /*public static String capitalizeMe(String input) {
        int index = 0;
        StringBuilder temp = new StringBuilder();
        temp.append(input);
        while (index != -1) {
            index = temp.indexOf("me", index);
            if (index != -1) {
                temp.replace(index, index + 2, "ME");
            }
        }
        String output = temp.toString();
        return output;
    }*/

    /*public static String getALetter(String input) {
        int ran = (int)(Math.random()*input.length());
        String output = input.substring(ran, ran + 1);
        return output;
    }*/
}
