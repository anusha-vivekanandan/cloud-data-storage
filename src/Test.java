import java.util.Arrays;
import java.util.Collections;

/*import java.util.Random;


public class randomno {
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public static void main(String args[])
	{
		int[] arr=new int[38];
		int j;
		for(int i=0;i<38;i++)
		{
			arr[i]=randInt(1,38);
			j=arr[i];
			for(int k=0;k<38;k++)
			if(arr[k]!=j)
			System.out.println(arr[i]);
		}
		
		
		
	}
}

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int size = 20;

        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.size() > 0) {
            int index = rand.nextInt(list.size());
            System.out.println("Selected: "+list.remove(index));
            
        }
    }
}
*/
public class Test{
	public static void main(String[] args) {
	    Integer[] arr = new Integer[25];
	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = i;
	    }
	    Collections.shuffle(Arrays.asList(arr));
	    System.out.println(Arrays.toString(arr));

	}
}