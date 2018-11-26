package project.capstone.sw.yahaja;

import android.content.Context;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class RankItemView extends ConstraintLayout {

    TextView rankNumView;
    TextView idView;
    TextView pointView;
    ImageView userImageView;

    public RankItemView(Context context) {
        super(context);
        init(context);
    }

    public RankItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rank_item, this, true);

        rankNumView = (TextView) findViewById(R.id.rankNum);
        idView = (TextView) findViewById(R.id.userId);
        pointView = (TextView) findViewById(R.id.rankPoint);
        userImageView = (ImageView) findViewById(R.id.userImage);
    }

    public void setRankNum(String rankNum){
        rankNumView.setText(rankNum);
    }
    public void setIDView(String id){
        idView.setText(id);
    }
    public void setPointView(String point){
        pointView.setText(point);
    }
    public void setUserImageView(Uri uri){
        userImageView.setImageURI(uri);
    }
}
