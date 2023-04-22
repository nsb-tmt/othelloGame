package com.example.othellover2;

import java.util.ArrayList;

public class PutChecker {
    OthelloController oc;
    int TurnColor;
    int altarColor;
    PutChecker(OthelloController oc){
        this.oc = oc;
    }
    int[][] directions = {
            {-1,-1},{-1,0},{-1,1},
            {0,-1},{0,1},
            {1,-1},{1,0},{1,1}
    };
    void currentTurn(){
        if(oc.BlackTurn){
            TurnColor = 1;
            altarColor = 2;
        }else{
            TurnColor = 2;
            altarColor = 1;
        }
    }
    boolean putCheck(int[] point){
        if(oc.boardPiece[point[0]][point[1]]!=0){
            return false;
        }
        for(int[] direction:directions){
            if(checkDirection(point,direction[0],direction[1])){
                return true;
            }
        }
        return false;
    }
    boolean canPutPiece(){
        for(int i = 0;i<oc.pieceId.length;i++){//リストの作成
            for(int j =0;j<oc.pieceId[i].length;j++){
                int[] point = {i,j};
                if(putCheck(point)){//置ける場所が一マスでもあるなら
                    return true;
                }
            }
        }
        return false;
    }
//    ArrayList<ArrayList<Integer>> searchReversible(int[]point,int dirX,int dirY){
//        ArrayList<ArrayList<Integer>> reversibleList = new ArrayList<>();
//        currentTurn();
//        int pointX = point[0];
//        int pointY = point[1];
//        boolean check = false;
//        while(pointX+dirX>=0&&pointX+dirX<oc.boardPiece.length){//左上の探索
//            pointX += dirX;//X座標が範囲内の時pointXの更新
//            if(pointY+dirY<0||pointY+dirY>=oc.boardPiece[pointX].length){
//                break;
//            }
//            pointY += dirY;//PointYの更新
//            if (oc.boardPiece[pointX][pointY] == TurnColor) {
//                if (check) {//駒の隣接色が相手色で自色にたどり着いたとき
//                    return reversibleList;
//                }//そうでないときは比較終了
//            }else if(oc.boardPiece[pointX][pointY] == altarColor){
//                check = true;
//                ArrayList<Integer> reversiblePoint = new ArrayList<>();
//                reversiblePoint.add(pointX);
//                reversiblePoint.add(pointY);
//                reversibleList.add(reversiblePoint);
//                continue;
//            }//まだおいていない場所の時はその先は探索しない
//            break;
//        }
//        reversibleList.clear();
//        return reversibleList;
//    }
    boolean checkDirection(int[]point,int dirX,int dirY){
        currentTurn();
        int pointX = point[0];
        int pointY = point[1];
        boolean check = false;
        while(pointX+dirX>=0&&pointX+dirX<oc.boardPiece.length){//左上の探索
            pointX += dirX;//X座標が範囲内の時pointXの更新
            if(pointY+dirY<0||pointY+dirY>=oc.boardPiece[pointX].length){
                return false;//X座標に問題はないけどY座標が範囲外を指定するとき
            }
            pointY += dirY;//PointYの更新
            if (oc.boardPiece[pointX][pointY] == TurnColor) {
                if (check) {//駒の隣接色が相手色で自色にたどり着いたとき
                    return true;
                }//そうでないときは比較終了
            }else if(oc.boardPiece[pointX][pointY] == altarColor){
                check = true;
                continue;
            }//まだおいていない場所の時はその先は探索しない
            break;
        }
        return false;
    }
}
