
import java.util.Random;

class Tabuleiro {
    private int[][] tabuleiro;
    private boolean[][] celulasIniciais;
    private static final int TAMANHO = 9;

    public Tabuleiro() {
        this.tabuleiro = new int[TAMANHO][TAMANHO];
        this.celulasIniciais = new boolean[TAMANHO][TAMANHO];
    }

    public void gerarTabuleiroInicial() {
        // Limpar tabuleiro
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                tabuleiro[i][j] = 0;
                celulasIniciais[i][j] = false;
            }
        }

        // Gerar um tabuleiro válido completo
        gerarTabuleiroCompleto();
        
        // Remover números aleatoriamente para criar o puzzle
        removerNumerosAleatorios();
    }

    private void gerarTabuleiroCompleto() {
        Resolvedor resolvedor = new Resolvedor();
        
        // Preencher diagonal principal (setores 3x3 independentes)
        preencherDiagonal();
        
        // Resolver o resto do tabuleiro
        resolvedor.resolver(tabuleiro);
    }

    private void preencherDiagonal() {
        Random random = new Random();
        
        for (int i = 0; i < TAMANHO; i += 3) {
            preencherSetor3x3(i, i, random);
        }
    }

    private void preencherSetor3x3(int linha, int coluna, Random random) {
        boolean[] usados = new boolean[10]; // índices 1-9
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int numero;
                do {
                    numero = random.nextInt(9) + 1;
                } while (usados[numero]);
                
                usados[numero] = true;
                tabuleiro[linha + i][coluna + j] = numero;
            }
        }
    }

    private void removerNumerosAleatorios() {
        Random random = new Random();
        int numerosParaRemover = 40 + random.nextInt(10); // Remove entre 40-49 números
        
        while (numerosParaRemover > 0) {
            int linha = random.nextInt(TAMANHO);
            int coluna = random.nextInt(TAMANHO);
            
            if (tabuleiro[linha][coluna] != 0) {
                tabuleiro[linha][coluna] = 0;
                numerosParaRemover--;
            }
        }
        

        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                celulasIniciais[i][j] = (tabuleiro[i][j] != 0);
            }
        }
    }

    public void exibirTabuleiro() {
        System.out.println("\n   1 2 3   4 5 6   7 8 9");
        System.out.println("  ┌─────┬─────┬─────┐");
        
        for (int i = 0; i < TAMANHO; i++) {
            if (i == 3 || i == 6) {
                System.out.println("  ├─────┼─────┼─────┤");
            }
            
            System.out.print((i + 1) + " │");
            
            for (int j = 0; j < TAMANHO; j++) {
                if (j == 3 || j == 6) {
                    System.out.print("│");
                }
                
                if (tabuleiro[i][j] == 0) {
                    System.out.print(" ");
                } else {
                    if (celulasIniciais[i][j]) {
                        System.out.print(tabuleiro[i][j]); // Números iniciais
                    } else {
                        System.out.print("\033[1;36m" + tabuleiro[i][j] + "\033[0m"); // Números do jogador em cyan
                    }
                }
                System.out.print(" ");
            }
            
            System.out.println("│");
        }
        
        System.out.println("  └─────┴─────┴─────┘");
    }

    public boolean podeInserir(int linha, int coluna) {
        return !celulasIniciais[linha][coluna];
    }

    public void inserirNumero(int linha, int coluna, int numero) {
        if (podeInserir(linha, coluna)) {
            tabuleiro[linha][coluna] = numero;
        }
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }
}
