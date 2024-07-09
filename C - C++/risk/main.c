//risk game
//Cort Adkins

#include <stdio.h>
#include <stdlib.h>

int compare(int[], int[], int);
void quickSort( int[], int, int);
int partition(int[], int, int);

int main(){
    int i, j, k, numCase, numArmies;
    int *attackers;
    int *defenders;
    //get number of cases
    scanf("%d", &numCase);

    for(i = 0; i < numCase; i++){
        scanf("%d", &numArmies);

        //allocate space for arrays
        attackers = malloc(sizeof(int) * numArmies);
        defenders = malloc(sizeof(int) * numArmies);

        //scan in attackers to array
        for(j = 0; j < numArmies; j++){
            scanf("%d", &attackers[j]);
        }

        //scan in defenders to array
        for(k = 0; k < numArmies; k++){
            scanf("%d", &defenders[k]);
        }

        //sort both arrays using quicksort function
        quickSort(attackers, 0, numArmies);
        quickSort(defenders, 0, numArmies);

        //print results using std out
        printf("%d\n", compare(attackers, defenders, numArmies));
    }

    //free both arrays
    free(attackers);
    free(defenders);
}

//compare two arrays to find number of battles defense can win
int compare(int attack[], int defend[], int size){
    int i, count = 0;
    int x = size - 1;
    int y = size - 1;

    for(i=0; i<size; i++){
        if(defend[x] >= attack[y]){
            x--;
            y--;
            count++;
        } else {
            y--;
        }
    }
    return count;
}

//function to sort array using partition
void quickSort( int array[], int left, int right){
    int x;

    if( left < right ){
        x = partition(array, left, right);
        quickSort(array, left, x-1);
        quickSort(array, x+1, right);
    }

}

//function to find partition of array for use in quicksort
int partition(int array[], int left, int right){
    int pivot, i, k, t;
    pivot = array[left];
    i = left;
    k = right + 1;

    for(;;){
        do ++i; while(array[i] <= pivot && i <= right);
        do --k; while( array[k] > pivot );
        if(i >= k){
            break;
        }
        t = array[i];
        array[i] = array[k];
        array[k] = t;
    }
    t = array[left];
    array[left] = array[k];
    array[k] = t;
    return k;
}
