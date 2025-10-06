package snake;

public class Test {
    public static void main(String[] args) {
        int[] sample1 = new int[]{
            0b11111,
            0b10000,
            0b11111,
            0b10001,
            0b11111
        };
        // number of rows: 5
        // number of columns: 5
        int[] sample2 = new int[]{
            0b1111,
            0b0010,
            0b0010,
            0b0100,
            0b0100
        };
        // number of rows: 5
        // number of columns: 4
        int[] sample3 = new int[]{
            0b11111,
            0b10001,
            0b10001,
            0b11111,
            0b10001,
            0b10001,
            0b11111
        };
        // number of rows: 7
        // number of columns: 5
        int[] sample4 = new int[]{
            0b1111,
            0b1001,
            0b1111,
            0b0001,
            0b1111
        };
        // number of rows: 5
        // number of columns: 4


        int[] sampledsa = new int[]{
            0b0110,
            0b1001,
            0b0110,
            0b0001,
            0b0110
        };
        // number of rows: 5
        // number of columns: 4
        System.out.println("number of rows: " + getMaxNumRow(sample1));
        System.out.println("number of columns: " + getMaxNumCol(sample1));


        
        System.out.println("number of rows: " + getMaxNumRow(sample2));
        System.out.println("number of columns: " + getMaxNumCol(sample2));


        
        System.out.println("number of rows: " + getMaxNumRow(sample3));
        System.out.println("number of columns: " + getMaxNumCol(sample3));



        
        System.out.println("number of rows: " + getMaxNumRow(sample4));
        System.out.println("number of columns: " + getMaxNumCol(sample4));


        
        System.out.println("number of rows: " + getMaxNumRow(sampledsa));
        System.out.println("number of columns: " + getMaxNumCol(sampledsa));





    }




    
    static int getMaxNumCol(int[] sample) {
        int max_num_cols = -1;
        for (int index = 0; index < sample.length; index++) {
            int answer = 0;
            int exponent_of_two = 1;
            while(true) {
                int digitBound = 1;
                for(int i =0; i < exponent_of_two; i++) {
                    digitBound *= 2;
                } 
                digitBound -= 1;

                if (sample[index] <= digitBound) {
                    answer = exponent_of_two;
                    break;
                }
                exponent_of_two++;
            }
            if (max_num_cols < answer) max_num_cols = answer;
        }
        return max_num_cols;
    }

    static int getMaxNumRow(int[] sample) {
        return sample.length;
    }
}



/*
xception in thread "main" java.lang.ArithmeticException: / by zero
        at snake.Test.testArea(Test.java:52)
        at snake.Test.main(Test.java:37)
        at jvlwjgl.App.main(App.java:27)

 */