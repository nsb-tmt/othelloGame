package com.example.othellover2;

import android.widget.Toast;

import java.util.ArrayList;

public class OthelloController {
    int[][] pieceId = {
        {R.id.othelloPiece1,R.id.othelloPiece2,R.id.othelloPiece3,R.id.othelloPiece4},
        {R.id.othelloPiece5,R.id.othelloPiece6,R.id.othelloPiece7,R.id.othelloPiece8},
        {R.id.othelloPiece9,R.id.othelloPiece10,R.id.othelloPiece11,R.id.othelloPiece12},
            {R.id.othelloPiece13,R.id.othelloPiece14,R.id.othelloPiece15,R.id.othelloPiece16}
    };
    ArrayList<ArrayList<PieceManager>> board = new ArrayList<>();
    MainActivity main;
    PutChecker pc = new PutChecker(this);
    int[][] boardPiece = {//盤面情報
            {0,0,0,0},
            {0,1,2,0},
            {0,2,1,0},
            {0,0,0,0}
    };
    int[] imageId = {R.drawable.reversi_black,R.drawable.reversi_white};
    boolean BlackTurn = true;//初期ターンは黒
    OthelloController(MainActivity main){
            this.main = main;
        for(int i = 0;i<pieceId.length;i++){//リストの作成
            ArrayList<PieceManager> pieces = new ArrayList<>();
            for(int j =0;j<pieceId[i].length;j++){
                int[] point = {i,j};
                pieces.add(new PieceManager(main.findViewById(pieceId[i][j]),this,point));
            }
            board.add(pieces);
        }
        applyBoardColor();
    }
    void applyBoardColor(){//現在の盤面情報に合わせて盤面の情報を変更する
        for(int i = 0; i< boardPiece.length; i++){
            for(int j = 0; j< boardPiece[i].length; j++){
                int[] point ={i,j};
                board.get(i).get(j).setMyColor(boardPiece[i][j]);
                board.get(i).get(j).setCanPutColor(pc.putCheck(point));
            }
        }
    }
    void putPiece(int[] point){
        if(!pc.putCheck(point)){//裏返せない場所の時
            return;
        }
        boardPiece[point[0]][point[1]] = (BlackTurn)?1:2;
        reversePiece(point);
        if(BlackTurn){//黒のターン
            BlackTurn = false;//ターンを変更
        }else{//白のターン
            BlackTurn = true;//ターンを変更
        }
        if(!pc.canPutPiece()){//次のターンにおける駒がない時
            String result = BlackTurn?"黒":"白";
            if(BlackTurn){
                BlackTurn = false;//ターンを変更
            }else{
                BlackTurn = true;//ターンを変更
            }
            if(!pc.canPutPiece()){//黒が駒を置けず、白も駒を置けないとき勝敗の判定
                winChecker();
                applyBoardColor();
                return;
            }
            Toast.makeText(main, result+"のプレイヤーは置ける駒がありません", Toast.LENGTH_SHORT).show();
        }
        applyBoardColor();

    }
    void winChecker(){
        int BLACK = 0;
        int WHITE = 0;
        for(int[] pieces:boardPiece){
            for(int piece:pieces){
                if(piece == 1){
                    BLACK++;
                }else if(piece == 2){
                    WHITE++;
                }
            }
        }
        String result = (BLACK==WHITE)?(BLACK>WHITE)?"引き分けです":"黒の勝ちです":"白の勝ちです";
        Toast.makeText(main, result, Toast.LENGTH_SHORT).show();
    }
    void reversePiece(int[] point){
        int TurnColor;
        if(BlackTurn){
            TurnColor = 1;
        }else{
            TurnColor = 2;
        }
        int[][] directions = {
                {-1,-1},{-1,0},{-1,1},
                {0,-1},{0,1},
                {1,-1},{1,0},{1,1}
        };
        for(int[]direction:directions){
            if(pc.checkDirection(point,direction[0],direction[1])){
                int putX = point[0]+direction[0];
                int putY = point[1]+direction[1];
                while(!(boardPiece[putX][putY]==TurnColor)){
                    boardPiece[putX][putY] = TurnColor;
                    putX+=direction[0];
                    putY+=direction[1];
                }
            }
        }
    }
}
