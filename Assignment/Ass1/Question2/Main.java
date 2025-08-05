import java.util.*;

class LevelData {
    int n_i;
    List<String> Numbers;
    int a_i;

    public LevelData(int n_i, List<String> Numbers, int a_i) {
        this.n_i = n_i;
        this.Numbers = Numbers;
        this.a_i = a_i;
    }
}

public class Main {

    public static String numberConcatenate(List<String> Numbers, int a_i) {
        String First = Numbers.get(a_i - 1);
        List<String> remaining = new ArrayList<>(Numbers);
        remaining.remove(a_i - 1);

        remaining.sort((x, y) -> (y + x).compareTo(x + y));
        
        StringBuilder result = new StringBuilder(First);
        for (String num : remaining) {
            result.append(num);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int T = scanner.nextInt();
        scanner.nextLine(); 

        List<LevelData> Datas = new ArrayList<>();

        for (int i = 0; i < T; i++) {
            String[] data = scanner.nextLine().split(" ");
            int n_i = Integer.parseInt(data[0]);
            List<String> Numbers = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(data, 1, n_i + 1)));
            int a_i = Integer.parseInt(data[data.length - 1]);
            Datas.add(new LevelData(n_i, Numbers, a_i));
        }

        for (LevelData data : Datas) {
            String result = numberConcatenate(data.Numbers, data.a_i);
            System.out.println(result);
        }

        scanner.close();
    }
}