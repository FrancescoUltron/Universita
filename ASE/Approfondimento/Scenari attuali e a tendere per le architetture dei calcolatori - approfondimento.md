software a tre livelli: Fomato da una parte client, da un application server e un database.

Attuali sistemi operativi:

- Commerciali
- Non commerciali 

UNIX non è open source, (1969) ha come standard di API si chiama POSIX, questo standard viene adottato anche da Linux

La grande differenza è che UNIX viene fornito con lincenza, linux NO, ciò complica l'utilizzo commerciale perché non ci sta garanzia, ma in realta ce ne sono alcune che hanno garanzia.

Attiuali Sistemi Operativi:

- Windows: Facile da usare, ha una semplice gestione e dalla versione windows 10 è presente l'emulatore per linux, ma è molto diffuso e quindi esposto a problemi di sicurezza, ha il vantaggio della licenza che permette un'assicurazione, condividono un **active directory**, bisogna installare tante patch di sicurezza (è un problema perché devo fermare il SO specialmente con gli SLA).

-  macOS: E' usato in ambienti particolari (Musica, video editing ecc......), il SO è integrato con l'hardware, è un settore di nicchia

- IBM AIX

- HP UX

- Oracle Solaris: oracle è il database relazionale più diffuso al mondo ha un file system paricolare (Zettabyte File System).

- Linux Red Hat

- Fedora

- ubuntu

- debian

- FreeBSD

- Android

DATA CENTER(CED): area usata per alloggiare architetture hanno come vantaggi: altà affidabilità, continuità operativa, backup, controllo ambientale, sicurezza e protezione, efficienza.

In passato ci stavano i Server Bare-Metal, cioè un server che era unico per un cliente, aveva diversi svantaggi: costi alti, tempi lunghi di rilascio.

Invece di dare un server fisico ad una persona conviene virtualizzare il server e condividerlo.

Sistema operativo VM/370:

virtualizzazione: si riferisce alla possibilità di astrarre le componenti hardware degli elaborato a fine di renderli disponibili da software sottoforma di risorsa virtuale.

CONTAINER: concetto che nasce nel 2004, è una nuova astrazione quindi lavoriamo a sistema operativo, è un unità standard che racchiude il codice e tutte le sue dipendenze in modo che l'applicazione funzioni sempre sullo stesso kernel girano diverse macchine con diversi sistemi.

Se uno dei docker va giu non ci sta problema perché gli altri contuineranno a funzionare.

La differenza ha un'astrazione maggiore rispetto alla macchina virtuale, nell'ultimo caso avro una macchina virtuale compresa di RAM virtuale, CPU virtuale ecc..., con i container docker sono un'astrazione a livello di applicazione che raggruppa codice e le dipendenze.

Caratteristiche dei container:

- Sono leggeri
- Non hanno bisogno di SO
- Possiamo avere più docker di MV su PC

Orchestratori di container: Sono software ideati per semplificare il lavoro dei team per la gestione dei container.

Docker Swarm è un cluster di macchine che eseguono Docker, le macchine swarm possono essere fisiche o virtuali e vengono intrecciati come nodi.

Un orchestratore famoso è Kubernetes permette l'automazione dei deployment.
Un altro è OpenShift Container Platform (OCP)


VIRTUALIZZAZIONE DELLA RETE: Ogni grande azienda usano reti private: per virtualizzare la rete si usano dispositivi di Software-Defined Networking(SDN)

VIRTUALIZZAZIONE DELLO STORAGE: Software Define Storage (SDS):

- file based
- block-based

Cluod Computing: è un modello che permette di rendere le risorse del sistema informatico disponibili su richiesta senza una gestione attiva da parte dell'utente, possono essere limitati ad una singola organizzazione o per molte organizzazione senza preoccuparmi delle macchine fisiche.

Caratteristiche:

- On-demand self-service: Consumatore richiede capacità di calcolo 
- Rete: le funzionalità sono in rete
- Resource pooling: Le risorse sono rappruppate per più consumatori
- Rapid elasticy
- Measured service

COME CAMBIERA  IL MODO DELLO SVILUPPO SOFTWARE:

occorre ridurre il time-to-market, si deve decentralizzarre, un modo per fare ciò è tramite microservizio: suddividiamo un'applicazione in una serie di componenti più piccole e specializzate ciascune con le proprio interfaccie comuni come API

Sono essenzialmente tanti piccoli server con ognuno il suo servizio, meglio avere tante macchine piccole con i propri servizi in modo tale da evitare che perdendo un server perdi tutto.

il processo di sviluppo è un insieme di pratiche che favorisce la collaborazione tra team di sviluppo software e quelli deputati da parte operativa,  semplicemente è il processo dietro alla creazione di un software.

DevOps: punta alla comunicazione e alla collaborazione e integrazione tra sviluppatori e alla operatività del prodotto in opposizione alla indipendwnza tra le due fasi del team. E' un ciclo di vita del servizio/prodotto.

obiettivi di devops:

- aumenta la frequenza di deploy 
- Riduce numero di errori nelle release
- riduce periodo di esposizione
- riduce tempo di attesa tra le fix
- riduce il tempo di recovery 
- Gestire facilmente rilasci non pianificate 

Motivazioni: 

- Favorire l'integrazione tra gruppi di sviluppo e di infrastrutture
- Abilita lo sviluppo di nuovi contesti 

Agile è un altro metodo di sviluppo di un software è simile, ma hanno una differenza, cioè che in Agile i vari compartimenti di sviluppo sono completamente separati o si lavora con altri settori solo quando ci sta effettivamente un bisogno di qualcosa di un altro settore, in DevOps invece si cerca di riunire i diversi settori e quindi di interdipendenza.

Sono un pò delle BS

SERVERLESS (Fass)


Un problema del CLuod Storage è quello da punto di vista legale, cioè dove si trovano i dati fisicamente, tutto quello che serve a gestire il paese è strategico è non possono uscire dal paese, i dati sono l'oro del terzo millennio e dobbiamo capire a chi darli.
Il motivo per cui vogliamo architetture sempre più veloce è per bucare la crittografia

tipi diversi di infrastuttura:

- Iaas
- PaaS
- SaaS
- CaaS
- FaaS




