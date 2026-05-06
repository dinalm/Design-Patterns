package pixelart;

public class GenerateCodeCommand implements Command {
    private Grid grid;

    public GenerateCodeCommand(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void execute() {
        int[][] pixels = grid.getPixels();
        StringBuilder code = new StringBuilder();
        code.append("int[][] pixelArt = {\n");

        for (int y = 0; y < grid.getSize(); y++) {
            code.append("    {");
            for (int x = 0; x < grid.getSize(); x++) {
                code.append(pixels[y][x]);
                if (x < grid.getSize() - 1) {
                    code.append(", ");
                }
            }
            code.append("}");
            if (y < grid.getSize() - 1) {
                code.append(",");
            }
            code.append("\n");
        }

        code.append("};");
        System.out.println(code.toString());
    }

    @Override
    public void undo() {
        // Code generation doesn't need undo
    }
}