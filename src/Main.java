import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
public class Main {
    private static volatile String[] texts = new String[100_000];
    private static AtomicInteger counter3 = new AtomicInteger(0);
    private static AtomicInteger counter4 = new AtomicInteger(0);
    private static AtomicInteger counter5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Генерация текстов
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

         Thread thread1 = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text)) {
                    int length = text.length();
                    if (length == 3) {
                        counter3.incrementAndGet();
                    } else if (length == 4) {
                        counter4.incrementAndGet();
                    } else if (length == 5) {
                        counter5.incrementAndGet();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String text : texts) {
                if (isSameLetter(text)) {
                    int length = text.length();
                    if (length == 3) {
                        counter3.incrementAndGet();
                    } else if (length == 4) {
                        counter4.incrementAndGet();
                    } else if (length == 5) {
                        counter5.incrementAndGet();
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (String text : texts) {
                if (isAscendingLetters(text)) {
                    int length = text.length();
                    if (length == 3) {
                        counter3.incrementAndGet();
                    } else if (length == 4) {
                        counter4.incrementAndGet();
                    } else if (length == 5) {
                        counter5.incrementAndGet();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Красивых слов с длиной 3: " + counter3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + counter4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + counter5.get() + " шт");
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isSameLetter(String text) {
        char firstLetter = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != firstLetter) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAscendingLetters(String text) {
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) <= text.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
