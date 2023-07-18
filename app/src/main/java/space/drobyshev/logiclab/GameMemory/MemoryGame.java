package space.drobyshev.logiclab.GameMemory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import space.drobyshev.logiclab.DBHelper;
import space.drobyshev.logiclab.MenuActivity;
import space.drobyshev.logiclab.R;

public class MemoryGame extends AppCompatActivity {


    DBHelper DB;

    ImageView iv_11, iv_12, iv_13, iv_14, iv_15, iv_16, iv_17, iv_18, iv_19, iv_20, iv_21, iv_22;
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    int image101, image102, image103, image104, image105, image106, image201, image202, image203, image204, image205, image206;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_memory);

        DB = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();

        List<Boolean> gameResults =DB.getAllGameResultsByEmail(email);

        DB.updateUserGameProgress(email,true, gameResults.get(1),gameResults.get(2),gameResults.get(3),gameResults.get(4));

        iv_11 = findViewById(R.id.iv_11);
        iv_12 = findViewById(R.id.iv_12);
        iv_13 = findViewById(R.id.iv_13);
        iv_14 = findViewById(R.id.iv_14);
        iv_15 = findViewById(R.id.iv_15);
        iv_16 = findViewById(R.id.iv_16);
        iv_17 = findViewById(R.id.iv_17);
        iv_18 = findViewById(R.id.iv_18);
        iv_19 = findViewById(R.id.iv_19);
        iv_20 = findViewById(R.id.iv_20);
        iv_21 = findViewById(R.id.iv_21);
        iv_22 = findViewById(R.id.iv_22);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_15.setTag("4");
        iv_16.setTag("5");
        iv_17.setTag("6");
        iv_18.setTag("7");
        iv_19.setTag("8");
        iv_20.setTag("9");
        iv_21.setTag("10");
        iv_22.setTag("11");

        frontOfCardsResources();

        Collections.shuffle(Arrays.asList(cardsArray));

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_11, theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_12, theCard);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_13, theCard);
            }
        });

        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_14, theCard);
            }
        });

        iv_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_15, theCard);
            }
        });

        iv_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_16, theCard);
            }
        });

        iv_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_17, theCard);
            }
        });

        iv_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_18, theCard);
            }
        });

        iv_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_19, theCard);
            }
        });

        iv_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_20, theCard);
            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_21, theCard);
            }
        });

        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(iv_22, theCard);
            }
        });
    }

    private void doStuff(ImageView iv, int card) {
        if (cardsArray[card] == 101) {
            iv.setImageResource(image101);
        } else if (cardsArray[card] == 102) {
            iv.setImageResource(image102);
        } else if (cardsArray[card] == 103) {
            iv.setImageResource(image103);
        } else if (cardsArray[card] == 104) {
            iv.setImageResource(image104);
        } else if (cardsArray[card] == 105) {
            iv.setImageResource(image105);
        } else if (cardsArray[card] == 106) {
            iv.setImageResource(image106);
        } else if (cardsArray[card] == 201) {
            iv.setImageResource(image201);
        } else if (cardsArray[card] == 202) {
            iv.setImageResource(image202);
        } else if (cardsArray[card] == 203) {
            iv.setImageResource(image203);
        } else if (cardsArray[card] == 204) {
            iv.setImageResource(image204);
        } else if (cardsArray[card] == 205) {
            iv.setImageResource(image205);
        } else if (cardsArray[card] == 206) {
            iv.setImageResource(image206);
        }

        if (cardNumber == 1) {
            firstCard = cardsArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(true);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_15.setEnabled(false);
            iv_16.setEnabled(false);
            iv_17.setEnabled(false);
            iv_18.setEnabled(false);
            iv_19.setEnabled(false);
            iv_20.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    calculate();
                }
            }, 1000);
        }
    }

    private void calculate() {
        if (firstCard == secondCard) {
            if (clickedFirst == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_15.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_16.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv_17.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv_18.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 8) {
                iv_19.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv_20.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv_22.setVisibility(View.INVISIBLE);
            }

            if (clickedSecond == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 4) {
                iv_15.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 5) {
                iv_16.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 6) {
                iv_17.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 7) {
                iv_18.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 8) {
                iv_19.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 9) {
                iv_20.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 10) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 11) {
                iv_22.setVisibility(View.INVISIBLE);
            }
        } else {
            iv_11.setImageResource(R.drawable.paw);
            iv_12.setImageResource(R.drawable.paw);
            iv_13.setImageResource(R.drawable.paw);
            iv_14.setImageResource(R.drawable.paw);
            iv_15.setImageResource(R.drawable.paw);
            iv_16.setImageResource(R.drawable.paw);
            iv_17.setImageResource(R.drawable.paw);
            iv_18.setImageResource(R.drawable.paw);
            iv_19.setImageResource(R.drawable.paw);
            iv_20.setImageResource(R.drawable.paw);
            iv_21.setImageResource(R.drawable.paw);
            iv_22.setImageResource(R.drawable.paw);

        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_15.setEnabled(true);
        iv_16.setEnabled(true);
        iv_17.setEnabled(true);
        iv_18.setEnabled(true);
        iv_19.setEnabled(true);
        iv_20.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);

        checkEnd();
    }

    private void checkEnd() {
        if (iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&
                iv_15.getVisibility() == View.INVISIBLE &&
                iv_16.getVisibility() == View.INVISIBLE &&
                iv_17.getVisibility() == View.INVISIBLE &&
                iv_18.getVisibility() == View.INVISIBLE &&
                iv_19.getVisibility() == View.INVISIBLE &&
                iv_20.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MemoryGame.this);
            alertDialogBuilder.setMessage("Вы выиграли!").setCancelable(false).setPositiveButton("Хотите еще?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getApplicationContext(), MemoryGame.class);
                    startActivity(intent);
                    finish();
                }
            }).setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void frontOfCardsResources() {
        image101 = R.drawable.bull;
        image102 = R.drawable.chick;
        image103 = R.drawable.fox;
        image104 = R.drawable.crab;
        image105 = R.drawable.hedgehog;
        image106 = R.drawable.hippopotamus;

        image201 = R.drawable.bull1;
        image202 = R.drawable.chick1;
        image203 = R.drawable.fox1;
        image204 = R.drawable.crab1;
        image205 = R.drawable.hedgehog1;
        image206 = R.drawable.hippopotamus1;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.get("email").toString();
        intent.putExtra("email","" + email);
        startActivity(intent);
        super.onBackPressed();
    }
}
