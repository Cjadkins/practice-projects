//Cort Adkins
//Word Search


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 300

const int DX_SIZE = 8;
const int DX[] = { -1,-1,-1,0,0,1,1,1 };
const int DY[] = { -1,0,1,-1,1,-1,0,1 };

//function prototype
int searchDictionary(char *dictionary[], char match[], int size);

int main(int argc, char *argv[]){

	//define variables and arrays for use in program
	int i, j, k, z, numCases, numWords, numChars;
	char line[SIZE];
    char *dictionary;

	//read in dictionary file from command line
	FILE *file = fopen(argv[1], "r");
	fscanf(file, "%d", &numWords);
    fseek(file , 0 , SEEK_END);
    numChars = ftell(file);
    rewind(file);

    //dynamically allocate dictionary array and read in text file
    dictionary = malloc(sizeof(char) * numChars);
    fread(dictionary, numChars, 1 , file);

	//read in word grid input file from command line
	FILE *gridFile = fopen(argv[2], "r");
	fgets(line, sizeof line, gridFile);
	rewind(gridFile);
	fscanf(gridFile, "%d", &numCases);

	//process each grid possibility
	for (z=0; z<numCases; z++) {
		printf("Words Found Grid #%d:\n", z+1);
		char **grid = (char**) calloc(SIZE, sizeof(char*));
        for (i=0; i<SIZE; i++ ){
            grid[i] = (char*) calloc(SIZE, sizeof(char));
        }
		char line2[SIZE + 1];
		int x,y;
		fscanf(gridFile, "%d%d", &x, &y);

		//read in grid to array
		for (i=0; i<x; i++) {
			fgets(line2, sizeof line2, gridFile);
			for (j=0; j<y; j++)
				grid[i][j] = line2[j];
		}

		//search the grid for a match
		for (i=0; i<x; i++){
			for (j=0; j<y; j++){
				for (k=0; k<DX_SIZE; k++){
					int pos = 0, posX = i, posY = j;
					char *match = malloc(sizeof(char*) * SIZE);
					match[0] = grid[i][j];
					match[pos] = grid[i][j];
					for (;;){
						pos++;
						int X = posX + DX[k];
						int Y = posY + DY[k];

						if (X > -1 && Y > -1 && X < x && Y < y){
							match[pos] = grid[X][Y];
							posX = X;
							posY = Y;
							if (pos > 1){
								searchDictionary(dictionary, match, numChars);
							}
						}
						else {
							break;
						}
					}
					free(match);
				}
			}
		}
		for (i=0; i<SIZE; i++){
            free(grid[i]);
        }
        free(grid);
	}
    free(dictionary);
	getchar();
	return 0;

}

//function to search dictionary for possible match
int searchDictionary(char *dictionary[], char match[], int size){
    int pos, start = 0, check = 0;
    int end = size - 1;

    while(start <= end) {
        pos = (start + end) / 2;
        if((check = strcmp(dictionary[pos], match)) == 0)
            printf("%s\n", match);
        else if(check < 0)
            start = pos + 1;
        else
            end = pos - 1;
    }
	return 0;
}
