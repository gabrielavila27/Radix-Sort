/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package radixsort;

import java.util.Arrays;

/**
 *
 * @author gabri
 */
public class RadixSort {
     public static void radixSort(int[] mainArray) {
        // Encontra o maior elemento no array para determinar o número de dígitos
        int max = Arrays.stream(mainArray).max().getAsInt();

        // Realiza o counting sort para cada dígito
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(mainArray, exp);
        }
    }

    private static void countingSort(int[] mainArray, int exp) {
        int n = mainArray.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Inicializa o array de contagem
        Arrays.fill(count, 0);

        // Conta a ocorrência de cada dígito
        for (int i = 0; i < n; i++) {
            int digit = (mainArray[i] / exp) % 10;
            count[digit]++;
        }

        // Calcula as posições dos elementos no array de saída
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Preenche o array de saída na ordem correta
        for (int i = n - 1; i >= 0; i--) {
            int digit = (mainArray[i] / exp) % 10;
            output[count[digit] - 1] = mainArray[i];
            count[digit]--;
        }

        // Copia os elementos do array de saída de volta para o array original
        System.arraycopy(output, 0, mainArray, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Array original: " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("Array ordenado: " + Arrays.toString(arr));
    }
}
