Servono a rappresentare dei numeri in altri modi, il tipo di sistemi che ci interessa sono quelli posizionali, cioè che ==il peso di una cifra dipende dalla posizione in cui si trova.==

esempi:

 - **Decimale** {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
 - **Binario** {0, 1}
 - **Ottale** {0, 1, 2, 3, 4, 5, 6, 7}
 - **Esadecimale** {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F}

Sequenza numerica: $x_{n-1}......x_1x_0$ dove $x_i$ = {0,1, ...... , B-1}  per ogni i = {0, ....., 9}:  $\sum_{k = 0} ^{n-1} x_kB^k$

esempio:

$2024_{10}= 2*10^3 + 0*10^2 +2*10 + 4*10^0$

**Binario -------> Decimale**:

$(111110)_2$ = $\sum_{k = 0} ^{5} x_k2^k$ = $1*2^5+1*2^4+1*2^3+1*2^2+1*2^1+0*2^0$ = $(62)_{10}$

**Decimale -------> Qualsiasi base:

$(62)_{10}$ = $(111110)_2$                     $(62)_{10} = (2022)_3$

| **62** | **0** |     |     | 62  | **2**   |
| ------ | ----- | --- | --- | --- | --- |
| 31     | **1** |     |     | 20  | **2**   |
| 15     | **1** |     |     | 6   | **0**   |
| 7      | **1** |     |     | 2   | **2**   |
| 3      | **1** |     |     |     |     |
| 1      | **1** |     |     |     |     |

**Binario -------> Ottale e Esadecimale**:

$(111110)_2 = (35)_{16}$
*$(111110)_2 = (76)_8$

**Esadecimale -------> Binario**:

$(5D7A)_{16} = (0101110101111010)_2$
















