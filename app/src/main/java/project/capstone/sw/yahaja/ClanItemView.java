package project.capstone.sw.yahaja;

import android.content.Context;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ClanItemView extends ConstraintLayout implements View.OnClickListener {

    TextView clanNameView;
    TextView clanTypeView;
    TextView clanPointView;
    ImageView userImageView;



    public ClanItemView(Context context) {
        super(context);
        init(context);
    }

    public ClanItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context){
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rank_item2, this, true);

        ImageButton imageButton = (ImageButton) findViewById(R.id.clan_join);

        clanNameView = (TextView) findViewById(R.id.clan_name);
        clanTypeView = (TextView) findViewById(R.id.clan_type);
        clanPointView = (TextView) findViewById(R.id.clan_point);
        userImageView = (ImageView) findViewById(R.id.userImage);

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast t = new Toast(context.getApplicationContext());
                t.setDuration(Toast.LENGTH_LONG);

              View viewclan = inflater.inflate(R.layout.clan_toast, null);
              t.setView(viewclan);
              t.show();
            }
        });
    }

    public void setRankNum(String rankNum){
        clanNameView.setText(rankNum);
    }
    public void setIDView(String id){
        clanTypeView.setText(id);
    }
    public void setPointView(String point){
        clanPointView.setText(point);
    }
    public void setUserImageView(Uri uri){
        userImageView.setImageURI(uri);
    }


    @Override
    public void onClick(View view) {

    }
}
