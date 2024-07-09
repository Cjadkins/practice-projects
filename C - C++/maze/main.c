//Maze program
//Cort Adkins

#include <stdio.h>
#include <stdlib.h>

struct queue
{
    struct node* front;
    struct node* back;
};

struct node
{
    int x;
    int y;
    struct node* next;
};

void init(struct queue* qPtr);

int main(){

    int numCases, i, j, k, h;
    int rows, cols, xStart, yStart;

    //get number of cases
    scanf("%d", &numCases);

    for(i=0; i<numCases; i++){

        // Allocate space for queue and initialize
        struct queue* search = (struct queue*)malloc(sizeof(struct queue));
        init(search);

        //scan in rows and columns of maze
        scanf("%d %d", &rows, &cols);

        //dynamically allocate maze array
        int **maze = (int**)malloc(rows * sizeof(int*));
        for (h=0; h<rows; h++)
            maze[h] = (int*)malloc(cols * sizeof(int));

        //dynamically allocate char array to store maze chars
        char **charMaze = (char**) calloc(rows, sizeof(char*));
        for (h=0; h<rows; h++ ){
            charMaze[h] = (char*) calloc(cols, sizeof(char));
        }

        for(j=0; j<rows; j++){
            scanf("%s", charMaze[j]);
        }

        //assign int value to each char used in maze
        for(j=0; j<rows; j++){
            for(k=0; k<cols; k++){
                if(charMaze[j][k]=='S'){
                    maze[j][k]=0;

                    //find starting position
                    xStart=j;
                    yStart=k;

                }
                else if(charMaze[j][k]=='X'){
                    maze[j][k]= -2;
                }
                else if(charMaze[j][k]=='~'){
                    maze[j][k]=-3;
                }
                else{
                    maze[j][k]=-1;
                }
            }
        }

        //free arrays
        free(search);
        for (i = 0; i<rows; i++){
            free(maze[i]);
            free(charMaze[i]);
        }
        free(maze);
        free(charMaze);
    }
    return 0;
}

//initialize queue to null values
void init(struct queue* qPtr) {
     qPtr->front = NULL;
     qPtr->back = NULL;
}


