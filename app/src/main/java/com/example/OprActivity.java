package com.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

public class OprActivity extends AppCompatActivity {

    public void onBackPressed() {
        Intent intent = new Intent(OprActivity.this, SelectActivity.class);
        startActivity(intent);
    }
    Map<String,String[]> map = new HashMap<String,String[]>(); // Contains triades of Questions and two answers
    Map<String,Integer> imap = new HashMap<String,Integer>(); //Links for imgs
    Set<String> sd = new HashSet<String>(); // Contains a res


    //Old

   // String[] tree = {"Это:","Животное с голоой влажной кожей","Животное с сухой кожей, покрытой чешуйками","С хвосттом, две почти одинаковые пары конечностей","Без хвоста, задние конечности крупнее, поджаты к телу","","","Это - Тритон Обыкновенный","","Вертикальный зрачок","Зрачок не вертикальный"};
    String[][][] rept = {{{"Животное:","Голая влажная кожа","Сухая кожа"},{"","",""},{"","",""}},
           {{"Есть панцирь? ","Да","Нет"},                                {"Это - Болоотная Черепаха","",""},{"","",""}},
           {{"Веки:","Неподвижные","Подвижные"},                          {"Рисунок из чередующихся"+"\n"+"черно-белых чешуек","Есть","Нет"}, {"Желтый рисунок на голове","Есть","Нет"},{"Это - Обыкновенный уж","",""}},
           {{" "," "," "},                                                {"","",""},{"Это - Водяный уж","",""}}
           };




    String[][][] zemn = {{{"Животное:","Голая влажная кожа","Сухая кожа"},{"Какое оно?","С хвостом","Без хвоста"},{"Это - Тритон обыкновенный","",""}},
                         {{" "," "," "},                                  {"Зрачки - вертикальные?","Да","Нет"},{"Это - Чессночница Обыкновенная","",""}},
                         {{" "," "," "},                                  {"Зрачок - треугольный?","Да","Нет"}, {"Это - Жерлянка Краснобрюхая","",""}},
                         {{" "," "," "},                                  {"Перепонки пратическ отсутствуют,"+"\n"+" нет зубов","Да","Нет"},{"Это - Зеленая жаба","",""}},
                         {{" "," "," "},                                  {"Рисунок на брюхе:","Есть","Нет"},{"Это - Озерная лягушка","",""}},
                         {{" "," "," "},                                  {"Это - Остромордая лягушка","",""},     {"","",""}}};
    /*

                                        Th<String,String,String>[][] zemn = {{new Th<String,String,String>().makePair("Животное:","Голая влажная кожа","Сухая кожа")},
                                         {new Th<String,String,String>().makePair("Животное:","Голая влажная кожа","Сухая кожа")}};
     */

    String[][][] trg;
    String tmp = "_";
    final int[] imglist = {R.drawable.krasn_jerl,R.drawable.obyk_chesn,R.drawable.obyk_trit,R.drawable.ostr_lyag,R.drawable.oz_lyag,R.drawable.zel_jab};
    TextView q;
    Button ans1,ans2,more;
    ImageView i;

    int f = 0;
    int s = 0;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SelectorDialog intro = new SelectorDialog();
        intro.show(getSupportFragmentManager(), "Intro");

        setContentView(R.layout.activity_opr);
        q=(TextView)findViewById(R.id.question);
        ans1 = (Button)findViewById(R.id.ans1);
        ans2 = (Button)findViewById(R.id.ans2);
        more = (Button)findViewById(R.id.button3);
        i = (ImageView)findViewById(R.id.imageView2);

        more.setVisibility(View.INVISIBLE);

        //New
        map.put("_",new String[]{"Животное:","Голая влажная кожа \n класс Земноводные(Amphibia)","Сухая кожа \n класс Пресмыкающиеся(Reptilia)" });
        map.put("_0",new String[]{"Наличие хвоста:","Есть","Нет"});
        map.put("_1",new String[]{"Есть панцирь? ","Да","Нет"});
        map.put("_00",new String[]{"Это - Тритон обыкновенный","",""});
        map.put("_01",new String[]{"Зрачки - вертикальные?","Да","Нет"});
        map.put("_10",new String[]{"Это - Болоотная Черепаха","",""});
        map.put("_11",new String[]{"Веки:","Неподвижные","Подвижные"});
        map.put("_010",new String[]{"Это - Чесночница Обыкновенная","",""});
        map.put("_011", new String[]{"Зрачок - треугольный?","Да","Нет"});
        map.put("_110", new String[]{"Рисунок из чередующихся"+"\n"+"черно-белых чешуек","Есть","Нет"});
        map.put("_111", new String[]{"Какая ящерица?","Вообще без ног","С двумя парами ног"});
        map.put("_0110", new String[]{"Это - Жерлянка Краснобрюхая","",""});
        map.put("_0111", new String[]{"Перепонки пратическ отсутствуют,"+"\n"+" нет зубов","Да","Нет"});
        map.put("_01110",new String[]{"Это - Зеленая жаба","",""});
        map.put("_01111", new String[]{"Рисунок на брюхе:","Есть","Нет"});
        map.put("_011110", new String[]{"Это - Озерная лягушка","",""});
        map.put("_011111",new String[] {"Это - Остромордая лягушка","",""});
        map.put("_1100", new String[]{"Желтый рисунок на голове","Есть","Нет"});
        map.put("_11000",new String[]{"Это - Обыкновенный уж","",""});
        map.put("_11001",new String[]{"Это - Водяный уж","",""});
        map.put("_1101", new String[]{"Черная?","Да","Нет"});
        map.put("_11010",new String[]{"Это - Гадюка Никольского","",""});
        map.put("_11011",new String[]{"Вдоль спины зигзагообразный"+"\n"+" узор из ромбов","Есть","Нет"});
        map.put("_110110",new String[]{"Это - Степная Гадюка","",""});
        map.put("_110111",new String[]{"Узор на голве","Есть","Нет"});
        map.put("_1101110",new String[]{"Это - Узорчатый Полоз","",""});
        map.put("_1101111", new String[]{"Это - Обыкновенная Медянка","",""});
        map.put("_1110", new String[]{"Это - Веретеница Ломкая","",""});
        map.put("_1111",new String[]{"Какая ящерица?","Чешуйки на брюхе"+"\n"+" расположены ёлочкой","Чешуйки на брюхе расположены"+"\n"+" параллельно"});
        map.put("_11110",new String[]{"Это - Разноцветная ящурка","",""});
        map.put("_11111",new String[]{"Хвост:","Короче тела","Длиннее тела"});
        map.put("_111110",new String[]{"Это - Прыткая Ящерица","",""});
        map.put("_111111", new String[]{"Это - Живородящая Ящерица","",""});

        imap.put("_", R.drawable._);
        imap.put("_0", R.drawable._0);
        imap.put("_10",R.drawable._10);
        imap.put("_11",R.drawable._11);
        imap.put("_11110", R.drawable._11110);
        imap.put("_11000",R.drawable._11000);
        imap.put("_11001",R.drawable._11001);
        imap.put("_11010",R.drawable._11010);
        imap.put("_110110",R.drawable._110110);
        imap.put("_1101110",R.drawable._1101110);
        imap.put("_1110",R.drawable._1110);
        imap.put("_111110",R.drawable._111110);
        imap.put("_111111",R.drawable._111111);
        imap.put("_1101111",R.drawable._1101111);
        imap.put("_011",R.drawable._011);
        imap.put("_0111",R.drawable._0111);
        imap.put("_01111",R.drawable._01111);
        imap.put("_00",R.drawable._00);
        imap.put("_011111",R.drawable._011111);
        imap.put("_011110",R.drawable._011110);
        imap.put("_010",R.drawable._010);
        imap.put("_0110",R.drawable._0110);
        imap.put("_01110",R.drawable._01110);
        imap.put("_011110",R.drawable._011110);



        sd.add("_00");
        sd.add("_10");
        sd.add("_010");
        sd.add("_0110");
        sd.add("_01110");
        sd.add("_011110");
        sd.add("_011111");
        sd.add("_11000");
        sd.add("_11001");
        sd.add("_11010");
        sd.add("_110110");
        sd.add("_1101110");
        sd.add("_1101111");
        sd.add("_1110");
        sd.add("_11110");
        sd.add("_111110");
        sd.add("_111111");

        q.setText(map.get("_")[0]);
        ans1.setText(map.get("_")[1]);
        ans2.setText(map.get("_")[2]);


        i.setImageDrawable(getResources().getDrawable(imap.get(tmp)));

        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tmp+="0";
                if(sd.contains(tmp)){
                    ans1.setVisibility(View.INVISIBLE);
                    ans2.setVisibility(View.INVISIBLE);
                    more.setVisibility(View.VISIBLE);
                    msg(map.get(tmp)[0]);
                }
                if(imap.containsKey(tmp))
                i.setImageDrawable(getResources().getDrawable(imap.get(tmp)));
                else
                    i.setImageDrawable(getResources().getDrawable(R.drawable.n));
                /*
                if(f==1&&s==0) trg=zemn;
                q.setText(trg[s][f][0]);
                ans1.setText(trg[s][f][1]);
                ans2.setText(trg[s][f][2]);
                if(((String)q.getText()).contains("Это - ")){
                    int ind  = 0;
                    if(f==2&&s==0) i.setImageDrawable(getResources().getDrawable(imglist[2]));;
                    if(f==2&&s==1) i.setImageDrawable(getResources().getDrawable(imglist[1]));
                    if(f==2&&s==2) i.setImageDrawable(getResources().getDrawable(imglist[0]));
                    if(f==2&&s==3) i.setImageDrawable(getResources().getDrawable(imglist[5]));
                    if(f==2&&s==4) i.setImageDrawable(getResources().getDrawable(imglist[4]));
                    if(f==1&&s==5) i.setImageDrawable(getResources().getDrawable(imglist[3]));
                    msg((String)q.getText());
                    ans1.setVisibility(View.INVISIBLE);
                    ans2.setVisibility(View.INVISIBLE);

                }*/

                q.setText(map.get(tmp)[0]);
                ans1.setText(map.get(tmp)[1]);
                ans2.setText(map.get(tmp)[2]);

            }
        });
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            tmp+="1";
                if(sd.contains(tmp)){
                    ans1.setVisibility(View.INVISIBLE);
                    ans2.setVisibility(View.INVISIBLE);
                    more.setVisibility(View.VISIBLE);
                    msg(map.get(tmp)[0]);
                }
                if(imap.containsKey(tmp))
                    i.setImageDrawable(getResources().getDrawable(imap.get(tmp)));
                else
                    i.setImageDrawable(getResources().getDrawable(R.drawable.n));
                /*if(f==0&&s==1) trg=rept;
                q.setText(trg[s][f][0]);
                ans1.setText(trg[s][f][1]);
                ans2.setText(trg[s][f][2]);
                if(((String)q.getText()).contains("Это - ")){
                    int ind  = 0;
                    if(f==2&&s==0) i.setImageDrawable(getResources().getDrawable(imglist[2]));;
                    if(f==2&&s==1) i.setImageDrawable(getResources().getDrawable(imglist[1]));
                    if(f==2&&s==2) i.setImageDrawable(getResources().getDrawable(imglist[0]));
                    if(f==2&&s==3) i.setImageDrawable(getResources().getDrawable(imglist[5]));
                    if(f==2&&s==4) i.setImageDrawable(getResources().getDrawable(imglist[4]));
                    if(f==1&&s==5) i.setImageDrawable(getResources().getDrawable(imglist[3]));
                    msg((String)q.getText());
                    ans1.setVisibility(View.INVISIBLE);
                    ans2.setVisibility(View.INVISIBLE);
                }
*/
                q.setText(map.get(tmp)[0]);
                ans1.setText(map.get(tmp)[1]);
                ans2.setText(map.get(tmp)[2]);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OprActivity.this, MoreActivity.class);
                intent.putExtra("EXTRA", q.getText());
                startActivity(intent);
            }
        });

    }
    public void msg(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);

            builder.setPositiveButton("Вернуться в меню", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                    // start_of_game();
                    Intent intent = new Intent(OprActivity.this, SelectActivity.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Повторить", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                    Intent intent = new Intent(OprActivity.this, OprActivity.class);
                    startActivity(intent);
                    f=0;
                    s=0;
                }
            });
        builder.setNeutralButton("Остаться", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {



            }
        });


        AlertDialog alert = builder.create();
        alert.show();
    }

}
/*class Th<F,S,T>{
    public F quest;
    public S first;
    public T second;
    public Th(F f,S s,T t){
        quest = f;
        first = s;
        second = t;
    }
    public Th(){

    }
    public Th<F,S,T> makePair(F f,S s, T t){
        return new Th<F,S,T>(f,s,t);
    }

}*/
