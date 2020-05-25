#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef void* ItemList; 
typedef void* KeyItem;

struct node
{
   void *i; 	      // pointer to the item (info)
   struct node *next; // pointer to the next Node
};
typedef struct node* Node;

struct list
{
   Node p; 	// pointer to the first list item
   Node u; 	// pointer to the last list item
   int n;   	// number of items in the list
};
typedef struct list* List;



typedef void* Item;
typedef void* Key;

List
listCreate ()
{
   List l = (List) malloc (sizeof (List));
   if (l == NULL)
      return NULL;

   l->p = l->u = NULL;
   l->n = 0;

   return l;
}

void
listDestroy (List l)
{
   Node c = NULL;   // current node
   Node x = NULL;   // next node of c

   if ( l == NULL) return;

   c = l->p;
   while (c != NULL)
   {
      x = c->next;
      free (c);
      c = x;
   }
   free (l);
}

ItemList
listGetI (List l, int p)
{
   int j = 0;
   Node c = l->p;   // current node

   if ( (l == NULL) || (p > l->n) )	return NULL;

   for (j = 1; j < p; j++, c = c->next)
	 ;

   return c->i;
}

int
listIns (ItemList i, int p, List l)
{
   int j = 0;
   Node c = NULL; // current node
   Node x = NULL; // new node

   if (l == NULL) return 910; 

   x = (Node) malloc (sizeof (Node)); 
   if (x == NULL) return 911;

   x->i = (ItemList*)malloc(sizeof(ItemList));
   memcpy(x->i , i, sizeof(i));

   if ( p == 1 )
   {  // insert x in the head of l
      x->next = l->p;
      l->p = x;
      l->u = (l->u == NULL) ? x : l->u;
   }
   else if (p > l->n)
   {  // insert x in the tail of l
      x->next = NULL;
      if ( (c = l->u) == NULL )
         l->p = l->u = x;
      else
         l->u = c->next = x;
   }
   else
   {  // insert x in the position p of l
      for (c = l->p, j = 1; j < p - 1; j++, c = c->next)
         ;
      x->next = c->next;
      c->next = x;
   }
   l->n++;

   return 0;
}

int
listNOI (List l)
{
   return l->n;
}


ItemList
listRmv (ItemList i, List l)
{
   Node c = NULL; // current node
   Node p = NULL; // previous node of c

   if ( (l == NULL) || (i == NULL) ) return NULL;

   for (c = l->p; (c != NULL) && (c->i != i); c = c->next)
      p = c;

   if (c == NULL)	return NULL;	// i was not found

   if (p == NULL)
      l->p = c->next;
   else
      p->next = c->next;

   l->u = (l->u == c) ? p : l->u;

   free (c);
   l->n--;

   return i;
}

ItemList
listSearch (KeyItem k, List l, int (*cmp) (const ItemList x, const ItemList y))
{
   Node c = NULL;   // current node

   if ( (k == NULL) || (l == NULL) ) return NULL;

   if (l->p == NULL) return NULL;   // list is empty

   for (c = l->p; (c != NULL); c = c->next){
      if ( cmp (k, c->i) == 0 )
         return c->i;
   }

   return NULL;
}

List
listSort (List l, int (*cmp) (const void *a, const void *b))
{	
   int j = 0;
   int n = listNOI (l);
   List ol = listCreate (); // ordered list
   void **x = (void *) malloc ( n * sizeof (void*) );
   if ( (ol == NULL) || (x == NULL) ) return NULL;

   // loading array x to send it to qsort
   for (j = 0; j < n; j++){
      x[j] = listGetI (l, j + 1);
   }

   qsort (x, n, sizeof(void *), cmp);

   for (j = 0; j < n; j++){
      listIns (x[j], j + 1, ol);
   }

   return ol;
}