/**
 * Created by Administrator on 2017-02-22.
 */
public class Test {
    public static void main(String[] args) {
        PlayGround pg=new PlayGround();
        MyThread t1=new MyThread(pg);
        MyThread t2=new MyThread(pg);
        t1.start();
        t2.start();
        System.out.println(pg.rounds);

    }
}

class PlayGround{
    int rounds=0;
    public void runRound(){
        for (int i=0;i<10;i++) {
            rounds++;
            System.out.println(Thread.currentThread().getName()+"------"+rounds);

        }
    }


}
class MyThread implements Runnable{
    PlayGround pg=null;
    MyThread(PlayGround pg){
        this.pg=pg;

    }


    @Override
    public void run() {
        pg.runRound();
    }
    public void start(){
        Thread t=new Thread(this);
        t.start();
    }
}