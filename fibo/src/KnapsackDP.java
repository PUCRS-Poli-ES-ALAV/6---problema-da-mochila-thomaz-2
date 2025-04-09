public class KnapsackDP {
    private static int iterations = 0;
    
    public static int knapsackDP(int capacity, int[] weights, int[] values, int n) {
        int[][] dp = new int[n+1][capacity+1];
        
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                iterations++;
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                else if (weights[i-1] <= w) {
                    dp[i][w] = Math.max(
                        values[i-1] + dp[i-1][w - weights[i-1]],
                        dp[i-1][w]
                    );
                }
                else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    public static void main(String[] args) {
        // Teste 1
        int capacity1 = 165;
        int[] weights1 = {23, 31, 29, 44, 53, 38, 63, 85, 89, 82};
        int[] values1 = {92, 57, 49, 68, 60, 43, 67, 84, 87, 72};
        int n1 = values1.length;
        
        iterations = 0;
        long startTime = System.nanoTime();
        int maxValue1 = knapsackDP(capacity1, weights1, values1, n1);
        long endTime = System.nanoTime();
        
        System.out.println("Teste 1 - Programação Dinâmica");
        System.out.println("Valor máximo: " + maxValue1);
        System.out.println("Número de iterações: " + iterations);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
        
        // Teste 2
        int capacity2 = 190;
        int[] weights2 = {56, 59, 80, 64, 75, 17};
        int[] values2 = {50, 50, 64, 46, 50, 5};
        int n2 = values2.length;
        
        iterations = 0;
        startTime = System.nanoTime();
        int maxValue2 = knapsackDP(capacity2, weights2, values2, n2);
        endTime = System.nanoTime();
        
        System.out.println("\nTeste 2 - Programação Dinâmica");
        System.out.println("Valor máximo: " + maxValue2);
        System.out.println("Número de iterações: " + iterations);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
    }
}