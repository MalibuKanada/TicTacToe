package Tic;

public class Cell {
    private String content;

    public Cell() {
        clear();
    }

    public void clear() {
        content = " ";
    }

    public void putX() {
        content = "X";
    }

    public void putO() {
        content = "O";
    }

    public String getContent() {
        return content;
    }
    public void paint() {
        System.out.print(content);
    }
}
