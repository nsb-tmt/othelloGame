package com.example.othellover2;

import android.graphics.Color;
import android.widget.ImageView;

public class PieceManager {

    ImageView piece;
    int[] imageId = {R.drawable.reversi_black,R.drawable.reversi_white};
    int[] point;//自身のX座標、Y座標を保存している
    PieceManager(ImageView piece,OthelloController oc,int[] point){
        this.piece = piece;
        this.point = point;
        this.piece.setOnClickListener(v->oc.putPiece(point));
    }
    void setMyColor(int i){//数字に応じて自身の色を変更する
        switch (i){
            case 0:
                this.piece.setImageDrawable(null);
                break;
            case 1:
                this.piece.setImageResource(R.drawable.reversi_black);
                break;
            case 2:
                this.piece.setImageResource(R.drawable.reversi_white);
                break;
        }
    }
    void setCanPutColor(boolean canPut){//置けるかどうかで背景色を変更
        if (canPut){
            this.piece.setBackgroundColor(Color.parseColor("#E6B46A"));
        }else{
            this.piece.setBackgroundColor(Color.parseColor("#4CAF50"));
        }
    }

}
