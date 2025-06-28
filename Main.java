import java.util.*;
import java.io.*;

class Player{
    private String PlayerName;
    private int Wins;
    private int Draws;
    private int Losses;

    Player(String PlayerName) {
        this.PlayerName = PlayerName;
        this.Wins = 0;
        this.Draws = 0;
        this.Losses = 0;
    }

    public void SetPlayerName (String NewPlayerName) {
        PlayerName = NewPlayerName;
    }
    public void AddWins () {
        Wins++;
    }
    public void AddDraws () {
        Draws++;
    }
    public void AddLosses () {
        Losses++;
    }

    public String GetPlayerName() {
        return PlayerName;
    }
    public int GetWins() {
        return Wins;
    }
    public int GetDraws() {
        return Draws;
    }
    public int GetLosses() {
        return Losses;
    }

    public float WinRate() {
        int SiNoPongoEstoSeBugeaLol = (Wins + Draws + Losses);
        if(SiNoPongoEstoSeBugeaLol == 0){
            return 0;
        }else {
            return (float)Wins/SiNoPongoEstoSeBugeaLol;
        }
    }

    public void PrintWeas() {
        System.out.println("PlayerName : " + PlayerName);
        System.out.println("Wins : " + Wins);
        System.out.println("Draws : " + Draws);
        System.out.println("Losses : " + Losses);
    }}; //Listoko

class Scoreboard {
    private BST<Integer, List<String>> WinTree;
    private HashST<String, Player> Players;
    private int PlayedGames;

    public Scoreboard() {
        WinTree = new BST<>();
        Players = new HashST<>();
        PlayedGames = 0;
    }

    public void AddGameResult(String WinnerPlayerName, String LooserPlayerName, boolean Draw) {
        if (!Players.contains(WinnerPlayerName) || !Players.contains(LooserPlayerName)) return;

        Player Winner = Players.get(WinnerPlayerName);
        Player Loser = Players.get(LooserPlayerName);

        // Eliminar del árbol al ganador actual (si tenía victorias)
        int OldWinnerWins = Winner.getWins();
        List<String> WinnerList = WinTree.get(OldWinnerWins);
        if (WinnerList != null) {
            WinnerList.remove(WinnerPlayerName);
            if (WinnerList.isEmpty()) {
                WinTree.delete(OldWinnerWins);
            } else {
                WinTree.put(OldWinnerWins, WinnerList);
            }
        }

        // Eliminar del árbol al perdedor actual (si tenía victorias)
        int OldLoserWins = Loser.getWins();
        List<String> LoserList = WinTree.get(OldLoserWins);
        if (LoserList != null) {
            LoserList.remove(LooserPlayerName);
            if (LoserList.isEmpty()) {
                WinTree.delete(OldLoserWins);
            } else {
                WinTree.put(OldLoserWins, LoserList);
            }
        }

        // Actualizar estadísticas
        if (Draw) {
            Winner.addDraw();
            Loser.addDraw();
        } else {
            Winner.addWin();
            Loser.addLoss();
        }

        // Reinsertar al ganador en nueva posición del árbol
        int NewWinnerWins = Winner.getWins();
        List<String> NewWinnerList = WinTree.get(NewWinnerWins);
        if (NewWinnerList == null) NewWinnerList = new ArrayList<>();
        NewWinnerList.add(WinnerPlayerName);
        WinTree.put(NewWinnerWins, NewWinnerList);

        // Reinsertar al perdedor en nueva posición del árbol
        int NewLoserWins = Loser.getWins();
        List<String> NewLoserList = WinTree.get(NewLoserWins);
        if (NewLoserList == null) NewLoserList = new ArrayList<>();
        NewLoserList.add(LooserPlayerName);
        WinTree.put(NewLoserWins, NewLoserList);

        PlayedGames++;
    }

     public void RegisterPlayer(String PlayerName) {
        if (!Players.contains(PlayerName)) {
            Player NewPlayer = new Player(PlayerName);
            Players.put(PlayerName, NewPlayer);

            List<String> Names = WinTree.get(0);
            if (Names == null) Names = new ArrayList<>();
            Names.add(PlayerName);
            WinTree.put(0, Names);
        }
    }

    public boolean CheckPlayer(String PlayerName) {
        return Players.contains(PlayerName);
    }

     public Player[] WinRange(int Lo, int Hi) {
        List<Player> Result = new ArrayList<>();

        for (int Key : WinTree.keysInRange(Lo, Hi)) {
            List<String> Names = WinTree.get(Key);
            if (Names != null) {
                for (String Name : Names) {
                    Player P = Players.get(Name);
                    if (P != null) Result.add(P);
                }
            }
        }

        return Result.toArray(new Player[0]);
    }

     public Player[] WinSuccesor(int Wins) {
        Integer NextKey = WinTree.successor(Wins);
        if (NextKey == null) return new Player[0];

        List<String> Names = WinTree.get(NextKey);
        List<Player> Result = new ArrayList<>();

        if (Names != null) {
            for (String Name : Names) {
                Player P = Players.get(Name);
                if (P != null) Result.add(P);
            }
        }

        return Result.toArray(new Player[0]);
    }

} //listo

class ConnectFour{
    private char[][] Grid = new char[7][6];
    private char CurrentSymbol;
    
    public char getCurrentSymbol() {
    return CurrentSymbol;
}// para alternar el simbolo

    public ConnectFour(char CurrentSymbol){
        this.CurrentSymbol = 'X'; //En teoria, X pq siempre inicia X
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                Grid[i][j] = ' ';
            }
        }
    }

    public boolean MakeMove(int z){
        
        if(z < 0 || z >= 7){
            return false;
        }
        
        for(int i = 5; i >= 0; i--){
            if(Grid[i][z] == ' '){
                Grid[i][z] = CurrentSymbol;
                CurrentSymbol = (CurrentSymbol == 'X') ? 'O' : 'X'; // alterna símbolo
                return true;
            }
            else{
            }
        }
            return false;
    }

    public int isGameOver() { //retorna 0 si naca se ha acabado, 1 si ganó papuA, 2 si ganó papuB, 3 si empataron noma
        int A = 0;
        int B = 0;
        for(int i = 0; i < 7; i++){ //revisa todas las columnas pa ver si ganó
            A=0;
            B=0;
            for(int j = 0; j < 6; j++){
                if(Grid[i][j] == 'X'){
                    X++;
                }else if(Grid[i][j] == 'O'){
                    B++;
                }
            }
            if(A == 4){
                return 1;
            }else if(B == 4){
                return 2;
            }
        }

        for(int i = 0; i < 7; i++){ //De lao a lao
            for(int j = 0; j < 6; j++){
                if(Grid[i][j] == 'X'){
                    X++;
                }else if(Grid[i][j] == 'O'){
                    B++;
                }
            }
            if(X == 4){
                return 1;
            }else if(B == 4){
                return 2;
            }
        }
        
        for(int i = 0; i < 7-4; i++){ // En diagonal pal lao
            for(int j = 0; j < 6-4; j++){
                char temp = Grid[i][j];
                if(temp != ' ' && temp == Grid[i+1][j+1] && temp == Grid[i+2][j+2] && temp == Grid[i+3][j+3]){
                    if(temp == 'X'){
                        return 1;
                    }else if(temp == 'O'){
                        return 2;
                    }
                }
            }
        }

        for(int i = 0; i < 7-4; i++){ // En diagonal pal otro lao
            for(int j = 3; j < 6; j++){
                char temp = Grid[i][j];
                if(temp != ' ' && temp == Grid[i-1][j-1] && temp == Grid[i-2][j-2] && temp == Grid[i-3][j-3]){
                    if(temp == 'X'){
                        return 1;
                    }else if(temp == 'O'){
                        return 2;
                    }
                }
            }
        }
        return 0;
    }

}; // me parece que esta listo, aunque el constructor no me convence

class Game{
    private String Status;
    private String WinnerPlayerName;
    private String PlayerNameA;
    private String PlayerNameB;
    private ConnectFour ConnectFour;

    Game(String WinnerPlayerName,String PlayerNameA,String PlayerNameB,ConnectFour ConnectFour) {
        this.Status = "IN_PROGRESS";
        this.WinnerPlayerName = WinnerPlayerName;
        this.PlayerNameA = PlayerNameA;
        this.PlayerNameB = PlayerNameB;
        this.ConnectFour = ConnectFour;
    }

    public void SetStatus (String NewStatus) {
        Status = NewStatus;
    }
    public void SetWinnerPlayerName (String NewWinnerPlayerName) {
        WinnerPlayerName = NewWinnerPlayerName;
    }
    public void SetPlayerNameA (String NewPlayerNameA) {
        PlayerNameA = NewPlayerNameA;
    }
    public void SetPlayerNameB (String NewPlayerNameB) {
        PlayerNameB = NewPlayerNameB;
    }
    public void SetConnectFour (ConnectFour NewConnectFour) {
        ConnectFour = NewConnectFour;
    }

    public String GetStatus() {
        return Status;
    }
    public String GetWinnerPlayerName() {
        return WinnerPlayerName;
    }
    public String GetPlayerNameA() {
        return PlayerNameA;
    }
    public String GetPlayerNameB() {
        return PlayerNameB;
    }
    public ConnectFour GetConnectFour() {
        return ConnectFour;
    }
    
    public char[][] getGrid() {//para imprimir la tabla con menos problema
    return Grid;
}

    
    public String play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();

            String currentPlayer = (ConnectFour.getCurrentSymbol() == 'X') ? PlayerNameA : PlayerNameB;
            System.out.print("Turno de " + currentPlayer + " (" + ConnectFour.getCurrentSymbol() + ") - Ingrese columna (0-6): ");

            int column;
            try {
                column = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debes ingresar un número entre 0 y 6.");
                scanner.next(); // limpiar buffer
                continue;
            }

            boolean valid = ConnectFour.MakeMove(column);
            if (!valid) {
                System.out.println("Movimiento inválido. Intenta de nuevo.");
                continue;
            }

            int result = ConnectFour.isGameOver();
            if (result == 1 || result == 2) {
                Status = "VICTORY";
                WinnerPlayerName = (result == 1) ? PlayerNameA : PlayerNameB;
                System.out.println("¡Victoria de " + WinnerPlayerName + "!");
                return WinnerPlayerName;
            } else if (result == 3) {
                Status = "DRAW";
                System.out.println("¡Empate!");
                return "";
            }
        }
    }

    private void printBoard() { //este es un metodo que deeeeveeriiiia imprimir de forma correcta el tablero
        System.out.println(" 0 1 2 3 4 5 6");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("|" + ConnectFour.getGrid()[j][i]);
            }
            System.out.println("|");
        }
        System.out.println("-----------------");
    }
    

    public void PrintWeas() {
        System.out.println("Status : " + Status);
        System.out.println("WinnerPlayerName : " + WinnerPlayerName);
        System.out.println("PlayerNameA : " + PlayerNameA);
        System.out.println("PlayerNameB : " + PlayerNameB);
        System.out.println("ConnectFour : " + ConnectFour);
    }};//listo

public class Main{
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
