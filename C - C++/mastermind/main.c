// Cort Adkins
// Mastermind Program

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

//function prototypes
int valid(int** game, int* nextMove, int slots, int moves);
int makeMove(int** game, int* nextMove, int slots, int colors, int moves);
int** gameBoard(int rows, int cols);

int main (int argc, char *argv[]){
    //declare variable for use in program
    int numGames, slots, colors, movesPlayed;

    //Scan in number of cases
    scanf("%d", &numGames);

    int i, j;
    for (i=0; i<numGames; i++){

        //Scan in number of slots, colors, and moves made and create dynamic arrays to store game
        scanf("%d %d %d", &slots, &colors, &movesPlayed);
        int* nextMove = malloc(sizeof(int)*slots);
        int** game = gameBoard(movesPlayed, slots);

        //Print outcome
        printf("\n%d\n", makeMove(game, nextMove, slots, colors, movesPlayed));

        //free arrays
        free(nextMove);
        for (j=0; j<movesPlayed; j++){
            free(game[j]);
        }
        free(game);
    }
    return 0;
}

//Function to scan in game to a 2d array
int** gameBoard(int rows, int cols){
    int i, j;
    int** array = malloc(sizeof(int*)*rows);
    for (i=0; i<rows; i++){
        array[i] = malloc(sizeof(int)*cols);
        for (j=0; j<cols; j++)
            scanf("%d", &array[i][j]);
    }
    return array;
}

//checks for a valid move
int valid(int** game, int* nextMove, int slots, int moves){
    int* used = (int*)calloc(slots, sizeof(int));
    int i, j, k;
    int black = 0, white = 0;
    for (i=0; i<moves; i++){
        for (j=0; j<slots; j++){
            if (game[i][j] == nextMove[j]){
                black++;
                used[j] = 1;
            }
        }
        if (black == game[i][++slots]){
            return 1;
        }
        for (j=0; j<slots; j++){
            for (k=0; k<slots; k++){
                if (game[i][j] == nextMove[k]){
                    white++;
                    break;
                }
            }
        }
        if (white == game[i][++slots]){
            return 1;
        }
    }
    return 0;
}

int makeMove(int** game, int* nextMove, int slots, int colors, int moves){
    int i;
    int sum = 0, count = 0;
    if(count == slots){
        return valid(game, nextMove, slots, moves);
    }
    else{
        for(i=0; i<colors; i++){
            nextMove[count] = i;
            sum+= makeMove(game, nextMove, slots, colors, moves);
            count++;
        }
    }
    return sum;
}
