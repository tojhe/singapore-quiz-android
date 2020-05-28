package com.example.singaporequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonTrue;
    private Button buttonFalse;
    private ImageButton buttonNext;
    private ImageButton buttonPrev;
    private TextView textQuestion;
    private int currentQuestionIndex = 0;
    private Question[] questionBank = new Question[] {
            new Question(R.string.text_question_independence, false),
            new Question(R.string.text_question_language, true),
            new Question(R.string.text_question_region, true),
            new Question(R.string.text_question_society, false)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Question question = new Question(R.string.text_question_independence, false);

        buttonTrue = findViewById(R.id.buttonTrue);
        buttonFalse = findViewById(R.id.buttonFalse);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrev = findViewById(R.id.buttonPrevious);
        textQuestion = findViewById(R.id.answer_test_view);

        buttonFalse.setOnClickListener(this); //register our buttons to listen to click event
        buttonTrue.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFalse:
//                Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                break;

            case R.id.buttonTrue:
//                Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                break;

            case R.id.buttonNext:
//                Log.d("Current","Button ok");
                // go to next question
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length; // index restart after hitting length of questionBank array to avoid index out of bounds/
                updateQuestion();
                break;

            case R.id.buttonPrevious:
                if (currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                updateQuestion();
                }
                break;

            default:
                break;
        }
    }

    private void updateQuestion() {
        Log.d("Current", "onClick: " + currentQuestionIndex);
        textQuestion.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageID;

         if (userChooseCorrect == answerIsTrue) {
             toastMessageID = R.string.text_answer_correct;
         } else {
             toastMessageID = R.string.text_answer_wrong;
         }

         Toast.makeText(MainActivity.this, toastMessageID, Toast.LENGTH_SHORT).show();
    }
}
