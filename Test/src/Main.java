import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        Thread thread1 = new Thread(new AddList(list));

        Thread thread2 = new Thread(new AddList(list));

        thread1.run();
        thread2.run();

        System.out.print(list);
    }
}

class AddList implements Runnable {
    List<Integer> list;

    public AddList( List<Integer> list) {
        this.list = list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        Integer j = 0;
        for(int i = 0; i <= 100; ++i) {
            list.add(i, j);

            j++;

            if(list.size() == 100) {
                return;
            }
        }
    }
}
