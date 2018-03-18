package com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.app.*;
import java.text.DecimalFormat;
import java.util.*;


public class MainActivity extends AppCompatActivity {
    int nl;
    final int NUM_OF_SPRITES = 5;
    int level;
    final int LEVEL_NUM = 4;
    ArrayList<Pair<String,String>> btn_txt = new ArrayList<>();;
    final Context context = this;
    int index,correct,bad;
    TextView info;
    ImageView img;
    long start,end;
    /*final int[] imglist = {R.drawable.chesn_obuk, R.drawable.gad_nick, R.drawable.gad_stepn, R.drawable.jash_prutk, R.drawable.jashk_razn, R.drawable.jerl_obuk, R.drawable.lyag_ozer, R.drawable.lyag_trav, R.drawable.med_ob, R.drawable.ser_jab, R.drawable.ver_lomk, R.drawable.yj_obuk,R.drawable.bol_cherep,R.drawable.lyag_ostromord,R.drawable.vod_yj,R.drawable.poloz_yzor};
      final int[][] type = {{0,1,1,1,1,0,0,0,1,0,1,1,1,0,1,1},{0,1,1,1,1,0,0,0,1,0,1,1,1,0,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0}};
      final String[] arrInfo = {"Чесночница Обыкновенна","Гадюка Никольского","Гадюка Степная","Ящерица Прыткая","Ящурка Разноцветная","Жерлянка Краснобрюхая","Лягушка Озерная","Лягушка Травяная","Медянка Обыкновенная","Жаба Серая","Веретеница Ломкая","Уж Обыкновенная","Черепаха Болотная","Лягушка Отсромордая","Уж Водяной","Полоз Узорчатный"};
    */
    final int[] imglist = {R.drawable.krasn_jerl,R.drawable.obyk_chesn,R.drawable.obyk_trit,R.drawable.ostr_lyag,R.drawable.oz_lyag,R.drawable.zel_jab};
    final int[][] type = {{0,0,0,0,0,0},{0,0,0,0,0,0},{1,1,1,1,1,1},{1,1,1,0,0,1}};
    final String[] arrInfo = {"Жерлянка Краснобрюхая","Чесночница Обыкновенная","Тритон Обыкновенный","Лягушка Остромордая","Лягушка Озерная","Жаба Зеленая"};
    int tmp[] = {0,1,2,3,4};
    Button next,back;

    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, SelectActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<arrInfo.length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<NUM_OF_SPRITES; i++) {
           tmp[i]=list.get(i);
        }*/
        level  = 1;
        btn_txt.add(new Pair<String,String>().makePair("Пресмыкающееся (Рептилия)","Земноводное (Амфибия)"));
        btn_txt.add(btn_txt.get(0));
        btn_txt.add(new Pair<String,String>().makePair("Обитает в Саратовской Области","\"Не Обитает в Саратовской Области\""));
        btn_txt.add(new Pair<String,String>().makePair("Ядовитое","Неядовитое"));
        tmp=genUniq(arrInfo.length,NUM_OF_SPRITES);
        index = -1;
        correct = 0;
        bad = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);
        next = findViewById(R.id.button);
        back = findViewById(R.id.button2);
        info = findViewById(R.id.textView);
        img.setImageDrawable(getResources().getDrawable(imglist[tmp[0]]));
        info.setText(arrInfo[tmp[0]]);
        msg(1,"Добро пожаловать в игру \"Лягушки-Змеи\"."+"После того, как вы начнете отвечать, пойдёт таймер."+"\n"+"Приятной игры!");
        start_of_game();
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(index==-1) {
                    //start_of_game();
                    start = System.currentTimeMillis();

                    index++;
                }
                if(next.getText().equals("Пресмыкающееся (Рептилия)"))
                    level=1;
                else if(next.getText().equals("Обитает в Саратовской Области"))
                    level = 3;
                else if(next.getText().equals("Ядовитое"))
                    level=4;
                if(type[level-1][tmp[index]]==1) correct++;
                else bad++;
                if(index==NUM_OF_SPRITES-1) {
                    index=0;
                    end = System.currentTimeMillis()-start;
                    end_of_game(div(end,1000),correct,bad);
                }else {

                    index++;
                }

                img.setImageDrawable(getResources().getDrawable(imglist[tmp[index]]));
                info.setText(arrInfo[tmp[index]]);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(index==-1) {
                    //start_of_game();
                    start = System.currentTimeMillis();
                    index++;
                }
                if(next.getText().equals("Пресмыкающееся (Рептилия)"))
                    level=1;
                else if(next.getText().equals("Обитает в Саратовской Области"))
                    level = 3;
                else if(next.getText().equals("Ядовитое"))
                    level=4;
                //msg(0,Integer.toString(level));
                if(type[level-1][tmp[index]]==0) correct++;
                else bad++;
                if(index==NUM_OF_SPRITES-1) {
                    index=0;
                    end = System.currentTimeMillis()-start;
                    end_of_game(div(end,1000),correct,bad);

                }else {

                    index++;
                }
                img.setImageDrawable(getResources().getDrawable(imglist[tmp[index]]));
                info.setText(arrInfo[tmp[index]]);
            }
        });

    }

    public void start_of_game(){
        level = select_lvl();
        /*next.setText(btn_txt.get(level-1).first);
        back.setText(btn_txt.get(level-1).second);*/
        if(level==2)
            info.setText("");
        index = 0;
        start = System.currentTimeMillis();
        correct=0;
        bad=0;
        end = 0;
        tmp=genUniq(arrInfo.length,NUM_OF_SPRITES);
       /* HashSet<Integer> set = new HashSet<>();
        for(int i = 0;i<NUM_OF_SPRITES;++i)
            set.add(new Random().nextInt(NUM_OF_SPRITES-1));
            tmp=set.toArray(new Integer[set.size()]);*/
       /* ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<arrInfo.length; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<NUM_OF_SPRITES; i++) {
            tmp[i]=list.get(i);
        }*/
    }
    boolean taped = false;

    public  int select_lvl(){
        nl=4;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Выберите уровень сложности. Чтобы выбрать первый уровень, нажмите на пустое пространство");
        builder.setPositiveButton("2 уровень", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                nl=2;
                next.setText(btn_txt.get(nl-1).first);
                back.setText(btn_txt.get(nl-1).second);
                //info.setText("");
                info.setVisibility(View.INVISIBLE);
                taped = true;
            }
        });
        builder.setNegativeButton("3 уровень", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //chngLvl(3);
                nl=3;
                next.setText(btn_txt.get(nl-1).first);
                back.setText(btn_txt.get(nl-1).second);
                taped = true;

            }
        });
        builder.setNeutralButton("4 уровень", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                nl=4;
                next.setText(btn_txt.get(nl-1).first);
                back.setText(btn_txt.get(nl-1).second);
                taped = true;


            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if(!taped){
                    next.setText(btn_txt.get(0).first);
                    back.setText(btn_txt.get(0).second);
                }
            }
        });
        //msg(0,Integer.toString(nl));
        level=nl;


        // msg(0,Integer.toString(level));
        AlertDialog alert = builder.create();
        alert.show();
        return nl;
    }
    public void end_of_game(double time, int score, int lose){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(lose == 0)
            builder.setMessage("Игра окончена!"+"\n"+"У вас "+score+" правильных ответов, и вы справились за "+time+" секунд. "+"\n"+"Просто замечательно!");
            else
        if(score>1)
        builder.setMessage("Игра оканчена!"+"\n"+"У вас "+score+" правильных ответов, и вы справились за "+time+" секунд. "+"\n"+"Неплохо!");
        else
            builder.setMessage("Игра оканчена!"+"\n"+"У вас "+score+" правильных ответ, и вы справились за "+time+" секунд. "+"\n"+"Можно и лучше!");
        builder.setPositiveButton("Повторить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                index = 0;
                correct=0;
                bad=0;
                info.setVisibility(View.VISIBLE);
                start_of_game();
            }
        });
        builder.setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void msg(int num, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);
        if(num>=1)
        builder.setPositiveButton("Начать!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
              // start_of_game();
            }
        });
        if(num>=2)
            builder.setNegativeButton("Повторить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things

                }
            });
        if(num>=3)
            builder.setNeutralButton("Повторить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things

                }
            });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public double div(long a,long b){
        double as = Double.valueOf(a);
        double bs = Double.valueOf(b);
        double res = as/bs;
        DecimalFormat df = new DecimalFormat("#.000");
        df.format(res);
        return res;

    }
    public int[] genUniq(int border,int length){
        int res[] = new int[length];
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<border; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<length; i++) {
            res[i]=list.get(i);
        }
        return res;
    }
    public void chngLvl(int nl){
        level=nl;
    }
}
    class Pair<F,S>{
        public F first;
        public S second;
        public Pair(F f,S s){
            first = f;
            second = s;
        }
        public Pair(){

        }
        public Pair<F,S> makePair(F f,S s){
            return new Pair<F,S>(f,s);
        }

        }
   /* public void Select(){
        LayoutInflater li = LayoutInflater.from(context);

        View promptsView = li.inflate(R.layout.activity_main, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setView(promptsView);

// set dialog message

        alertDialogBuilder.setTitle("My Dialog..");

// create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        final Spinner mSpinner= (Spinner) promptsView
                .findViewById(R.id.mySpinner);


// reference UI elements from my_dialog_layout in similar fashion

        mSpinner.setOnItemSelectedListener(new OnSpinnerItemClicked());

// show it
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
            }


}
public class OnSpinnerItemClicked implements OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {
        Toast.makeText(parent.getContext(), "Clicked : " +
                parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }
}*/
