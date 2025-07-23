% Problema dei 3 cannibali e 3 missionari
% Stato: [MissionariSx, CannibaliSx, BarcaPosizione, MissionariDx, CannibaliDx]

% Stati iniziale e finale
stato_iniziale([3, 3, sinistra, 0, 0]).
stato_finale([0, 0, destra, 3, 3]).

% Sicurezza: una riva è sicura se non ci sono missionari o i missionari >= cannibali
sicuro(0, _).
sicuro(M, C) :-
    M > 0,
    M >= C.

% --- MOSSE DA SINISTRA A DESTRA ---

move([MS, CS, sinistra, MD, CD], missionario_da_SX_a_DX,
     [MS2, CS, destra, MD2, CD]) :-
    MS2 is MS - 1,
    MD2 is MD + 1,
    MS2 >= 0,
    sicuro(MS2, CS),
    sicuro(MD2, CD).

move([MS, CS, sinistra, MD, CD], cannibale_da_SX_a_DX,
     [MS, CS2, destra, MD, CD2]) :-
    CS2 is CS - 1,
    CD2 is CD + 1,
    CS2 >= 0,
    sicuro(MS, CS2),
    sicuro(MD, CD2).

move([MS, CS, sinistra, MD, CD], due_missionari_da_SX_a_DX,
     [MS2, CS, destra, MD2, CD]) :-
    MS2 is MS - 2,
    MD2 is MD + 2,
    MS2 >= 0,
    sicuro(MS2, CS),
    sicuro(MD2, CD).

move([MS, CS, sinistra, MD, CD], due_cannibali_da_SX_a_DX,
     [MS, CS2, destra, MD, CD2]) :-
    CS2 is CS - 2,
    CD2 is CD + 2,
    CS2 >= 0,
    sicuro(MS, CS2),
    sicuro(MD, CD2).

move([MS, CS, sinistra, MD, CD], missionario_e_cannibale_da_SX_a_DX,
     [MS2, CS2, destra, MD2, CD2]) :-
    MS2 is MS - 1,
    CS2 is CS - 1,
    MD2 is MD + 1,
    CD2 is CD + 1,
    MS2 >= 0,
    CS2 >= 0,
    sicuro(MS2, CS2),
    sicuro(MD2, CD2).

% --- MOSSE DA DESTRA A SINISTRA ---

move([MS, CS, destra, MD, CD], missionario_da_DX_a_SX,
     [MS2, CS, sinistra, MD2, CD]) :-
    MS2 is MS + 1,
    MD2 is MD - 1,
    MD2 >= 0,
    sicuro(MS2, CS),
    sicuro(MD2, CD).

move([MS, CS, destra, MD, CD], cannibale_da_DX_a_SX,
     [MS, CS2, sinistra, MD, CD2]) :-
    CS2 is CS + 1,
    CD2 is CD - 1,
    CD2 >= 0,
    sicuro(MS, CS2),
    sicuro(MD, CD2).

move([MS, CS, destra, MD, CD], due_missionari_da_DX_a_SX,
     [MS2, CS, sinistra, MD2, CD]) :-
    MS2 is MS + 2,
    MD2 is MD - 2,
    MD2 >= 0,
    sicuro(MS2, CS),
    sicuro(MD2, CD).

move([MS, CS, destra, MD, CD], due_cannibali_da_DX_a_SX,
     [MS, CS2, sinistra, MD, CD2]) :-
    CS2 is CS + 2,
    CD2 is CD - 2,
    CD2 >= 0,
    sicuro(MS, CS2),
    sicuro(MD, CD2).

move([MS, CS, destra, MD, CD], missionario_e_cannibale_da_DX_a_SX,
     [MS2, CS2, sinistra, MD2, CD2]) :-
    MS2 is MS + 1,
    CS2 is CS + 1,
    MD2 is MD - 1,
    CD2 is CD - 1,
    MD2 >= 0,
    CD2 >= 0,
    sicuro(MS2, CS2),
    sicuro(MD2, CD2).

% --- RISOLUZIONE ---

risultato(Inizio, Fine, Soluzione) :-
    risultato(Inizio, Fine, [Inizio], Soluzione).

risultato(Pos, Pos, _, []).

risultato(PosCorrente, PosFinale, Visitati, [Mossa | AltreMosse]) :-
    move(PosCorrente, Mossa, NuovaPos),
    \+ member(NuovaPos, Visitati),
    risultato(NuovaPos, PosFinale, [NuovaPos|Visitati], AltreMosse).