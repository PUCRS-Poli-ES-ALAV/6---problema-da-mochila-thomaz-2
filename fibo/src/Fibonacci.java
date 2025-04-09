import java.math.BigInteger;

public class Fibonacci {
    static class ExecutionMetrics {
        int iterations;
        int instructions;
        long startTime;
        long endTime;
        
        void start() {
            startTime = System.nanoTime();
        }
        
        void stop() {
            endTime = System.nanoTime();
        }
        
        double getTimeMs() {
            return (endTime - startTime) / 1e6;
        }
        
        void printMetrics(String methodName) {
            System.out.println(methodName + " - Iterações: " + iterations + 
                             ", Instruções: ~" + instructions + 
                             ", Tempo: " + getTimeMs() + " ms");
        }
    }

    // FIBO-REC com medição
    public static long FIBO_REC(int n, ExecutionMetrics metrics) {
        metrics.iterations++;
        metrics.instructions += 3; // if, comparação e possível return
        
        if (n <= 1) return n;
        
        metrics.instructions += 3; // chamadas recursivas e adição
        return FIBO_REC(n - 1, metrics) + FIBO_REC(n - 2, metrics);
    }

    // FIBO com medição
    public static long FIBO(int n, ExecutionMetrics metrics) {
        metrics.iterations++;
        metrics.instructions += 3;
        
        if (n <= 1) return n;
        
        long[] f = new long[n + 1];
        f[0] = 0;
        f[1] = 1;
        metrics.instructions += 4; // atribuições e criação do array
        
        for (int i = 2; i <= n; i++) {
            metrics.iterations++;
            f[i] = f[i - 1] + f[i - 2];
            metrics.instructions += 5; // acesso array, adição, atribuição
        }
        
        metrics.instructions += 1; // return
        return f[n];
    }

    // MEMOIZED-FIBO com medição
    private static BigInteger[] memo;
    
    public static BigInteger MEMOIZED_FIBO(int n, ExecutionMetrics metrics) {
        memo = new BigInteger[n + 1];
        metrics.instructions += 2; // criação array e inicialização i
        
        for (int i = 0; i <= n; i++) {
            metrics.iterations++;
            if (i <= 1) {
                memo[i] = BigInteger.valueOf(i);
                metrics.instructions += 3;
            } else {
                memo[i] = memo[i - 1].add(memo[i - 2]);
                metrics.instructions += 5;
            }
        }
        metrics.instructions += 1;
        return memo[n];
    }

    public static void main(String[] args) {
        int[] pequenos = {4, 8, 16, 32};
        int[] grandes = {128, 1000, 10000};
        
        System.out.println("Testando com valores pequenos:");
        for (int n : pequenos) {
            System.out.println("\nPara n = " + n + ":");
            
            ExecutionMetrics metrics = new ExecutionMetrics();
            metrics.start();
            long recResult = FIBO_REC(n, metrics);
            metrics.stop();
            System.out.println("FIBO-REC: " + recResult);
            metrics.printMetrics("FIBO-REC");
            
            metrics = new ExecutionMetrics();
            metrics.start();
            long iterResult = FIBO(n, metrics);
            metrics.stop();
            System.out.println("FIBO: " + iterResult);
            metrics.printMetrics("FIBO");
            
            metrics = new ExecutionMetrics();
            metrics.start();
            BigInteger memoResult = MEMOIZED_FIBO(n, metrics);
            metrics.stop();
            System.out.println("MEMOIZED-FIBO: " + memoResult);
            metrics.printMetrics("MEMOIZED-FIBO");
        }
        
        System.out.println("\nTestando com valores grandes:");
        for (int n : grandes) {
            System.out.println("\nPara n = " + n + ":");
            
            ExecutionMetrics metrics = new ExecutionMetrics();
            metrics.start();
            BigInteger resultado = MEMOIZED_FIBO(n, metrics);
            metrics.stop();
            
            String strResult = resultado.toString();
            int len = strResult.length();
            String preview = len <= 50 ? strResult : strResult.substring(0, 20) + "..." + strResult.substring(len - 20);
            
            System.out.println("MEMOIZED-FIBO: " + preview);
            System.out.println("Dígitos: " + len);
            metrics.printMetrics("MEMOIZED-FIBO");
        }
    }
}