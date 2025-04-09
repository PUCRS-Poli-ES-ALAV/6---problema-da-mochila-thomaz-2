public class KnapsackRecursive {
    private static int iterations = 0;
    
    public static int knapsackRecursive(int capacity, int[] weights, int[] values, int n) {
        iterations++;
        // Caso base
        if (n == 0 || capacity == 0) {
            return 0;
        }
        
        // Se o peso do n-ésimo item é maior que a capacidade, não pode ser incluído
        if (weights[n-1] > capacity) {
            return knapsackRecursive(capacity, weights, values, n-1);
        }
        // Retorna o máximo entre incluir ou não incluir o item
        else {
            return Math.max(
                values[n-1] + knapsackRecursive(capacity - weights[n-1], weights, values, n-1),
                knapsackRecursive(capacity, weights, values, n-1)
            );
        }
    }
    
    public static void main(String[] args) {
        // Teste 1
        int capacity1 = 165;
        int[] weights1 = {23, 31, 29, 44, 53, 38, 63, 85, 89, 82};
        int[] values1 = {92, 57, 49, 68, 60, 43, 67, 84, 87, 72};
        int n1 = values1.length;
        
        iterations = 0;
        long startTime = System.nanoTime();
        int maxValue1 = knapsackRecursive(capacity1, weights1, values1, n1);
        long endTime = System.nanoTime();
        
        System.out.println("Teste 1 - Solução Recursiva");
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
        int maxValue2 = knapsackRecursive(capacity2, weights2, values2, n2);
        endTime = System.nanoTime();
        
        System.out.println("\nTeste 2 - Solução Recursiva");
        System.out.println("Valor máximo: " + maxValue2);
        System.out.println("Número de iterações: " + iterations);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ns");
    }
}