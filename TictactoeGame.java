import java.util.Random;
import java.util.Scanner;

public class TictactoeGame {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ticTacToe t=new ticTacToe();

        System.out.println("ENTER THE CHOISE!");
        System.out.println("1.Play with Friend");
        System.out.println("2.Play with Ai");
        int choise=sc.nextInt();
        sc.close();
        t.Display();
        humanPlayer p1 =new humanPlayer("PLAYER 1",'X');
        Player p2;
        if(choise==1){
            p2=new humanPlayer("PLAYER 2",'O');
        }else{
            p2=new aiPlayer("AI",'O');
        }
        Player cp;
        cp=p1;

        while(true){
            System.out.println(cp.name+" Turns");
            cp.makeMove();
            ticTacToe.Display();
            if (ticTacToe.checkColWin()||ticTacToe.checkRowWin()||ticTacToe.checkDiagWin()){
                System.out.println(cp.name+" Has won");
                break;
            }
            else if (ticTacToe.checkDraw()){
                System.out.println("Game is Draw");
                break;
            }
            else{
                if(cp==p1){
                    cp=p2;
                }else{
                    cp=p1;
                }
            }

        }
    }
}
class ticTacToe{
    static char [][] board;
    public ticTacToe(){
        board=new char[3][3];
        initBoard();
    }
    void initBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0 ;j<board[i].length;j++){
                board[i][j]=' ';
            }
        }
    }
    static void Display(){
        System.out.println("-------------");
        for(int i=0;i<board.length;i++){
            System.out.print("| ");
            for(int j=0 ;j<board[i].length;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    static void placeMark(int row,int col,char mark){
        if(row>=0&&row<3&&col>=0&&col<3)
        board[row][col]=mark;
        else
            System.out.println("Invalid Position");
    }
    static boolean checkColWin(){
        for(int j=0;j<3;j++){
            if(board[0][j]!=' '&& board[0][j]==board[1][j] && board[1][j]==board[2][j]){
                return true;
            }
        }
        return false;
    }
    static boolean checkRowWin(){
        for(int i=0;i<3;i++){
            if(board[i][0]!=' '&&board[i][0]==board[i][1]&&board[i][1]==board[i][2]){
                return true;
            }
        }
        return false;
    }
    static boolean checkDiagWin(){
        return board[0][0]!=' '&&board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
                board[0][2]!=' '&&board[0][2] == board[1][1] && board[1][1] == board[2][0];
    }
    static boolean checkDraw(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }

}
abstract class Player{
    String name;
    char mark;
    abstract void makeMove();
    boolean isvValidMove(int row ,int col){
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if(ticTacToe.board[row][col]==' '){
                return true;
            }
        }
        return false;
    }
}
class humanPlayer extends Player{
    humanPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makeMove(){
        Scanner scan=new Scanner(System.in);
        int row;
        int cols;
        do{
            System.out.println("Enter the Row and Cols");
            row=scan.nextInt();
            cols=scan.nextInt();
        }while (!isvValidMove(row, cols));
        ticTacToe.placeMark(row,cols,mark);
        scan.close();
    


    }
}


class aiPlayer extends Player{
    aiPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makeMove(){
        Scanner scan=new Scanner(System.in);
        int row;
        int cols;
        do{
            Random r=new Random();
            row=r.nextInt(3);
            cols=r.nextInt(3);

        }while (!isvValidMove(row, cols));
        ticTacToe.placeMark(row,cols,mark);


    }
}

