//definição fantasia de Set
typedef void* Set; 

typedef unsigned long size_t;

// setCard
// -------
// Return the cardinality (number of elements) of s

int setCard (Set s);


// setCreate
// ---------
// Create a Set.
//
// Return a pointer to a Set or return NULL, in the case of error

Set setCreate ();


// setDiff
// -------
// Create a Set whose content is the difference between a and b.
//
// Return the resulting Set or return NULL, in the case of error.
//
// cmp is a user defined comparison function pointer. This function
// compares ea (element of set a) against eb (element of set b).
// cmp returns 0, if ea == eb, otherwise, returns a value i != 0.

Set setDiff (Set a, Set b, int (*cmp)(const void *ea, const void *eb));


// setErase
// --------
// Erase s.
//
// Any memory space allocated by the application must be de-allocated
// by the application BEFORE calling setErase().

void setErase (Set s);


// setGetE
// -------
// Return the p-th element of set a, such that, p > 0;
// or return NULL, whether
// 	s does not exist,
//	or p < 1,
//	or p > setCard (s).

void * setGetE (int p, Set s);


// setIns
// ------
// Insert element e whose size is t into s.
//
// Return 0, if e was inserted into s; otherwise, return
// 23 (s is full - there is no room for e);
// or 25 (e is already exists in s);
// or 27 (s does not exist).
//
// cmp is a user defined comparison function pointer. This function
// compares ea (element of set a) against eb (element of set b).
// cmp returns 0, if ea == eb, otherwise, returns a value i != 0.

int setIns (void *e, size_t t, Set s,  int (*cmp)(const void *ea, const void *eb));


// setInter
// --------
// Create a Set whose content is the intersection between a and b.
//
// Return the resulting Set or return NULL, in the case of error
//
// cmp is a user defined comparison function pointer. This function
// compares ea (element of set a) against eb (element of set b).
// cmp returns 0, if ea == eb, otherwise, returns a value i != 0.

Set setInter (Set a, Set b,  int (*cmp)(const void *ea, const void *eb));


// setRem
// ------
// Remove e from s.
//
// Return 0, if e was removed from s; otherwise, return
// 13 (s is empty);
// or 15 (e does not exist in s);
// or 17 (s does not exist).
//
// cmp is a user defined comparison function pointer. This function
// compares ea (element of set a) against eb (element of set b).
// cmp returns 0, if ea == eb, otherwise, returns a value i != 0.

int setRem (void *e, Set s,  int (*cmp)(const void *ea, const void *eb));


// setUnion
// --------
// Create a Set whose content is the union of a and b.
//
// Return the resulting Set or return NULL, in the case of error.
//
// cmp is a user defined comparison function pointer. This function
// compares ea (element of set a) against eb (element of set b).
// cmp returns 0, if ea == eb, otherwise, returns a value i != 0.

Set setUnion (Set a, Set b,  int (*cmp)(const void *ea, const void *eb));


