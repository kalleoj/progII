public class Foreachtheirown {

    public static void main(String [] args) {

        double arr [] [] = { {18,42,3,}, {34,55,6}, {7,88,19} };
        System.out.println(arr[0][1]);

        for (int i = 0; i < arr.length; i++) {
            int column = 0;
            for (int j = 1; j < arr[i].length; j++) {
                column = (arr[i][column] < arr[i][j]) ? column : j;

            }
            System.out.println("Smallest element for row " + i + " = " + column +" th column");
        }
    }
}
