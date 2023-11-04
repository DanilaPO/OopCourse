package ru.academits.petrushin.tree_main;

import ru.academits.petrushin.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        Integer x2 = 2;
        Integer x3 = 3;
        Integer x1 = 1;
        Integer x4 = 4;
        Integer x5 = 5;
        Integer x19 = 19;
        Integer x10 = 10;
        Integer x25 = 25;
        Integer x21 = 21;
        Integer x27 = 27;
        Integer x20 = 20;
        Integer x24 = 24;
        Integer x26 = 26;
        Integer x30 = 30;

        tree.add(x3);

// 		tree.add(x19);
//         tree.add(x10);
// 		tree.add(x25);
// 		tree.add(x21);
// 		tree.add(x27);
// 		tree.add(x20);
// 		tree.add(x24);
//         tree.add(x26);
// 		tree.add(x30);

//

// 		tree.add(x3);
// 		tree.add(x2);
// 		tree.add(x1);
// 		tree.add(x4);
        Integer x7 = 7;
        tree.add(x7);

        tree.add(x5);

        Integer x8 = 8;
        tree.add(x8);

        Integer x9 = 9;
        tree.add(9);

        tree.add(x1);

        Integer x0 = 0;
        tree.add(x0);

        tree.remove(x3);




        tree.print();

    }
}