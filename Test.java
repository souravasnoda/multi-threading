public class Test {
    public static void main(String[] args) {
        // World world = new World();
        // world.start();
        world2 w2 = new world2();
        Thread t1 = new Thread(w2);
        t1.start();
        for(;;){
            System.out.println("Hello");
        }
    }
}
