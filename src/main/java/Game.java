import model.Matrix;

public class Game {
    public static void main(String[] args) {
        System.out.println("It's working!");
        Matrix<Integer> m = new Matrix<>(2, 2, 0);
        m.print();
        System.out.println();
        m.setValue(0, 0, 2);
        m.setValue(0, 1, 2);
        m.setValue(1, 1, 3);
        m.print();
        System.out.println();
        System.out.println(m.getValue(0, 1));
    }
}
