package OjTestJava;

/**
 * @author abs
 * @Date 2019/8/20 - 23:43
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1,2,3,5,7,8,9,12,25,62,75};
        int index = search(array,0,array.length-1,12);
        System.out.println(index);
    }

    public static int search(int[] array,int left,int right,int target){
        if(left > right){
            return -1;
        }
        int mid = (left+right)/2;
        if(target < array[mid]){
            return search(array,left,mid-1,target);
        }else if(target > array[mid]){
            if(left == right){
                return mid;
            }else if(array[mid+1] > target){
                return mid;
            }
            return search(array,mid+1,right,target);
        }else{
            return mid;
        }
    }
}
