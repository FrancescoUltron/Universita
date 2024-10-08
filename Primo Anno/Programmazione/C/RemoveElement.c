//Rimuove tutti gli elementi nella lista nums uguali a val
//Complessità di O(n), per trovare tutti gli elementi da eliminare dobbiamo 
//iterare la nostra lista almeno una volta completa


int removeElement(int* nums, int numsSize, int val) 
{

    int j = 0;
    
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] != val) {
            nums[j] = nums[i];
            j++;
        }
    }
    return j;
}