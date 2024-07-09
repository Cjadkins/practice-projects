//AVL tree heist
//Cort Adkins

#include <stdio.h>
#include <stdlib.h>
#define bool int

struct tree_node
{
    int data;
    struct tree_node *left;
    struct tree_node *right;
};

int max(int a, int b);
struct tree_node* newNode(int val);
struct tree_node* insert(struct tree_node *root, struct tree_node *element);
int height(struct tree_node* node);
bool balanced(struct tree_node *root);

int main()
{
    //initialize some useful variables
    int i, j, value, numCase, numInserts, count = 0;

    //get number of cases
    scanf("%d", &numCase);

    //read in number of cases and start inserting values
    for(i=0; i<numCase; i++)
    {
        struct tree_node *root = NULL, *temp_node;

        scanf("%d", &numInserts);

        for(j=0; j<numInserts; j++)
        {
            scanf("%d", &value);
            temp_node = newNode(value);
            root = insert(root, temp_node);
        }
        if(balanced(root))
            printf("Tree #%d: KEEP\n", ++count);
        else
            printf("Tree #%d: REMOVE\n", ++count);
    }
    return 0;
}

//function to create a new node in binary tree
struct tree_node* newNode(int val)
{
    struct tree_node* temp;
    temp = (struct tree_node*)malloc(sizeof(struct tree_node));
    temp->data = val;
    temp->left = NULL;
    temp->right = NULL;

    return temp;
}

//function to insert data into binary tree
struct tree_node* insert(struct tree_node *root, struct tree_node *value)
{
    if (root == NULL)
        return value;
    else{
        if (value->data > root->data){
            if (root->right != NULL)
                root->right = insert(root->right, value);
            else
                root->right = value;
        }
        else{
            if (root->left != NULL)
                root->left = insert(root->left, value);
            else
                root->left = value;
        }
        return root;
    }
}

//find the max between two values
int max(int a, int b)
{
    return (a >= b)? a: b;
}

//find the height of a binary tree
int height(struct tree_node* node)
{
    if(node == NULL)
        return 0;
    return 1 + max(height(node->left), height(node->right));
}

//function to check if a binary tree is balanced
bool balanced(struct tree_node *root)
{
    int lefth;
    int righth;

    if(root == NULL)
        return 1;

    lefth = height(root->left);
    righth = height(root->right);

    if( abs(lefth-righth) <= 1 && balanced(root->left) && balanced(root->right))
        return 1;

    return 0;
}
