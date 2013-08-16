//
package hello.world;
//
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class helloActivity extends Activity implements OnClickListener 
{

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	// The usual 
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);

        // Wire up click event
        Button button = (Button)findViewById(R.id.btOk);
        button.setOnClickListener(this);
        
        // Pre populate 
        ((EditText)this.findViewById( R.id.txPlayer1 )).setText( "0.5" );
        ((EditText)this.findViewById( R.id.txPlayer2 )).setText( "4" );
    } 
    
    
    // Implement the OnClickListener callback
    public void onClick(View v) 
    {
    	// Input from user
        String sPlayer1 = ((EditText)this.findViewById( R.id.txPlayer1)).getText().toString().trim();
        String sPlayer2 = ((EditText)this.findViewById( R.id.txPlayer2)).getText().toString().trim();
        
        // Turn into doubles
        double[] players = {0,0};
        players[0] = Double.parseDouble( sPlayer1 );
        players[1] = Double.parseDouble( sPlayer2 );
        
        // Calculate 
        calculate( players );
        
        // Set the results 
        ((EditText)this.findViewById( R.id.txMustWin1 )).setText( "" + (int)players[0] );
        ((EditText)this.findViewById( R.id.txMustWin2 )).setText( "" + (int)players[1] );
    }
     
    // Calculates handicap
    private static void calculate( double[] pdaPlayers )
    {
    	// STRONGER is lower handicap
    	// See who is better rated coming in,
    	boolean bStrongerFirst =  pdaPlayers[0] < pdaPlayers[1];
    	// Holds results
    	double strong_race, weak_race;

    	if( bStrongerFirst )
    	{ 
    		// What's the race 
    		weak_race = Math.max(2, 6 - Math.floor( pdaPlayers[1] ));
    		strong_race = weak_race + Math.round( pdaPlayers[1] - pdaPlayers[0] );
    		// set the slots before returning
    		pdaPlayers[0] = strong_race;
    		pdaPlayers[1] = weak_race;
    	}
    	else
    	{ 
    		// What's the race 
    		weak_race = Math.max(2, 6 - Math.floor( pdaPlayers[0] ));
    		strong_race = weak_race + Math.round( pdaPlayers[0] - pdaPlayers[1] );
    		//  Set the slots before returning 
    		pdaPlayers[0] = weak_race;
    		pdaPlayers[1] = strong_race;
    	}
    }


}  // EOC