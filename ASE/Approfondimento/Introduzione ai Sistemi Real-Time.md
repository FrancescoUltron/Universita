Si trovano molto vicini all'hardware comparati rispetto ai SO general purpose.

Hanno uno scopo diverso rispetto ad altri SO, infatti deve rispondere in un tempo definito.
Questa cosa non è garantita nei SO interattivi, ci deve essere un tempo di risposta preciso.

Si possono trovare in diverse applicazioni:

- Sistemi di controllo digitale: Sistemi in cui ci sono sensori che monitorano il sistema oltre a dare un ouput danno anche una controreazione che serve a bilanciare il sistema dopo l'arrivo delll'output.

- Processamento di segnali: Deve avvenire in un tempo prestabilito, altrimnenti il segnale perde la sua informazione

- Base di dati temporali: Base di dati dinamiche in cui le informazioni perdono di significato dopo un certo periodo di tempo quindi vanno costantemente aggiornate.

- Applicazioni multimediali: Se le trasformazioni multimediali non sono fatte in maniera predicibile, allora alcuni flussi di dati arrivano in ritardo, esempio: film con audio in ritardo.

La corettezza logica di un sistema real-time dipende non solo dal risultato fornito, ma anche dall'istanza temporale in cui tale risultato viene fornito.

Possono essere:

- Puramente ciclici
- A maggioranza ciclica: Esistono task aperiodici.
- Asincroni predicibili: Accetta tutti i task asincroni, ma rimane predicile.
- Asincroni impredicibili

Job: Unità di lavoro che può essere schedulato ad eseguito da un sistema real-time e caratterizzato da: 

 - Vincoli temporali
 - Parametri funzionali 
 - Uso di risorse
 - Proprietà di inteconnessione e dipendenza

Task: Insieme di job tra loro correlati che insieme realizzano una determainata funzione del sistema, possono essere:

- ==**Periodici**==
- ==**Aperiodici**==: Ha istanti di rilascio casuali
- Sporadico: Aperiodico con intervallo di tempo minimo tra il rilascio di due job

I vincoli temporali di un job sono:

- Istante di rilascio: Istante in cui il job diventa disponibile per l'esecuzione
- Scadenza: istante In cui deve essere eseguito il job
- Scadenza relativa: Massimo di tempo di risposta ammeso
- Intervallo di fattibilità: Interavallo di tempo tra istante di rilascio e scadenza 

Esistono diversi tipi di sistemi real-time:

- **Hard real-time**: Non è ammesso il mancato rispetto delle scadenze.
- Firm real-time: Perdere la scadenza rende l'informazione inutile.
- **Soft real-time**: Perdere la scadenza non comporta il non poter usare il risultato.

Bisogna far validare tutti i sistemi Hard real-time 

Il tempo d'esecuzione è il tempo in cui possiamo eseguire un job da solo e con tutte le risorse disponibili.

Come facciamo a definire il tempo d'esecuzione di un job?
Si fa un anlisi statistica per capirlo.

## Modello a task periodici

In questi modelli conosco un task e lo ripeto periodicamente, quindi una sequenza di job che deve essere ripetuta in maniera periodica in un tempo d'esecuzione.

Iperperiodo: mcm di tutti i periodi dei task, per farli coesistere devo definire il massimo range temporale per il quale tutti i job sono ripetuti, dentro l'iperperiodo eseguo N job.

Scheduler: Modulo del Sistema Operativo che realizza gli algoritmi di ordinamento di esecuzione dei job e ne permette l'accesso alle risorse, la schedulazione è un'assegnazione dei job del sistema ai processori disponibili 

## Schedulazione clock-driven

Schedulazione guidata dal clock, le attività vengono pianificate sulla base delle interruzioni ricevute dall'orologio, ogni tick lo scheduler svolge quello che deve fare.

Vantaggi:

- Facile da validare
- Efficiente

Svantaggi:

- Poco flessibili

Prendono decisioni ad intervalli prefissati di clock, sono adatti per sistemi deterministici in cui i job sono già conosciuti a priori.

Come faccio a capire se un sistema è sostenibile?
Calcoliamo l'iperperiodo e scheduliamo i task 

Uno schedulazione clock-driven opuò essere rappresentato da una tabella di come i job si evolvono dell'iperperiodo.
## Programmazione Bare-Metal

Andiamo a programmare direttamente sull'hardware senza l'utillizo di un SO, la particolarità è che non avendo un compilatore dobbiamo usare un cross compilatore da un altro computer.

- Attivazione UART
- Attivazione timer
- Attivazione interrupt del timer
- Attivazione dello scheduler e della tabella