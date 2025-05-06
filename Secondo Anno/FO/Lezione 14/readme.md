# 14 - Da Grammatica a Macchina di Turing

La Grammatica è un modello di calcolo - da una grammatica possiamo descrivere come generare delle parole appartenenti ad un insieme di parole. La Macchina di Turing è un modello di calcolo - una macchina di Turing descrive come riconoscere se delle parole appartengono ad un insieme di parole.

I due modelli sono equivalenti ossia:

- *Se un linguaggio è accettato da una Macchina di Turing allora esiste una grammatica di tipo 0 che lo genera*.

- *Se un linguaggio è generato da una grammatica di tipo 0 allora esiste una macchina di Turing che lo accetta.*

> **Le grammatiche di tipo 0 soddisfano la tesi di Church-Turing.**

**Teorema:** *Per ogni macchina di Turing $T$ a un nastro e con alfabeto $\{0,1\}$ *esiste una macchina di Turing $T'$ con un solo nastro semi-infinito e che non scrive mai $\square$ tale che - per ogni $x \in \{0,1\}^*$ $[o_{T(x)} = o_{T'}(x)]$.

**Teorema G4:** *Per ogni linguaggio accettabile $L$ esiste una grammatica $G$ di tipo 0 tale che $L=L(G)$*.

