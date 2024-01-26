package edu.harrisburgu.geoquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

////information for helping hw is at 2:07ish in the recording

public class geoquiz extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;

    private TextView mQuestionTextView;
    private static final String EXTRA_MESSAGE = "edu.harrisburgu.geoquiz.cheatmessage";

    private static final int REQUEST_CODE_CHEAT = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_atlantic, false),
            new Question(R.string.question_taiwan, true),
    };
    private int mCurrentIndex = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i  = CheatActivity.newIntent(geoquiz.this, mQuestionBank[mCurrentIndex].ismAnswerTrue());
                String message = "hello from mainactivity";
                i.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_View);
        updateQuestion();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHEAT){
            //Log.d(requestCode);
            if (null != data && data.getStringExtra("result") != null){
                int messageResId = R.string.judgment_toast;
                Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;
    if (userPressedTrue == answerIsTrue){
        messageResId = R.string.correct_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    } else{
        messageResId = R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    }
}