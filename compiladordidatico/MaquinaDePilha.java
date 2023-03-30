import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class MaquinaDePilha {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java MaquinaPilha arquivoDeEntrada");
            System.exit(1);
        }

        String arquivo = args[0];

        Stack<Integer> pilha = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(" ");
                String instrucao = partes[0];
                if (instrucao.equals("PUSH")) {
                    int valor = Integer.parseInt(partes[1]);
                    pilha.push(valor);
                } else if (instrucao.equals("SUM")) {
                    int arg2 = pilha.pop();
                    int arg1 = pilha.pop();
                    int resultado = arg1 + arg2;
                    pilha.push(resultado);
                } else if (instrucao.equals("SUB")) {
                    int arg2 = pilha.pop();
                    int arg1 = pilha.pop();
                    int resultado = arg1 - arg2;
                    pilha.push(resultado);
                } else if (instrucao.equals("MULT")) {
                    int arg2 = pilha.pop();
                    int arg1 = pilha.pop();
                    int resultado = arg1 * arg2;
                    pilha.push(resultado);
                } else if (instrucao.equals("DIV")) {
                    int arg2 = pilha.pop();
                    int arg1 = pilha.pop();
                    int resultado = arg1 / arg2;
                    pilha.push(resultado);
                } else if (instrucao.equals("PRINT")) {
                    int resultado = pilha.pop();
                    System.out.println(resultado);
                } else {
                    System.err.println("Instrução inválida: " + instrucao);
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Valor inválido na instrução PUSH: " + e.getMessage());
            System.exit(1);
        } catch (RuntimeException e) {
            System.err.println("Erro de execução: " + e.getMessage());
            System.exit(1);
        }

        if (!pilha.empty()) {
            System.err.println("Erro de execução: pilha não vazia");
            System.exit(1);
        }
    }
}
