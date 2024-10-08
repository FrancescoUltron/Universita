//Programma con diverse funzioni per la manipolazione di stringhe
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int lunghezza(char *s); //complessità O(n)
char *concatena(char *s1, char *s2); //complessità O(n)
char *duplica(char *s); //complessità O(n)
char *inverte(char *s); //complessità O(n)
char *taglia(char *s, int inizio, int fine); //complessità O(n)
char *sostituisci(char *s, char *vecchia, char *nuova); //complessità O(n)
int anagrammi(char *s1, char *s2); //complessità O(n)
char *elimina(char *s, char *c); //complessità O(n)

char *elimina(char *s, char *c)
{
    int l = lunghezza(s);
    int count[256] = {0};
    for (int i = 0; i < lunghezza(c); i++)
    {
        count[c[i]] = 1;
    }
    char *t = malloc(l + 1);
    int j = 0;
    for (int i = 0; i < l; i++)
    {
        if (count[s[i]] == 0)
        {
            t[j] = s[i];
            j++;
        }
    }
    t[j] = '\0';
    return t;
}

int anagrammi(char *s1, char *s2)
{
    int l1 = lunghezza(s1);
    int l2 = lunghezza(s2);
    if (l1 != l2)
    {
        return 0;
    }
    
    int count[256] = {0}; 
    
    for (int i = 0; i < l1; i++)
    {
        count[s1[i]]++;
        count[s2[i]]--;
    }
    
    for (int i = 0; i < 256; i++)
    {
        if (count[i] != 0)
        {
            return 0;
        }
    }
    
    return 1;
}

char *sostituisci(char *s, char *vecchia, char *nuova)
{
    int l = lunghezza(s);
    int lv = lunghezza(vecchia);
    int ln = lunghezza(nuova);
    char *t = malloc(l + 1);
    for (int i = 0; i < l; i++)
    {
        if (strncmp(s + i, vecchia, lv) == 0)
        {
            for (int j = 0; j < ln; j++)
            {
                t[i + j] = nuova[j];
            }
            i += lv - 1;
        }
        else
        {
            t[i] = s[i];
        }
    }
    t[l] = '\0';
    return t;
}

char *taglia(char *s, int inizio, int fine)
{
    int l = lunghezza(s);
    if (inizio < 0 || inizio >= l || fine < 0 || fine >= l || inizio > fine)
    {
        return NULL;
    }
    char *t = malloc(fine - inizio + 2);
    for (int i = 0; i <= fine - inizio; i++)
    {
        t[i] = s[inizio + i];
    }
    t[fine - inizio + 1] = '\0';
    return t;
}

char *inverte(char *s)
{
    int l = lunghezza(s);
    char *i = malloc(l + 1);
    for (int j = 0; j < l; j++)
    {
        i[j] = s[l - j - 1];
    }
    i[l] = '\0';
    return i;
}

char *duplica(char *s)
{
    int l = lunghezza(s);
    char *d = malloc(l + 1);
    for (int i = 0; i < l; i++)
    {
        d[i] = s[i];
    }
    d[l] = '\0';
    return d;

}

char *concatena(char *s1, char *s2)
{
    int l1 = strlen(s1);
    int l2 = strlen(s2);
    char *s = malloc(l1 + l2 + 1);
    for (int i = 0; i < l1; i++)
    {
        s[i] = s1[i];
    }
    for (int i = 0; i < l2; i++)
    {
        s[l1 + i] = s2[i];
    }
    s[l1 + l2] = '\0';
    return s;
}

int lunghezza(char *s)
{
    int i = 0;
    while (s[i] != '\0')
    {
        i++;
    }
    return i;
}