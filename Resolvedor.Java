
class Resolvedor {
    private static final int TAMANHO = 9;
    
    public boolean resolver(int[][] tabuleiro) {
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                if (tabuleiro[linha][coluna] == 0) {
                    for (int numero = 1; numero <= 9; numero++) {
                        if (jogadaSegura(tabuleiro, linha, coluna, numero)) {
                            tabuleiro[linha][coluna] = numero;
                            
                            if (resolver(tabuleiro)) {
                                return true;
                            }
                            
                            // Backtrack
                            tabuleiro[linha][coluna] = 0;
                        }
                    }
                    return false; // Não foi possível resolver
                }
            }
        }
        return true; // Tabuleiro completo
    }
    
    private boolean jogadaSegura(int[][] tabuleiro, int linha, int coluna, int numero) {
        // Verificar linha
        for (int j = 0; j < TAMANHO; j++) {
            if (tabuleiro[linha][j] == numero) {
                return false;
            }
        }
        
        // Verificar coluna
        for (int i = 0; i < TAMANHO; i++) {
            if (tabuleiro[i][coluna] == numero) {
                return false;
            }
        }
        
        // Verificar setor 3x3
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;
        
        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                if (tabuleiro[i][j] == numero) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
