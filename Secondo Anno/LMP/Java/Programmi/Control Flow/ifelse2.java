public class ifelse2 {

    static int test(int testval, int target, int begin, int end){
        if(testval > target && (target <= end && target >= begin)){
            return 1;
        } else if (testval < target && (target <= end && target >= begin)){
            return -1;
        } else if (testval == target && (target <= end && target >= begin)){
            return 0;
        } 

        return -2; //Ritorna -2 se testval non sta nel range
    }

    public static void main(String[] args) {
        System.out.println(test(10, 5,1,2));
        System.out.println(test(5, 10,1,100));
        System.out.println(test(5, 5,1,100));
    }

    /*  
    Modify the two test( ) methods in the previous two programs so that
    they take two extra arguments, begin and end, and so that testval is tested to see if it is
    within the range between (and including) begin and end.  
    */



}


