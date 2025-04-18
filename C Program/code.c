#include <stdio.h>
#include <string.h>

void bitStuffing(char *input, char *stuffed) {
    int i, j = 0, count = 0;
    for (i = 0; input[i] != '\0'; i++) {
        stuffed[j++] = input[i];
        if (input[i] == '1') {
            count++;
            if (count == 5) {
                stuffed[j++] = '0';
                count = 0;
            }
        } else {
            count = 0;
        }
    }
    stuffed[j] = '\0';
}

void bitDeStuffing(char *stuffed, char *original) {
    int i, j = 0, count = 0;
    for (i = 0; stuffed[i] != '\0'; i++) {
        original[j++] = stuffed[i];
        if (stuffed[i] == '1') {
            count++;
            if (count == 5 && stuffed[i + 1] == '0') {
                i++;
                count = 0;
            }
        } else {
            count = 0;
        }
    }
    original[j] = '\0';
}

int main() {
    char input[100], stuffed[150], original[100];
    
    printf("Enter the bit stream: ");
    scanf("%s", input);
    
    bitStuffing(input, stuffed);
    printf("Stuffed bit stream: %s\n", stuffed);
    
    bitDeStuffing(stuffed, original);
    printf("De-stuffed bit stream: %s\n", original);
    
    return 0;
}
