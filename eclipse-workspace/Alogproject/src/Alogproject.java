


import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.util.*;

public class Alogproject {

   public static class mergsort {

    int swapcount=0;
    int compcount=0;
       
   void merge(int arr[], int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        
        
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
            	compcount++;
                arr[k] = L[i];
                i++;
            }
            else {
            	compcount++;
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    int mergcount=0;
    
    void sort(int arr[], int l, int r){
        if (l < r) {
            
        	swapcount++;
            
            int m =l+ (r-l)/2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
  
    
    }
    
    
    void printArray(int[] arr, int size)
{
    for(int i = 0; i < size; i++)
        System.out.print(arr[i] + " ");
         
    System.out.println();
}
    
  
    
    void reverse(int ar[])
    {
        int i, k, n = ar.length;
        int t;
        for (i = 0; i < n / 2; i++)
        {
            t = ar[i];
            ar[i] = ar[n - i - 1];
            ar[n - i - 1] = t;
        }
}

    
    
    public static class quicksort 
    {
    	int swapcount=0;
    	int compcount=0;
   void swap(int[] arr, int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    swapcount++;
}
 

  
 int partition(int[] arr, int low, int high)
{
     
    
    int pivot = arr[high];
     
    
    int i = (low - 1);
 
    for(int j = low; j <= high - 1; j++)
    {
         
        
        if (arr[j] < pivot)
        {
             compcount++;
            i++;
            swap(arr, i, j);
        }
    }
    swap(arr, i + 1, high);
    return (i + 1);
}
 
int quicksortcount = 0;

 void quickSort(int[] arr, int low, int high)
{
    if (low < high)
    {
        quicksortcount++;
        
        int pi = partition(arr, low, high);
 
        
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}
 

 void printArray(int[] arr, int size)
{
    for(int i = 0; i < size; i++)
        System.out.print(arr[i] + " ");
         
    System.out.println();
}
 
 
 void reverse(int ar[])
    {
        int i, k, n = ar.length;
        int t;
        for (i = 0; i < n / 2; i++)
        {
            t = ar[i];
            ar[i] = ar[n - i - 1];
            ar[n - i - 1] = t;
        }
    }
 
 
    }

   
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
      
        int chos1, chos2;
        int indx, valu;
        int ar[] = new int[10];
        
    
         
        
      
        while (true)
        {
            System.out.println("\t\t\t\t\t\tAlogorithms project");
            System.out.println("");
            System.out.println(" =========================================== ");
            System.out.println("|| MENU SELECTION DEMO                     ||");
            System.out.println(" =========================================== ");
            System.out.println("||    1- Enter the array:                  ||");
            System.out.println("||    2- decending Merge sort:             ||");
            System.out.println("||    3- decending Quick Sort:             ||");
            System.out.println("||    4- ascending Quick Sort:             ||");
            System.out.println("||    5- ascending Merge Sort:             ||");
            System.out.println("||    6- All of them                       ||");
            System.out.println("||    7- Exit                              ||");
            System.out.println(" =========================================== ");
           
            
            
            
            chos1 = in.nextInt();
            if (chos1 == 1)
            {
                for (int i = 0; i < 10; i++) {
                    System.out.print("Enter element no." + (i+1) + ": ");
                    ar[i] = in.nextInt();
                }
            }
            
            
            
            
            else if(chos1 == 2)
            {
                int n = ar.length;
                System.out.println("Given Array");
                mergsort mg = new mergsort();
               mg.printArray(ar,n); 
              mg.sort(ar, 0, ar.length - 1);
              mg.reverse(ar);
             System.out.println("\nSorted array using descending merge sort :");
             mg.printArray(ar,n);
             System.out.println(mg.mergcount);
            
            }
            
           
            
            else if (chos1==3)
            {
            int n = ar.length;
                
              quicksort q = new quicksort ();
      
            
      
             q.quickSort(ar, 0, n - 1);
           
             q.reverse(ar);
             System.out.println("Sorted array using descending quick sort: ");
             q.printArray(ar, n);
             System.out.println(q.quicksortcount);
            
            }
            
            
            
            
            
            
            
            else if(chos1 == 4)
            {

                
                int n = ar.length;
                
              quicksort q = new quicksort ();
      
      
      
             q.quickSort(ar, 0, n - 1);
             
             System.out.println("Sorted array using ascending quick sort: ");
             q.printArray(ar, n);
             System.out.println("Number of swaps and comparisons: "+q.swapcount+" "+q.compcount);
           
             
             
                /*quicksort   keda hay3mel quick sort w y3mel sort lel array */ 
                
            }
            else if(chos1 == 5)
            {
                
             int n = ar.length;
             System.out.println("Given Array");
              
             mergsort mg = new mergsort();
                
             mg.printArray(ar,n);
 
              
             mg.sort(ar, 0, ar.length - 1);
 
             System.out.println("\nSorted array using ascending merge sort :");
             mg.printArray(ar,n);
             System.out.println(mg.mergcount);
                
                
                
                /*merge sort*   keda hay3mel mergesort w hay3mel print*/
                
            }  
                
            else if (chos1 == 6)
            {
                int ar2[]=new int[10];
                for(int x=0;x<10;x++) {
                	ar2[x]=ar[x];
                }
                
                mergsort mg = new mergsort();
                quicksort q = new quicksort ();
                int n = ar.length;
                mg.sort(ar, 0, n-1);
                q.quickSort(ar, 0, n - 1);
                System.out.println("Merge sort----> Swaps: "+mg.swapcount+"   Comparisons:  "+mg.compcount);
                System.out.println("Quick sort----> Swaps: "+q.swapcount+"   Comparisons:  "+q.compcount);
                
                
                
                
                
                
                
                
                
                
                
            /*both*/
            
            }
             
            else if (chos1 == 7 )
            {
            
            break;
            
            }
            
            else{
                System.out.println("Wrong input, try again : ");
                chos1 = in.nextInt();
            }
        }
    
    
    }
    
    
    
    
    
    }
    
}