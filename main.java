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

class Scoreboard{
    private BST<int, String> WinTree;
    private HashST<String, Player> Players;
    private int PlayedGames;

    public void AddGameResult(String WinnerPlayerName, String LooserPlayerName, boolean Draw){

    }

    public void RegisterPlayer(String PlayerName){

    }

    public void CheckPlayer(String PlayerName){

    }

    public Player[] WinRange(int low, int high){

    }

    public Player[] WinSuccesor(int Wins){

    }
};

class ConnectFour{
    private char[][] Grid = new char[7][6];
    private char CurrentSymbol;

    public ConnectFour(char CurrentSymbol, char[][] Grid){
        this.CurrentSymbol = "X"; //En teoria, X pq siempre inicia X
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                Grid[i][j] = ' ';
            }
        }
    }

    public boolean MakeMove(int z){
        for(int i = 5; i >= 0; i--){
            if(Grid[i][z] == ' '){
                Grid[i][z] = CurrentSymbol;
                return true;
            }
            else{
                return false;
            }
        }
    }

    public int isGameOver() { //retorna 0 si naca se ha acabado, 1 si ganó papuA, 2 si ganó papuB, 3 si empataron noma
        int A;
        int B;
        for(int i = 0; i < 7; i++){ //revisa todas las columnas pa ver si ganó
            A=0;
            B=0;
            for(int j = 0; j < 6; j++){
                if(Grid[i][j] == "X"){
                    X++;
                }else if(Grid[i][j] == "O"){
                    B++;
                }
            }
            if(X == 4){
                return 1;
            }else if(B == 4){
                return 2;
            }
        }
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                if(Grid[i][j] == "X"){
                    X++;
                }else if(Grid[i][j] == "O"){
                    B++;
                }
            }
            if(X == 4){
                return 1;
            }else if(B == 4){
                return 2;
            }
        }
        for(int i = 0; i < 7; i++){ // En diagonal pal lao
            for(int j = 0; j < 6; j++){
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

        for(int i = 0; i < 7; i++){ // En diagonal pal otro lao
            for(int j = 0; j < 6; j++){
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
    };

}
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

    public void PrintWeas() {
        System.out.println("Status : " + Status);
        System.out.println("WinnerPlayerName : " + WinnerPlayerName);
        System.out.println("PlayerNameA : " + PlayerNameA);
        System.out.println("PlayerNameB : " + PlayerNameB);
        System.out.println("ConnectFour : " + ConnectFour);
    }};

public class Main
{
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}