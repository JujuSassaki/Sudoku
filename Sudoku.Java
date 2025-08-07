
import java.util.Scanner;

public class SudokuGame {
    private Tabuleiro tabuleiro;
    private ValidadorSudoku validador;
    private Scanner scanner;
    private boolean jogoAtivo;

    public SudokuGame() {
        this.tabuleiro = new Tabuleiro();
        this.validador = new ValidadorSudoku();
        this.scanner = new Scanner(System.in);
        this.jogoAtivo = true;
    }

    public void iniciarJogo() {
        System.out.println("=== BEM-VINDO AO SUDOKU ===");
        System.out.println("Instruções:");
        System.out.println("- Digite linha (1-9), coluna (1-9) e número (1-9)");
        System.out.println("- Digite 'quit' para sair");
        System.out.println("- Digite 'resolver' para resolver automaticamente");
        System.out.println("- Digite 'novo' para novo jogo");
        
        tabuleiro.gerarTabuleiroInicial();
        
        while (jogoAtivo) {
            tabuleiro.exibirTabuleiro();
            processarJogada();
            
            if (verificarVitoria()) {
                System.out.println("\n🎉 PARABÉNS! VOCÊ VENCEU! 🎉");
                jogoAtivo = false;
            }
        }
        
        scanner.close();
    }

    private void processarJogada() {
        System.out.print("\nSua jogada (linha coluna número) ou comando: ");
        String entrada = scanner.nextLine().trim();
        
        if (entrada.equalsIgnoreCase("quit")) {
            jogoAtivo = false;
            return;
        }
        
        if (entrada.equalsIgnoreCase("resolver")) {
            resolverAutomaticamente();
            return;
        }
        
        if (entrada.equalsIgnoreCase("novo")) {
            tabuleiro.gerarTabuleiroInicial();
            return;
        }
        
        try {
            String[] partes = entrada.split(" ");
            if (partes.length != 3) {
                System.out.println("❌ Formato inválido! Use: linha coluna número");
                return;
            }
            
            int linha = Integer.parseInt(partes[0]) - 1;
            int coluna = Integer.parseInt(partes[1]) - 1;
            int numero = Integer.parseInt(partes[2]);
            
            realizarJogada(linha, coluna, numero);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor, digite apenas números!");
        }
    }

    private void realizarJogada(int linha, int coluna, int numero) {
        if (!validarEntrada(linha, coluna, numero)) {
            return;
        }
        
        if (!tabuleiro.podeInserir(linha, coluna)) {
            System.out.println("❌ Esta célula não pode ser modificada!");
            return;
        }
        
        if (validador.jogadaValida(tabuleiro.getTabuleiro(), linha, coluna, numero)) {
            tabuleiro.inserirNumero(linha, coluna, numero);
            System.out.println("✅ Jogada válida!");
        } else {
            System.out.println("❌ Jogada inválida! Este número já existe na linha, coluna ou setor 3x3.");
        }
    }

    private boolean validarEntrada(int linha, int coluna, int numero) {
        if (linha < 0 || linha > 8 || coluna < 0 || coluna > 8) {
            System.out.println("❌ Linha e coluna devem estar entre 1 e 9!");
            return false;
        }
        
        if (numero < 1 || numero > 9) {
            System.out.println("❌ Número deve estar entre 1 e 9!");
            return false;
        }
        
        return true;
    }

    private void resolverAutomaticamente() {
        System.out.println("🤖 Resolvendo automaticamente...");
        Resolvedor resolvedor = new Resolvedor();
        
        if (resolvedor.resolver(tabuleiro.getTabuleiro())) {
            System.out.println("✅ Sudoku resolvido!");
            tabuleiro.exibirTabuleiro();
        } else {
            System.out.println("❌ Não foi possível resolver este Sudoku.");
        }
    }

    private boolean verificarVitoria() {
        return validador.sudokuCompleto(tabuleiro.getTabuleiro());
    }

    public static void main(String[] args) {
        new SudokuGame().iniciarJogo();
    }
}
