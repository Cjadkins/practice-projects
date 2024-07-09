//Cort Adkins
//Boggle program

#include <stdio.h>
#include <stdlib.h>

#define numNodes 26
#define N 4

typedef struct trie {
 int isWord;
 struct trie* nextLetter[numNodes];
} trie;

int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
int dy[] = {1, 1, 0, -1, -1, -1, 0, 1};
char board[N][N];
int visited[N][N];

void addWord (const char* word, const int length, struct trie* root);
int checkBoard(char* word, int index, int r, int c, int length);
void freeTrie(trie* current);

int main(){

    //set useful variables
    trie* nextLetter = NULL;
    int i, n, j, k, numGames;
    char word[16];
    int length;

    //read in dictionary file
    FILE* file = fopen("dictionary.txt", "r");
    fscanf(file, "%d", n);
    for(i = 0; i < n; i++){
        fscanf(file, "%s", word);
        length = strlen(word);
        if(length > 3 && length < 16)
            addWord(word, length, nextLetter);
    }

    //get number of games to be played and scan in board
    scanf("%d", numGames);
    for(i = 0; i < numGames; i++){
        for(j=0; j<N; j++){
            for(k=0; k<N; k++){
                scanf("%s", board[j][k]);
            }
        }
    }

    freeTrie(nextLetter);
}

//check the board for valid word
int checkBoard(char* word, int index, int r, int c, int length){
    if (index == length - 1){
        return 1;
    }
    int check = 0;
    int y;

    for (y = 0; y < 8; ++y){
        int nextX = r + dx[y];
        int nextY = c + dy[y];

        if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY] && word[index+1] == board[nextX][nextY]){
            ++index;
            visited[nextX][nextY] = 1;
            check = checkBoard(word, index, nextX, nextY, length);
            if (check)
                break;
            --index;
            visited[nextX][nextY] = 0;
        }
    }
    return check;
}

//function to add words from dictionary to trie
void addWord (const char* word, const int length, struct trie* root) {
   int i, index;
   trie* currentNode = root;

   for (i = 0; i < length; ++i){
       index = tolower(word[i]) - 'a';

       if (index >= 0 && index <= numNodes - 1){
            if (currentNode->nextLetter[index] == NULL){
                currentNode->nextLetter[index] = calloc(1, sizeof(trie));
            }
            currentNode = currentNode->nextLetter[index];
       }
   }
   currentNode->isWord = 1;
}

//function to free trie
void freeTrie(trie* current){
    int i;
    for (i = 0; i < numNodes; i++)
    {
        if(current->nextLetter[i] != NULL){
            freeTrie(current->nextLetter[i]);
        }
    }
    free(current);
}
