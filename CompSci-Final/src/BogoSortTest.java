


public class BogoSortTest {
    public static void main(String args[]) {
        new BogoSortTest();
    }

    public BogoSortTest() {
        int a[] = {4, 5, 3, 7, 8, 10, 1, 6};
        bogoSort(a);
    }

    public void bogoSort(int a[]) {
        while (!isSorted(a)) {
//TO DO: randomize all elements in the array
            for (int i = a.length - 1; i > 0; i--) {
                int j = (int) Math.random() * i;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }

        }
//TO DO: print out the array so the user can verify it is sorted
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+"");
        }
    }


public boolean isSorted(int a[]) {
//TO DO: returns true if the array is sorted, false otherwise
    int check = 0;
    for(int i = 0; i < a.length-1; i++){
    	
            if (a[i] > a[i + 1])
                check++;
        
    }
    if (check==0){
        return true;
    }
    else
        return false;
}

}