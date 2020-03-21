package deadlock;

import java.util.Random;

/**
 * @description：TODO
 */
public class LiveLock {
    static class Spoon {
        private Diner owner;

        public synchronized void use() {
            System.out.println(owner.name + " is eating");
        }

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry = true;

        public Diner(String name) {
            this.name = name;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + "让给了" + spouse.name);
                    spoon.setOwner(spouse);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + " ok");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("Husband");
        Diner wife = new Diner("wife");

        Spoon spoon = new Spoon(husband);
        new Thread(() -> husband.eatWith(spoon, wife)).start();

        new Thread(() -> wife.eatWith(spoon, husband)).start();
    }
}
