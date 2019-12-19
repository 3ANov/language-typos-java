public class Main {
    public static void main(String[] args) {
        String inp = "plhfdncdeqnt";
        String inp2 = "здравствуйте";
        System.out.println(inp);

        System.out.println(LangTypos.convert(inp,"en","ru"));
        System.out.println(LangTypos.convert(inp2,"ru","en"));
    }
}
