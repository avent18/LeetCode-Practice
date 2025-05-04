public class DominoesSimulation {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] forces = new int[n];
        int force = 0;

        // Left to right: simulate 'R' forces
        for (int i = 0; i < n; i++) {
            char c = dominoes.charAt(i);
            if (c == 'R') {
                force = n;
            } else if (c == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] += force;
        }

        // Right to left: simulate 'L' forces
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = dominoes.charAt(i);
            if (c == 'L') {
                force = n;
            } else if (c == 'R') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0);
            }
            forces[i] -= force;
        }

        // Build the final result
        StringBuilder result = new StringBuilder();
        for (int f : forces) {
            if (f > 0) result.append('R');
            else if (f < 0) result.append('L');
            else result.append('.');
        }

        return result.toString();
    }

    // Example usage
    public static void main(String[] args) {
        DominoesSimulation ds = new DominoesSimulation();
        String input = ".L.R...LR..L..";
        String output = ds.pushDominoes(input);
        System.out.println(output); 
    }
}
