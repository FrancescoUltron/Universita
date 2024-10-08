//Funzione che prende in input un array, la dimensione dell'array e un numero
//Se il numero è all'interno allora ritorna la posizione in cui si trova
//Altrimenti ritorna la posizione in cui si dovrebbe trovare se fosse nell'array

int searchInsert(int* nums, int numsSize, int target) {

    int left = 0;
    int right = numsSize - 1;

    int mid = 0;

    while (left <= right) {
        mid = (left + right) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] < target) {
            left = mid + 1;
        }
       
        else {
            right = mid - 1;
        }
    }

    return left;
} 
    
