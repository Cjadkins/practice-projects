#include <stdio.h>
#include <stdlib.h>

//stores start and end times of challenge houses
struct interval
{
    int start;
    int end;
};

double getMaxTime(int* perm, struct interval* times, int length);
double getMax(struct interval* time, int length, int numGames);
int contains(const struct interval* space, double time);
void nextPerm(int* perm, int length);
int works(int* perm, struct interval* times, int length, double gap);
int getMaxInterval(struct interval* times, int length);


int main()
{
    int i, j, k, numHouses, numInput;

    scanf("%d", &numInput);

    for(i=0; i<numInput; i++)
    {
        scanf("%d", &numHouses);

        struct interval time[numHouses];

        for(j=0; j<numHouses; j++)
        {
            scanf("%d%d", &time[j].start, &time[j].end);
        }

        int length[numHouses+1];

        for(k=0; k<numHouses+1; k++)
        {
            scanf("%d", &length[k]);
        }

        int max = getMax(time, numHouses, numHouses);
        printf("%d", max);

    }

    return 0;
}

//binary search function
double getMaxTime(int* perm, struct interval* times, int length)
{
    double high = getMaxInterval(times, length);
    double low = 0, mid = (low+high)/2;

    while (high-low > 1e-9)
    {

        mid = (low+high)/2;

        if (works(perm,times,length,mid))
            low = mid;
        else
            high = mid;
    }

    return mid;
}

//Test each permutation
double getMax(struct interval* time, int length, int numHouses)
{

    //get factorial
    int k, ans = 1;
    for (k=2; k<=length; k++)
        ans*= k;

    int i, perm[numHouses];
    for (i=0; i<ans; i++)
        perm[i] = i;

    double max = 0;
    for (i=0; i<ans; i++)
    {
        double score = getMaxTime(perm, time, length);
        if (score > max)
            max = score;
        nextPerm(perm, length);
    }
    return max;
}

// get next permutation
void nextPerm(int* perm, int length)
{

    int i, j;

    i = length-1;
    while (i>0 && perm[i] < perm[i-1]) i--;
    i--;

    if (i == -1) return;
    int works(int* perm, struct interval* times, int length, double gap);

    j = length-1;
    while (j>i && perm[j]<perm[i]) j--;

    int temp = perm[i];
    perm[i] = perm[j];
    perm[j] = temp;

    int k,m;
    for (k=i+1,m=length-1; k<(length+i+1)/2; k++,m--)
    {
        temp = perm[k];
        perm[k] = perm[m];
        perm[m] = temp;
    }
}

int contains(const struct interval* space, double time) {
    return space->start-1e-9 <= time && time <= space->end+1e-9;
}

int works(int* perm, struct interval* times, int length, double gap)
{

    double curtime = times[perm[0]].start;
    int i;

    for (i=1; i<length; i++)
    {

        double nexttime = curtime+gap;

        if (nexttime < times[perm[i]].start)
            nexttime = times[perm[i]].start;

        if (!contains(&times[perm[i]],nexttime))
            return 0;
        else
            curtime = nexttime;
    }
    return 1;
}

//returns difference of min and max times
int getMaxInterval(struct interval* times, int length)
{

    int min = times[0].start;
    int max = times[0].end;
    int i;
    for (i=1; i<length; i++)
    {
        if (times[i].start < min)
            min = times[i].start;
        if (times[i].end > max)
            max = times[i].end;
    }
    return max-min;
}
